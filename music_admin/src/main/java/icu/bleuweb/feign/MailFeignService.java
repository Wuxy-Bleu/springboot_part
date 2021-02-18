package icu.bleuweb.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("musicMail")
public interface MailFeignService {

    @GetMapping("/send")
    public void sendMail();
}
