package icu.bleuweb.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    /**
     * 发行时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String pic;

    private String lyric;

    private String url;


}
