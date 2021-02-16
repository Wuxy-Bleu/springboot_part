package com.baomidou.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuxy
 * @since 2021-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Integer sex;

    private String phoneNum;

    private String email;

    private LocalDateTime birth;

    private String introduction;

    private String location;

    private String avator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
