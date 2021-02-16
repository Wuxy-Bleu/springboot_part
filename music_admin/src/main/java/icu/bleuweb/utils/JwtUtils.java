package icu.bleuweb.utils;

import icu.bleuweb.bean.Users;
import icu.bleuweb.constants.JwtConstant;
//import icu.bleuweb.dto.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class JwtUtils {

//    public static String createJWT() {
//
//        String audience = "everyone";
//        String jwtId = "wuxy";
//        //用来设置过期时间
//        long TTLMillis = 30 * 24 * 60 * 60;
//        //用来设置签发时间
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        //secret 最主要的签名
//        String base64Security = "secret-wuxy-123-vhaerbvuiqaerwhlguiaebovahweflugfbiwv";
//        //签名算法
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        //生成签名密钥 对签名做了什么处理，得到最终的字符串
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //添加构成JWT的参数
//        JwtBuilder builder = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setIssuedAt(now)
//                .setSubject("wuxy")
//                .setIssuer(UUID.randomUUID().toString())
//                .setId(jwtId)
//                .setAudience(audience)
//                .signWith(signingKey, signatureAlgorithm);  //设置签名使用的签名算法和签名使用的秘钥
//        //添加Token过期时间
//        if (TTLMillis >= 0) {
//            // 过期时间
//            long expMillis = nowMillis + TTLMillis;
//            // 现在是什么时间
//            Date exp = new Date(expMillis);
//            // 系统时间之前的token都是不可以被承认的
//            builder.setExpiration(exp).setNotBefore(now);
//        }
//        //生成JWT
//        return builder.compact();
//    }

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     * DatatypeConverter javax.xml java拓展包提供，将字符串转换为Base64二进制编码
     * Keys.hmacShaKeyFor 这个是jwt导入的包所提供的类和方法
     * SecretKey来自于 javax.crypto  java拓展包提供的加密类
     * 最终的SECRET_KEY是静态常量，无法在外部获取
     */
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(JwtConstant.JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);

    public static String createTokenFromUser(Users user) {

        //通过是否记住，设置过期时间
        long expiration = user.getIsRememberMe() ? JwtConstant.EXPIRATION_REMEMBER : JwtConstant.EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        //创建jwt token
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", JwtConstant.TOKEN_TYPE)   //设置jwt头的typ，但是这里的name参数不应该是typ吗
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)         //通过密钥和签名算法设置JWT的签名部分
                .claim("roles", new String[]{user.getRoles() == 0 ? "admin" : "user"})                     //用户角色信息
                .setId(String.valueOf(user.getId()))                     //以下设置jwt官方规定的字段,id设置用户id，唯一性。
                .setIssuer("wuxy")
                .setIssuedAt(createdDate)
                .setSubject(user.getName())                               //用户名
                .setExpiration(expirationDate)                            //过期时间
                .compact();                                               //转换加密
//        return JwtConstant.TOKEN_PREFIX + tokenPrefix; // 添加 token 前缀 "Bearer ";
        return tokenPrefix;         //加前缀有什么用，我暂时不需要
    }

    /**
     * 注意了  这个方法不仅仅是能够解析jwt token
     * 而且如果我理解没错，它能够直接check认证header中的token
     * 能解析出来就证明这个token没问题，然后通过解析出来的某个字段来实现更进一步的认证
     *
     * @param token
     * @return
     */
    public static Claims getBody(String token) {
        //这是最新的解析jwt token的api，网上大部分找到的都已经@deprecated了

//        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
//        } catch (ExpiredJwtException e) {
//            log.info(" Token expired ");
//            throw e;
//        } catch (Exception e) {
//            log.info(" Some other exception in JWT parsing ");
//            throw e;
//        }
    }


}
