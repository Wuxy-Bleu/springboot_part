package icu.bleuweb.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SongListBean {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String title;
    @TableField("pic")
    private String pic;
    private String introduction;
    private String style;
}
