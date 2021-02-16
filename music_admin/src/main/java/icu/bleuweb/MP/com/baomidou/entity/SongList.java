package com.baomidou.entity;

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
public class SongList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String pic;

    private String introduction;

    private String style;


}
