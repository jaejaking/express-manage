package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TraceRecordCountVo implements Serializable {
    private static final long serialVersionUID = -6148762180048026188L;
    /**
     * 当前快递已有记录数
     */
    private Integer recordCount;

    /**
     * 快递id
     */
    private Integer expressId;


}
