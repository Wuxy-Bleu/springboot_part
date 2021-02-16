package icu.bleuweb.controller;

import icu.bleuweb.feign.TestFeign;
import icu.bleuweb.oss.MyOSSClient;
import icu.bleuweb.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
public class TestController {

//    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    MyOSSClient myOSSClient;

    @GetMapping("/test")
    public Map<String, String> test() {

        log.info("获取ossde签名来了");

        Map respMap = null;
        try {
            respMap = myOSSClient.getPolicy("justtest");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return respMap;
    }

    @Autowired
    TestFeign testFeign;

    @GetMapping("/test2")
    public void test02() {

        testFeign.getMusics();
    }


    @Autowired
    OssService ossService;

    @GetMapping("oss/callback")
    public String ossCallback(HttpServletRequest httpServletRequest) {

//        RestTemplate restTemplate

        System.out.println("controller hit");
        return ossService.callback(httpServletRequest);
    }

}
