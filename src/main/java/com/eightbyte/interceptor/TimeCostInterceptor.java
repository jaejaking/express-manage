package com.eightbyte.interceptor;

import com.eightbyte.util.NetWorkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author  yanghaoran@ehomepay.com.cn
 *@createDate  2019/2/19
 *@description 统计api处理耗时
 */
@Component("timeCostInterceptor")
public class TimeCostInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> startTimeThreadLocal=new NamedThreadLocal<>("本地线程变量存储消耗时间");

    private Logger logger= LoggerFactory.getLogger(TimeCostInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("收到来自ip:"+ NetWorkUtil.getIpAddr(httpServletRequest)+"\t的请求，请求接口是:"+httpServletRequest.getRequestURI());
        Long beginTime=System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();
        long consumeTime = endTime - beginTime;
        if (handler instanceof HandlerMethod) {
            try {
                logger.info("ip:" + NetWorkUtil.getIpAddr(httpServletRequest) +" invoke {}.{}, invoke elapsed {} ms ", ((HandlerMethod) handler).getBean().getClass()
                        .getName(), ((HandlerMethod) handler).getMethod().getName(), consumeTime);
            } catch (Exception e) {
                logger.error("postHandle:", e);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
