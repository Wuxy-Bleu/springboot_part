package icu.bleuweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.bleuweb.aspect.WebLog;
import icu.bleuweb.bean.Users;
import icu.bleuweb.service.UserService;
import icu.bleuweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    //需要开始集成数据库了
    @RequestMapping("/login_wxy")
    public String login() {
//        log.info(str);
        System.out.println("hafsfvhbakefjsvbnlajs");
        return "admin-token";
    }

    @Autowired
    UserService userService;

    @GetMapping("/getusers/{userid}")
    public Object getUserInfo(@PathVariable("userid") Integer userId) {
        System.out.println(userId);
        Users users = userService.getById(userId);
        return users;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login02")
    @WebLog(description = "测试===============================================>")
    public HashMap<String, String> userLogin(@RequestBody Users user) {
        System.out.println(user);

        HashMap<String, String> res = new HashMap<>();

        QueryWrapper<Users> query = Wrappers.<Users>query()
                .eq("name", user.getName())
                .eq("password", user.getPassword());

        Users queryedUser = userService.getOne(query);
        if (queryedUser != null) {
            //生成jwt token返回
            String token = JwtUtils.createTokenFromUser(queryedUser);
            //登录时将用户的登录信息存到redis中， (用户在数据库中的id，生成的token)的形式存放， 30分钟过期。
            //过期就是指这个记录就不在redis中存在了。
            redisTemplate.opsForValue().set(String.valueOf(queryedUser.getId()), token, 60 * 30, TimeUnit.SECONDS);
            log.info("id:token的形式存放到redis中，过期时间30分钟 "
                    , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date().getTime() + 60 * 30 * 1000));
            //TODO 我把用户的角色信息放在jwt token中，那么前端就没办法通过用户的角色信息做什么操作了，所以还得在设置一个roles字段，
            // 保存到前端的vuex和cookies中
            res.put("jwtToken", token);
            res.put("roles", queryedUser.getRoles() == 0 ? "admin" : "user");
        } else
            res.put("state", "failed, no such user");
        return res;

    }

    @PostMapping("/register")
    public void register(@RequestBody Users user) {
        System.out.println(user);
        userService.save(user);
        System.out.println("fads");
    }

    @PostMapping("/info")
    @WebLog(description = "测试===============================================>")
    public Map<String, Object> getUserInfo(@RequestParam String token) {
        Map<String, Object> res = new HashMap<>();
        System.out.println(token);
        Claims body = JwtUtils.getBody(token);
        Object roles = body.get("roles");
        res.put("roles", roles);
        res.put("name", body.getSubject());
        res.put("id", body.getId());
        res.put("avatar", "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png");
        res.put("introduction", "asfjashvohefihbosetjgoarvoiae;itvnae");
        return res;
    }


}
