package com.hao.movieshareback.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.dao.LogMapper;
import com.hao.movieshareback.model.Log;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Date;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @Transactional(rollbackFor = Exception.class)
    public void save(JwtUser user, String browser, String ip, ProceedingJoinPoint joinPoint, Log log) throws JsonProcessingException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.hao.movieshareback.annotation.log.Log aopLog = method.getAnnotation(com.hao.movieshareback.annotation.log.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        ObjectMapper objectMapper = new ObjectMapper();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(objectMapper.writeValueAsString(argValues[i]));
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
            log.setBusinessType(aopLog.businessType());
        }
        assert log != null;
        log.setRequestIp(ip);
        log.setMethod(methodName);
        if (user!=null) {
            log.setUsername(user.getUsername());
            log.setUserId(user.getUserId());
        }else {
            log.setUserId(-1);
            log.setUsername("visitor");
        }
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        log.setCreateTime(new Date());
        logMapper.save(log);
    }

}
