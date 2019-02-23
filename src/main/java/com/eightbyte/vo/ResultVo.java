/**
 * Copyright © 2016 理房通. All rights reserved.
 *
 * @Title： ResultVo.java
 * @Package： com.ehomepay.common.viewmode
 * @author： zhangpeng
 * @date： 2016年2月23日 上午11:54:18  
 * @version  V0.01    
 */
package com.eightbyte.vo;

import java.io.Serializable;

/**
 *@author  yanghaoran@ehomepay.com.cn
 *@createDate  2019/2/20
 *@description controller返回实体json
 */
public class ResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "";
    private String info = "";
    private transient Object data = "";

    public ResultVo() {
    }

    public ResultVo(String code, Object res) {
        this(code, "", res);
    }

    public ResultVo(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResultVo(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code to set code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info to set info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data to set data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResultVo [code=" + code + ", info=" + info + ", data=" + data + "]";
    }
   
}
