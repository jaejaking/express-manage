package com.eightbyte.controller;


import com.eightbyte.enumration.ResultCode;
import com.eightbyte.util.NetWorkUtil;
import com.eightbyte.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/23
 * @description 基础控制器
 */
public abstract class BaseController {

    /**
     * 日志类
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * @param request
     * @return
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
     * @param info
     * @return ResultVo
     * @Description: 返回封装, code=400
     */
    protected ResultVo warn(String info) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.WARN.getCode());
        vo.setInfo(info);
        return vo;
    }

    /**
     * @param info
     * @return ResultVo
     * @Description: 返回封装, code=500
     */
    protected ResultVo error(String info) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.ERROR.getCode());
        vo.setInfo(info);
        return vo;
    }

    /**
     * @param info
     * @param res
     * @return ResultVo
     * @Description:返回封装，code=500支持自定义res信息
     */
    protected ResultVo error(String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.ERROR.getCode());
        vo.setInfo(info);
        vo.setData(res);
        return vo;
    }

    /**
     * @param res
     * @return
     */
    protected ResultVo success(Object res) {
        return success(null, res);
    }

    /**
     * @param code 返回码{@link ResultCode}
     * @param info 页面提示信息
     * @param res  数据字段          可以为空
     * @return ResultVo
     * @Description:自定义提示信息方法
     */
    protected ResultVo message(String code, String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(code);
        vo.setInfo(info);
        if (null != res) {
            vo.setData(res);
        }
        return vo;
    }

    protected ResultVo message(String code, String info) {
        return this.message(code, info, null);
    }

    /**
     * @param info
     * @param res
     * @return 设定文件
     * @Description: 返回封装，code=0
     */
    public ResultVo success(String info, Object res) {
        ResultVo vo = new ResultVo();
        vo.setCode(ResultCode.SUCCESS.getCode());
        if (info == null) {
            vo.setInfo("success");
        } else {
            vo.setInfo(info);
        }
        if (null != res) {
            vo.setData(res);
        }
        return vo;
    }

}
