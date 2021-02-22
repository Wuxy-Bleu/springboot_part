package icu.bleuweb.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer type;

    private Integer songId;

    private Integer songListId;

    private LocalDateTime createTime;

}
