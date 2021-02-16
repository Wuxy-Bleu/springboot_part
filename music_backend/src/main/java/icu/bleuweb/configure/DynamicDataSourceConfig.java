package icu.bleuweb.configure;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import icu.bleuweb.dataSource.DataSourceNames;
import icu.bleuweb.dataSource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 *
 * @author geYang
 * @date 2018-05-14
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource oneDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")   //这个很明显是从配置文件中读取部分配置
    public DataSource twoDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceNames.ONE, oneDataSource);
        targetDataSources.put(DataSourceNames.TWO, twoDataSource);
        // 还有数据源,在targetDataSources中继续添加
        System.out.println("DataSources:" + targetDataSources);
        return new DynamicDataSource(oneDataSource, targetDataSources);
    }
}

