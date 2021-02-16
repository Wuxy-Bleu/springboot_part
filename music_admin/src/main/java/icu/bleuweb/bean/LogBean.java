package icu.bleuweb.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "logs")   //mongodb的注解 就类似于数据库的bean  一个对象对应一个collection 但是貌似不加也没事
@Data
public class LogBean {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String ip;           //发起请求的host
    private String url;          //请求的path
    private String httpMethod;   //请求方法
    private String className;    //controller类名
    private String method;       //controller方法名
    private String reqParam;     //请求参数 不是请求头
}