package icu.bleuweb.configure;

import icu.bleuweb.filter.GlobalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    /**
     * 将我自定义的第一个过滤器注册到容器中
     * @return
     */
    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new GlobalFilter());
        registration.addUrlPatterns("/*");         //过滤器过滤的URL地址
//        registration.setName("GlobalFilter");
        registration.setOrder(1);                  //过滤器执行的顺序
        return registration;
    }
}
