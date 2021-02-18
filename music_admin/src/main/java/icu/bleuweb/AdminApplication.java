package icu.bleuweb;

import icu.bleuweb.controller.UserController;
import icu.bleuweb.dataSource.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ServletComponentScan
@MapperScan("icu.bleuweb.mapper")
@EnableDiscoveryClient
@EnableFeignClients("icu.bleuweb.feign")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
