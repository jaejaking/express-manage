package com.eightbyte.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/24
 * @description 前端跨域处理
 */
@Component("crossDomainInterceptor")
public class CrossDomainInterceptor implements HandlerInterceptor {

    @Value("${ajax.origin.value}")
    private String origin;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        String originStr = httpServletRequest.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", originStr);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        //允许ajax访问携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
