package com.eightbyte.interceptor;

import com.alibaba.fastjson.JSON;
import com.eightbyte.constant.Constant;
import com.eightbyte.enumration.ResultCode;
import com.eightbyte.vo.ResultVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

@Component("loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(true);
        Object attribute = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (attribute == null) {
            response.setContentType("application/json;charset=UTF-8");
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(ResultCode.LOGOUT.getCode());
            resultVo.setInfo("用户未登录!");
            response.getOutputStream().write(JSON.toJSONString(resultVo).getBytes(Charset.forName("UTF-8")));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
