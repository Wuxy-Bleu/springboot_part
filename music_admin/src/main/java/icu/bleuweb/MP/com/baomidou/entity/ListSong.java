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
public class ListSong implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer songId;

    private Integer songListId;


}
