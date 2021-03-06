package com.baomidou.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("songListId")
    private Long songlistid;

    @TableField("consumerId")
    private Long consumerid;

    private Integer score;


}
