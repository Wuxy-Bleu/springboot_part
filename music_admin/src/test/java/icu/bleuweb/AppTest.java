package icu.bleuweb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.bleuweb.bean.SongListBean;
import icu.bleuweb.bean.Users;
import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.dataSource.DataSourceNames;
import icu.bleuweb.mapper.UserMapper;
import icu.bleuweb.service.UserService;
import icu.bleuweb.service.YinMusicService;
import icu.bleuweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.ToString;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AppTest {

    @Test
    public void testJwt() {

        Map<String, Object> map = new HashMap<>();
        map.put("user", "wuxy");
        map.put("pw", "123");
        String audience = "test";
        String jwtId = "LJH";
        long TTLMillis = 30 * 24 * 60 * 60;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);


        String base64Security = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥 就是一个base64加密后的字符串？
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setSubject(map.toString())
                .setIssuer(UUID.randomUUID().toString())
                .setId(jwtId)
                .setAudience(audience)
                .signWith(signingKey, signatureAlgorithm);  //设置签名使用的签名算法和签名使用的秘钥
        //添加Token过期时间
        if (TTLMillis >= 0) {
            // 过期时间
            long expMillis = nowMillis + TTLMillis;
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        System.out.println(builder.compact());
    }

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Test
    public void testIsExist() {
        //TODO Wrappers.<Users>query()这是什么写法 泛型方法？？
        Users user = userService.getOne(Wrappers.<Users>query()
                .eq("name", "wuxy")
                .eq("password", "vsdf"));

//        QueryWrapper<Users> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", "wuxy");
//        Users user = userService.getOne(wrapper);

//        QueryWrapper<Users> queryWrapper = Wrappers.<Users>query()
//                .eq("name", "wuxy")
//                .eq("password", "123");
//        Users user = userMapper.selectOne(queryWrapper);

        System.out.println(user);
    }

    @Test
    public void testJwtRoles() {

        Users user = new Users();
        user.setName("wuxy");
        user.setRoles(0);

        String token = JwtUtils.createTokenFromUser(user);

        Claims body = JwtUtils.getBody(token);
        Object roles = body.get("roles");

        System.out.println(roles);

    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("1", "msgafsngteionbt");
    }

    @Autowired
    YinMusicService yinMusicService;

    @Test
    public void testMP() {
        List<SongListBean> songListBeans = yinMusicService.allSongList();
        for (SongListBean bean :
                songListBeans) {
            System.out.println(bean);
        }
    }
}
