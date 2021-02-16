package icu.bleuweb.security.config;

import icu.bleuweb.filter.CorsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //网上找的spring security 放行某一个url的配置代码
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors(withDefaults())
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .cors()
//                .and()
//                .csrf()
//                .disable();

        log.info("security的过滤器");

        http
                .csrf().disable()   //关闭.csrf
                .authorizeRequests()
                .antMatchers("/user/**", "/redis/**", "/yin/**").permitAll()             //放行
                .anyRequest().authenticated()
                .and().addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)       //spring security所有过滤器之前先执行我的过滤器
                .formLogin();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/**/*.js",
                "/lang/*.json", "/**/*.css",
                "/**/*.js", "/**/*.map", "/**/*.html",
                "/**/*.png",
                "/img/**/*.png", "/img/**/*.jpg",
                "/song/*.mp3");//最后两个规则是我自己写的
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

//    /**
//     * Cors配置优化
//     **/
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(singletonList("*"));
//        configuration.setAllowedHeaders(singletonList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
//        configuration.setAllowCredentials(false);
//        configuration.setMaxAge(3600l);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
