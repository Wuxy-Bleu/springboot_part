//package icu.bleuweb.configure;
//
//import icu.bleuweb.interceptor.GlobalInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MyWebMvcConfigurer implements WebMvcConfigurer {
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("*")
////                .allowCredentials(true)
////                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
////                .maxAge(3600);
////    }
//
//    @Autowired
//    GlobalInterceptor myInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/cookie");
//    }
//}
