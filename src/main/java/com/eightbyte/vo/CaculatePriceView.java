package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaculatePriceView implements Serializable {
    private static final long serialVersionUID = -5874114094476840875L;
    /**
     * 总距离
     */
    private BigDecimal distance;
    /**
     * 预估价格
     */
    private BigDecimal price;
    /**
     * 运输方式
     */
    private Integer transferType;

}
