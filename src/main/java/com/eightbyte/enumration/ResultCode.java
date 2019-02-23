package com.eightbyte.enumration;

/**
 *@author  yanghaoran@ehomepay.com.cn
 *@createDate  2019/2/20
 *@description 业务状态码
 */
public enum ResultCode {
    SUCCESS("200"),
    WARN("600"),          //核心返回错误
    ERROR("500"),         //参数错误
    LOGOUT("20001");    //没登录

    private String code;

    private ResultCode(String code){
     this.code=code;
   }


    public String getCode() {
        return code;
    }


}
