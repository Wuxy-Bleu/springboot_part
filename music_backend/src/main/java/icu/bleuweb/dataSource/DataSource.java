package icu.bleuweb.dataSource;

import java.lang.annotation.*;

/*在我的理解中，注解还只是一个标注的作用，也就是标注了这个注解的类，方法等，会怎么怎么样，也就是不包括任何业务
* 那么标注了这个注解的类要实现什么业务呢，貌似我现在只知道通过aop的方式来实现
* 标注了注解的作为切入点，然后在这里写业务
* */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default DataSourceNames.ONE;
}
//但是 这个注解，用来选择数据源的，我该标注在哪里呢，service的实现类的方法上吗。标注在controller中？？
//而且我感觉标注在dao层的接口中才是最正宗的