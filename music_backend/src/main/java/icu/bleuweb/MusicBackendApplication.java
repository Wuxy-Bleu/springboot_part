package icu.bleuweb;

import com.alibaba.cloud.nacos.ConditionalOnNacosDiscoveryEnabled;
import icu.bleuweb.configure.DynamicDataSourceConfig;
import icu.bleuweb.utils.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;

@EnableDiscoveryClient
@EnableTransactionManagement
@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MusicBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicBackendApplication.class, args);

//        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
//        DataSource bean = applicationContext.getBean(DataSource.class);
//        Object druid = applicationContext.getBean("druid");
//
//        System.out.println(bean == druid);
//
//        String name = applicationContext.getBean(DataSource.class).getClass().getName();
//        String canonicalName = applicationContext.getBean(DataSource.class).getClass().getCanonicalName();
//        Annotation[] annotations = applicationContext.getBean(DataSource.class).getClass().getAnnotations();
//        String simpleName = applicationContext.getBean(DataSource.class).getClass().getSimpleName();
//        String typeName = applicationContext.getBean(DataSource.class).getClass().getTypeName();
//
//
//        System.out.println(applicationContext);
//        System.out.println(bean);
    }
}
