package com.hao.movieshareback.service;

import cn.hutool.core.util.IdUtil;
import com.hao.movieshareback.config.auth.JwtTokenGenerator;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.RoleMapper;
import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.exception.ActiveUserException;
import com.hao.movieshareback.exception.LoginException;
import com.hao.movieshareback.exception.UserExistException;
import com.hao.movieshareback.exception.ValidateCodeExpireException;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.service.auth.JwtUserDetailsService;
import com.hao.movieshareback.service.mail.MailService;
import com.hao.movieshareback.service.redis.IRedisService;
import com.hao.movieshareback.utils.EncryptUtils;
import com.hao.movieshareback.vo.MenuVo;
import com.hao.movieshareback.vo.auth.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private MailService mailService;
    //异步执行任务
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Value("${email-validate.key}")
    private String emailKey;

    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }

    public boolean isEmailExist(String email){
        return userMapper.getCountByEmail(email)!=0;
    }

    public boolean isUserNameExist(String userName){
        return userMapper.getCountByUserName(userName)!=0;
    }

    public boolean isUserActive(String userName){
        User user = userMapper.getUserByUserName(userName);
        if (user==null){
            return false;
        }
        return user.isHasActive();
    }

    public void registerUser(RegisterVo registerVo) throws MessagingException {
        //校验验证码
        redisService.validateCode(registerVo.getValidCode(),registerVo.getUuid());
        if (this.isUserNameExist(registerVo.getUserName())){
            throw new UserExistException("用户已经存在");
        }
        if (this.isEmailExist(registerVo.getEmail())){
            throw new UserExistException("邮箱已经注册过了");
        }
        sendActiveEmailAsync(registerVo.getUserName(),registerVo.getEmail());
        User user = createUser(registerVo);
        userMapper.save(user);
    }

    private void sendActiveEmail(String userName,String email) throws MessagingException {
        String activeToken = emailKey+IdUtil.simpleUUID();
        redisService.saveMailVadateToken(activeToken,userName);

        String content = createActiveContent(userName,activeToken);
        mailService.sendHtmlMail(email,"激活帐号邮件",content);
    }

    private void sendActiveEmailAsync(final String userName,final String email){
        threadPoolTaskExecutor.execute(()->{
            try {
                sendActiveEmail(userName,email);
            } catch (MessagingException e) {
                e.printStackTrace();
                log.error("发送邮件错误:{}",e.getMessage());
            }
        });
    }

    private String createActiveContent(String userName,String activeToken){
        String html ="<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>用户激活邮件</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <img src=\"http://localhost:8089/logo.png\">\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <p>\n" +
                "            亲爱的用户请点击以下链接，激活用户：\n" +
                "        </p>\n" +
                "        <a href=\"http://localhost:8080/#/user/active?userName="+userName+"&token="+activeToken+"\">激活账户</a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return html;
    }

    private User createUser(RegisterVo registerVo){
        //盐值
        String salt = EncryptUtils.md5(IdUtil.simpleUUID());
        String passwordWithSalt = EncryptUtils.md5(registerVo.getPassword()+salt);
        User user = new User(registerVo.getUserName(),passwordWithSalt,salt,registerVo.getEmail());
        return user;
    }
    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
    public void activeUser(String userName,String token) throws ActiveUserException {
        User user = userMapper.getUserByUserName(userName);
        if (user!=null){
            if (user.isHasActive()){
                throw new ActiveUserException("用户已经激活");
            }else {
                String tokenValue = redisService.getMailVadateToken(token);
                if (StringUtils.isBlank(tokenValue)){
                    throw new ActiveUserException("激活超期");
                }else if (!StringUtils.equals(userName,tokenValue)){
                    throw new ActiveUserException("token可能被冒用");
                }else {
                    userMapper.activeUser(userName);
                    Role role = roleMapper.selectRoleByRoleName("user");
                    roleMapper.addUserRole(user.getUserId(),role.getRoleId());
                }
            }
        }else {
            throw new ActiveUserException("用户不存在，请前往注册");
        }
    }

    public void resendActiveMail(String userName) throws MessagingException,ActiveUserException {
        User user = userMapper.getUserByUserName(userName);
        if (user!=null){
            if (user.isHasActive()){
                throw new ActiveUserException("用户已经激活");
            }else {
                sendActiveEmailAsync(userName,user.getEmail());
            }
        }else {
            throw new ActiveUserException("用户不存在");
        }
    }

    public AuthInfo login(AuthUser authUser, HttpServletRequest request) {
        redisService.validateCode(authUser.getValidateCode(),authUser.getUuid());
        JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(authUser.getUserName());
        List<MenuVo> menuVoList = menuService.createMenuVoList(jwtUser.getUsername());
        if (jwtUser==null){
            throw new LoginException("用户不存在");
        }else if (!jwtUser.isEnabled()){
            throw new LoginException("用户还没有激活");
        }
        String salt = jwtUser.getSalt();
        String passwordWithSalt = EncryptUtils.md5(authUser.getPassword()+salt);
        if (!StringUtils.equals(passwordWithSalt,jwtUser.getPassword())){
            throw new LoginException("密码错误");
        }
        // 生成令牌
        final String token = jwtTokenGenerator.generateToken(jwtUser);
        // 保存在线信息
        onlineUserService.save(jwtUser, token, request);
        // 返回 token
        return new AuthInfo(token,new UserVo(jwtUser.getUserId(),jwtUser.getUsername(),jwtUser.getAvatarUrl(),jwtUser.getEmail(),menuVoList));
    }

    public void logOut(HttpServletRequest request){
        onlineUserService.logout(jwtTokenGenerator.getToken(request));
    }

    public UserVo getUserVoByUserId(Integer userId){
        User user = userMapper.getUserByUserId(userId);
        Picture avatar = pictureMapper.selectPictureById(user.getAvatarPicId());
        Picture skin = pictureMapper.selectPictureById(user.getUserSkinId());
        UserVo userVo = new UserVo(user.getUserId(),user.getUserName(),avatar.getUrl(),skin.getUrl(),user.getIntroduce(),null,null);
        return userVo;
    }

    public void updateUserAvatar(Integer userId,Integer pictureId){
        userMapper.updateUserAvatarUrl(userId,pictureId);
    }
}
