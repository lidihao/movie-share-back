package com.hao.movieshareback.filter.auth;

import com.hao.movieshareback.config.auth.JwtTokenGenerator;
import com.hao.movieshareback.model.OnlineUser;
import com.hao.movieshareback.vo.auth.JwtUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationTokenFilter.class);

    @Value("${jwt.online}")
    private String onlineKey;

    private final UserDetailsService userDetailsService;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final RedisTemplate redisTemplate;

    public JwtAuthorizationTokenFilter(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, JwtTokenGenerator jwtTokenGenerator, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = jwtTokenGenerator.getToken(request);
        OnlineUser onlineUser = null;
        try {
            onlineUser = (OnlineUser)redisTemplate.opsForValue().get(onlineKey + authToken);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
        }
        if (onlineUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            JwtUser userDetails = (JwtUser)this.userDetailsService.loadUserByUsername(onlineUser.getUserName());
            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (jwtTokenGenerator.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
