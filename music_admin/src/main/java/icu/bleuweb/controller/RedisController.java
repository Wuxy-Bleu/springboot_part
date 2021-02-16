package icu.bleuweb.controller;

import icu.bleuweb.aspect.WebLog;
import icu.bleuweb.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    //这里是cookie中已查到了登录信息后，查看redis中是否过期的。
    //不仅如此这个还可以作为第二个vue应用中，通过token查看是否登录
    //TODO 这里的逻辑 redis集成上了，但是其实它根本没有用处，我现在所有的功能都可以通过设置cookie和jwt token的过期时间来实现的
    // redis是用来解决后端session无法分布式的问题的，但是我根本没有用到session
    @GetMapping("/isLogin")
    @WebLog(description = "测试===============================================>")
    public HashMap<String, String> getToken(@RequestParam(value = "token") String token) {

        String id = null;
        HashMap<String, String> res = new HashMap<>();
        try {
            id = JwtUtils.getBody(token).getId();
            log.info(redisTemplate.hasKey(id) ? "redis中有数据" : "redis中无数据");
            res.put("isLogin", String.valueOf(redisTemplate.hasKey(id)));
        } catch (ExpiredJwtException e) {
            //TODO 当前对于token过期的处理也很随意，只不过是过期了，让它自动去登录页而已。
            System.out.println("===============================");
            System.out.println(e);
            res.put("errMsg", e.getMessage());
            res.put("redirect", "login");
            res.put("isLogin", "false");
        } catch (Exception e) {
            res.put("errMsg", e.getMessage());
            res.put("isLogin", "false");
        }

        return res;
    }

}
