package icu.bleuweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public void test(){
        System.out.println("跳转成功了嘛？？？？");
    }
}
