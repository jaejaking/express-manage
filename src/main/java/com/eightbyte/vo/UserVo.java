package com.eightbyte.vo;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = -3109478004251771885L;

    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 派送任务数
     */
    private Integer tasks;


}
