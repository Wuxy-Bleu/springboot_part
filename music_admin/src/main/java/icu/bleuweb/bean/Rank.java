package icu.bleuweb.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
    private Long songlistId;

    @TableField("consumerId")
    private Long userId;

    private Integer score;


}
