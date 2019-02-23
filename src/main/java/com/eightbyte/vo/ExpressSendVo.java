package com.eightbyte.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpressSendVo implements Serializable {

    private static final long serialVersionUID = -4595607727578118541L;


    private String sendName;

    private String sendPhone;

    private String sendIdNo;

    private String sendProvince;

    private String sendCity;

    private String sendDistrict;

    private String sendDetailedAddr;


    private String receiveName;

    private String receivePhone;

    private String receiveProvince;

    private String receiveCity;

    private String receiveDistrict;

    private String receiveDetailedAddr;

    /**
     * 商品类型（0：其他 1:数码产品  2:生活用品 3:服饰 4:食品 5：文件）
     */
    private Integer goodsType;
    /**
     * 预估重量
     */
    private BigDecimal estimateWeight;

}
