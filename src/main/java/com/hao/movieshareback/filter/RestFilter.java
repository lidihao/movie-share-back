package com.hao.movieshareback.filter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class RestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String originUrl = req.getHeader("Origin");
		resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, range, Referer, method");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
		if (originUrl != null) {
			resp.setHeader("Access-Control-Allow-Origin", originUrl);
			resp.setHeader("Access-Control-Allow-Credentials", "true");
		} else {
			String allowhost=req.getHeader("Referer");
			resp.setHeader("Access-Control-Allow-Origin", allowhost);
			resp.setHeader("Access-Control-Allow-Credentials", "true");
		}
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            filterChain.doFilter(request,response);
        }
    }

	@Override
	public void destroy() {

	}
}
