package com.hao.movieshareback.controller;

import cn.hutool.core.util.IdUtil;
import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.exception.*;
import com.hao.movieshareback.service.MenuService;
import com.hao.movieshareback.service.UserService;
import com.hao.movieshareback.service.auth.JwtUserDetailsService;
import com.hao.movieshareback.service.redis.IRedisService;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.ChangePwdDataReceiver;
import com.hao.movieshareback.vo.ImgResult;
import com.hao.movieshareback.vo.MenuVo;
import com.hao.movieshareback.vo.ResultBody;
import com.hao.movieshareback.vo.auth.*;
import com.wf.captcha.ArithmeticCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${jwt.codeKey}")
    private String codeKey;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private MenuService menuService;


    @AnonymousAccess
    @GetMapping("/code")
    public ResultBody getVerifyCode(){
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果：5
        String result = captcha.text();
        String uuid = codeKey + IdUtil.simpleUUID();
        redisService.saveCode(uuid,result);
        return ResultBody.success(new ImgResult(captcha.toBase64(),uuid));
    }

    @AnonymousAccess
    @PostMapping("/register")
    public ResultBody registerUser(@RequestBody RegisterVo registerVo){
        try {
            userService.registerUser(registerVo);
            return ResultBody.success("请前往邮箱激活账户");
        }catch (Exception e){
            throw new RegisterException(e.getMessage());
        }
    }

    @AnonymousAccess
    @PostMapping("/user/active")
    public ResultBody userActive(String userName,String token){
        try {
            userService.activeUser(userName,token);
            return ResultBody.success("激活成功");
        }catch (ActiveUserException e){
            logger.warn("激活用户出错:"+e.getMessage());
            return ResultBody.error(e.getMessage());
        }
    }

    @AnonymousAccess
    @GetMapping("/user/resendActiveMail")
    public ResultBody resendActiveMail(String userName){
        try {
            userService.resendActiveMail(userName);
            return ResultBody.success();
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.warn("重发激活邮件错误:"+e.getMessage());
            return ResultBody.error(e.getMessage());
        }catch (ActiveUserException e){
            logger.warn("重发邮件错误:"+e.getMessage());
            return ResultBody.error(e.getMessage());
        }
    }

    @AnonymousAccess
    @PostMapping("/login")
    public ResultBody login(@RequestBody AuthUser authUser, HttpServletRequest request){
        try {
            AuthInfo info = userService.login(authUser,request);
            return ResultBody.success(info);
        }catch (ValidateCodeExpireException |UsernameNotFoundException|LoginException e){
            e.printStackTrace();
            logger.warn("用户登录错误");
            return ResultBody.error(e.getMessage());
        }
    }

    @GetMapping("/userInfo")
    public ResultBody getUserInfo(){
        JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        List<MenuVo> menuVoList = menuService.createMenuVoList(jwtUser.getUsername());
        return ResultBody.success(new UserVo(jwtUser.getUserId(),jwtUser.getUsername(),jwtUser.getAvatarUrl(),jwtUser.getEmail(),menuVoList));
    }

    @AnonymousAccess
    @GetMapping("/getUserInfoById")
    public ResultBody getUserInfo(Integer userId){
        return ResultBody.success(userService.getUserVoByUserId(userId));
    }


    @AnonymousAccess
    @GetMapping("/menuTree")
    public ResultBody getMenuTree(){
        //游客
        List<MenuVo> menuVoList = null;
        if (SecurityUtils.isLogin()){
            menuVoList = menuService.createMenuVoList(SecurityUtils.getUsername());
        }else{
            menuVoList = menuService.createMenuVoListByRoleName("visitor");
        }
        return ResultBody.success(menuVoList);
    }

    @AnonymousAccess
    @PostMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request){
        userService.logOut(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateUserAvatarUrl")
    public ResultBody updateUserAvatarUrl(Integer userId,Integer pictureId){
        userService.updateUserAvatar(userId,pictureId);
        return ResultBody.success();
    }

    @AnonymousAccess
    @GetMapping("/searchUser")
    public ResultBody searchUser(String searchKey,Integer pageNum,Integer pageSize){
        if (Strings.isBlank(searchKey)){
            return ResultBody.success();
        }
        return ResultBody.success(userService.searchUser(searchKey,pageNum,pageSize));
    }

    @AnonymousAccess
    @PostMapping("/sendResetPasswordEmail")
    public ResultBody sendResetPasswordEmail(String email){
        if (Strings.isBlank(email)){
            return ResultBody.error("邮箱为空");
        }
        try {
            userService.sendResetEmail(email);
            return ResultBody.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultBody.error(e.getMessage());
        }
    }

    @AnonymousAccess
    @PostMapping("/resetPassword")
    public ResultBody sendResetPasswordEmail(String password,String token,
                                             String uuid,String code,HttpServletRequest request){
        try {
            userService.resetPassword(password, token,uuid,code,request);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }
    @PostMapping("/changePwd")
    public ResultBody changePwd(@RequestBody ChangePwdDataReceiver changePwdDataReceiver,HttpServletRequest httpServletRequest){
        try {
            userService.changePwd(changePwdDataReceiver,httpServletRequest);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }
    @PostMapping("/sendChangeEmail")
    public ResultBody sendChangeEmail(String newEmail){
        try {
            userService.sendChangeEmail(newEmail);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }

    @AnonymousAccess
    @PostMapping("/doChangeEmail")
    public ResultBody changeEmail(String token){
        userService.changeEmail(token);
        return ResultBody.success();
    }
}
