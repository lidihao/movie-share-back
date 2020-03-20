package com.hao.movieshareback.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hao.movieshareback.model.Log;
import com.hao.movieshareback.service.LogService;
import com.hao.movieshareback.utils.RequestHolder;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.utils.StringUtils;
import com.hao.movieshareback.utils.ThrowableUtil;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {

    private final LogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.hao.movieshareback.annotation.log.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log("INFO",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        try {
            logService.save(getUser(), StringUtils.getBrowser(request), StringUtils.getIp(request),joinPoint, log);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        try {
            logService.save(getUser(), StringUtils.getBrowser(request), StringUtils.getIp(request), (ProceedingJoinPoint)joinPoint, log);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    public JwtUser getUser() {
        try {
            return (JwtUser) SecurityUtils.getUserDetails();
        }catch (Exception e){
            return null;
        }
    }
}

