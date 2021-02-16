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
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer sex;

    private String pic;

    private LocalDateTime birth;

    private String location;

    private String introduction;


}
