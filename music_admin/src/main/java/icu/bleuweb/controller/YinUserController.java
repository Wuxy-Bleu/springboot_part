package icu.bleuweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.bleuweb.aspect.WebLog;
import icu.bleuweb.bean.Users;
import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.service.UserService;
import icu.bleuweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
public class YinUserController {

    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    @WebLog(description = "用来给yin-music登录")
    @DataSource
    public HashMap<String, String> userLogin(@RequestParam String username, @RequestParam String password) {

        HashMap<String, String> res = new HashMap<>();

        QueryWrapper<Users> query = Wrappers.<Users>query()
                .eq("name", username)
                .eq("password", password);

        Users queryedUser = null;
        try {
            queryedUser = userService.getOne(query);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            log.info("是否查询成功" + queryedUser.toString());
        }
        if (queryedUser != null) {
            String token = JwtUtils.createTokenFromUser(queryedUser);
            try {
                redisTemplate.opsForValue().set(String.valueOf(queryedUser.getId()), token, 60 * 30, TimeUnit.SECONDS);
                log.info("id:token的形式存放到redis中，过期时间30分钟 "
                        , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(new Date().getTime() + 60 * 30 * 1000));
                res.put("jwtToken", token);
                res.put("roles", queryedUser.getRoles() == 0 ? "admin" : "user");
                res.put("code", "1");
                res.put("id", String.valueOf(queryedUser.getId()));
                res.put("name", queryedUser.getName());
            } catch (Exception e) {
                log.info(e.getMessage());
                res.put("errMsg", e.getMessage());
            }
        } else
            res.put("state", "failed, no such user");
        return res;

    }

    @GetMapping("/detail/{id}")
    public Users getUserOfId(@PathVariable("id") String id) {
        Users user = null;
        try {
            user = userService.getById(id);
            user.setAvator("https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png");
        } catch (Exception e) {
            log.info("======================  error  ==================================");
            log.info(e.getMessage());
        } finally {
            return user;
        }
    }

}
