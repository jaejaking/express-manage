package com.eightbyte.controller;



import com.eightbyte.enumration.ResultCode;
import com.eightbyte.util.NetWorkUtil;
import com.eightbyte.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @ClassName: BaseController
 * @Description: 基础控制器
 * @author: luxn
 * @date: 2016年1月12日 上午9:38:03
 */
public abstract class BaseController {

    /**
     * 日志类
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    /**
     *
     * @Description: 判断请求是否为ajax
     * @param request
     * @return    设定文件
     * @throws 异常说明
     * @author pangtongtong01 pangtongtong01@baidu.com
     * @date 2014年3月31日 下午1:24:24
     */
    public boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * 获取客户端ip
     *
     * @return
     */
    public String getIp() {
        return NetWorkUtil.getIpAddr(request);
    }

    /**
     *
     * @Description: 返回封装,code=400
     * @param info
     * @return ResultVo
     */
    protected ResultVo warn(String info) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.WARN.getCode());
        vo.setInfo(info);
        return vo;
    }

    /**
     *
     * @Description: 返回封装,code=500
     * @param info
     * @return ResultVo
     */
    protected ResultVo error(String info) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.ERROR.getCode());
        vo.setInfo(info);
        return vo;
    }

    /**
     * @Description:返回封装，code=500支持自定义res信息
     * @param info
     * @param res
     * @return ResultVo
     */
    protected ResultVo error(String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.ERROR.getCode());
        vo.setInfo(info);
        vo.setData(res);
        return vo;
    }

    /**
     * @Description: 返回封装，code=0
     * @param info
     * @param res
     * @return    设定文件
     */
    protected ResultVo success(Object res) {
        return success(null, res);
    }

    /**
     * @Description:自定义提示信息方法
     * @param code 返回码{@link ResultCode}
     * @param info 页面提示信息
     * @param res 数据字段          可以为空
     * @return ResultVo
     */
    protected ResultVo message(String code, String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(code);
        vo.setInfo(info);
        if(null != res){
            vo.setData(res);
        }
        return vo;
    }

    protected ResultVo message(String code,String info){
        return this.message(code, info,null);
    }

    /**
     * @Description: 返回封装，code=0
     * @param info
     * @param res
     * @return    设定文件
     */
    public ResultVo success(String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.SUCCESS.getCode());
        if (info == null) {
            vo.setInfo("success");
        } else {
            vo.setInfo(info);
        }
        if(null != res){
            vo.setData(res);
        }
        return vo;
    }

    /**
     *
     * @Description: 异常处理
     * @param e
     * @param request
     * @return    设定文件
     * @throws 异常说明
     */
    @ResponseBody
    @ExceptionHandler({ MissingServletRequestParameterException.class, TypeMismatchException.class,
            ConversionFailedException.class })
    public ResultVo handleConversionFailedException(Exception e) {
        logger.error("参数不正确:", e);
        return warn("参数不正确！");
    }

    /**
     *
     * @Description:异常处理
     * @param e
     * @return    设定文件
     * @throws 异常说明
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResultVo handleException(Throwable e) {
        logger.error("服务异常:", e);
        return error("网络异常，请稍后再试！");
    }
}
