package com.hao.movieshareback.service.redis;

import com.hao.movieshareback.exception.ValidateCodeExpireException;
import com.hao.movieshareback.utils.PageUtil;
import com.hao.movieshareback.vo.redis.RedisVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@SuppressWarnings({"unchecked","all"})
public class RedisServiceImpl implements IRedisService {

    private final RedisTemplate redisTemplate;

    @Value("${loginCode.expiration}")
    private Long expiration;

    @Value("${jwt.online}")
    private String onlineKey;

    @Value("${jwt.codeKey}")
    private String codeKey;

    @Value("${email-validate.expiration}")
    private Integer expiredTime;

    @Value("${email-validate.key}")
    private String emailKey;

    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<RedisVo> findByKey(String key, Pageable pageable){
        List<RedisVo> redisVos = findByKey(key);
        return new PageImpl<RedisVo>(
                PageUtil.toPage(pageable.getPageNumber(),pageable.getPageSize(),redisVos),
                pageable,
                redisVos.size());
    }

    @Override
    public List<RedisVo> findByKey(String key) {
        List<RedisVo> redisVos = new ArrayList<>();
        if(!"*".equals(key)){
            key = "*" + key + "*";
        }
        Set<String> keys = redisTemplate.keys(key);
        for (String s : keys) {
            // 过滤掉权限的缓存
            if (s.contains("role::loadPermissionByUser") || s.contains("user::loadUserByUsername") || s.contains(onlineKey) || s.contains(codeKey)) {
                continue;
            }
            RedisVo redisVo = new RedisVo(s, Objects.requireNonNull(redisTemplate.opsForValue().get(s)).toString());
            redisVos.add(redisVo);
        }
        return redisVos;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(  "*");
        redisTemplate.delete(keys.stream().filter(s -> !s.contains(onlineKey)).filter(s -> !s.contains(codeKey)).
                filter(s -> !s.contains(emailKey)).collect(Collectors.toList()));
    }

    @Override
    public String getCodeVal(String key) {
        try {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void saveCode(String key, Object val) {
        redisTemplate.opsForValue().set(key,val);
        redisTemplate.expire(key,expiration, TimeUnit.MINUTES);
    }

    @Override
    public void saveMailVadateToken(String key, Object val) {
        redisTemplate.opsForValue().set(key,val);
        redisTemplate.expire(key,expiredTime, TimeUnit.HOURS);
    }

    @Override
    public String getMailVadateToken(String key) {
        try {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void validateCode(String value, String uuid) {
        String code = this.getCodeVal(uuid);
        this.delete(uuid);
        if (StringUtils.isBlank(code)){
            throw new ValidateCodeExpireException("验证码过期了,重新获取验证码");
        }
        if(!StringUtils.equals(code,value)){
            throw new ValidateCodeExpireException("验证码错误,请重新输入");
        }
    }
}
