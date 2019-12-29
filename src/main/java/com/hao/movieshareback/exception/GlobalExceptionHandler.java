package com.hao.movieshareback.exception;

import com.hao.movieshareback.vo.CommonErrorInfo;
import com.hao.movieshareback.vo.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResultBody.error(CommonErrorInfo.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return ResultBody.error(CommonErrorInfo.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value =RegisterException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, RegisterException e){
        logger.warn("用户注册时发生错误",e);
        return ResultBody.error(CommonErrorInfo.ERROR_CODE,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ValidateCodeExpireException.class)
    public ResultBody exceptionHandle(HttpServletRequest req,ValidateCodeExpireException e){
        logger.warn("验证码过期");
        return ResultBody.error(CommonErrorInfo.ERROR_CODE,e.getMessage());
    }
}