package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ExpressInfoVo implements Serializable {

    private static final long serialVersionUID = -6098763897935281939L;

    private Integer expressId;

    private String expressOrderNo;

    private Integer isBusy;

    private Integer status;

    private String sendProvince;

    private String sendCity;

    private String sendDistrict;


    private String receiveProvince;

    private String receiveCity;

    private String receiveDistrict;

    private String sendMobile;

    private String receiveMobile;

    /**
     * 详细地址
     */
    private String detailedAddr;

    private Integer source;


}
