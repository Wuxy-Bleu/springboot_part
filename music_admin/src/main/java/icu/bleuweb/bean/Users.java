package icu.bleuweb.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

//TODO 和dto貌似重合了啊
@Data
@ToString
public class Users {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private Integer roles;
    @TableField(exist = false)
    private Boolean IsRememberMe = false;
    private Byte sex;
    private String avator;
    private Date birthday;
    private String phone_num;
    private String email;
    private String introduction;
}