package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/27
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExpressRecordView implements Serializable {
    private static final long serialVersionUID = -2662997180248748183L;

    private Integer expressId;

    private Integer type;

    private String orderNo;

    private Integer status;

    private Object records;
}
