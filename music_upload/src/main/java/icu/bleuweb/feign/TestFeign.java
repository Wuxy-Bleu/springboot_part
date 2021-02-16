package icu.bleuweb.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("music-backend")    //指定被调用的微服务的  application name
public interface TestFeign {

    /**
     * 将被调用的微服务的controller方法放入这个接口进行管理
     * 注意请求地址要写全
     */
    @GetMapping("/musics")
    public void getMusics();
}
