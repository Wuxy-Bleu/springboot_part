package icu.bleuweb.aspect;

import java.lang.annotation.*;


//TODO 将这个功能放进common模块中
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";

}
