package com.eightbyte.vo;

import lombok.*;


/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/25
 * @description
 */
@Builder
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class TraceRecordVo {

    /**
     * 快递记录表id
     */
    private Integer expressId;

    /**
     * 中转开始地
     */
    private String fromAddr;
    /**
     * 中转下一站地
     */
    private String toAddr;

    /**
     * 是否到终点(0:否 1：是)
     */
    private Integer isFinal;


}
