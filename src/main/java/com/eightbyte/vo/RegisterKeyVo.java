package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RegisterKeyVo implements Serializable {
    private static final long serialVersionUID = -3156462439475951312L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 密钥状态
     */
    private Integer status;

    private String keyName;

    private String unionUser;

    private String roleName;


}
