package icu.bleuweb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ErrorController {

//    /**
//     * 不知道这个error是什么方法，所以用@RequestMapping了
//     */
//    @RequestMapping("/error")
//    public String basicError() {
//        return "error";
//    }

    @GetMapping("/testError")
    public String test() throws Exception {

        throw new Exception("测试异常");
    }

    @GetMapping("/testError2")
    public String test02() throws Exception {

        throw new Exception("111111111111111111111111111");
    }
}
