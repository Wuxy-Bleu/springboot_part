package icu.bleuweb.controller;

//import icu.bleuweb.dto.User;

import icu.bleuweb.bean.Users;
import icu.bleuweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class JwtController {

    /**
     * 获取JWT生成的token
     *
     * @param response
     * @return
     */
    @GetMapping("/getToken1")
    public String getToken(HttpServletResponse response) {
//        String token = JwtUtils.createJWT();
//        return token;
        return "nouse";
    }

    @GetMapping("testAuth")
    public String testAuth(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        System.out.println(token);
        return "ok";
    }

    @PostMapping("/gettoken2")
    public String getToken2(@RequestBody Users user, HttpServletRequest request, HttpServletResponse response) {

        String token = JwtUtils.createTokenFromUser(user);
        Cookie cookie = new Cookie(user.getName(), token);
        response.addCookie(cookie);

        HttpSession session = request.getSession();
//        session.getAttributeNames();
        session.setAttribute(user.getName(), token);

        return token;
    }

    @GetMapping("/parseToken")
    public String parseToken(
//            HttpServletRequest request,
//            @RequestHeader HttpHeaders headers,
            @RequestHeader("Host") String hostName,
            @RequestHeader("Accept") String acceptType,
//            @RequestHeader("Accept-Language") String acceptLang,
            @RequestHeader("Accept-Encoding") String acceptEnc,
//            @RequestHeader("Cache-Control") String cacheCon,
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader("Authorization") String token) {
//        System.out.println("request : " + request);
//        System.out.println("headers : " + headers);
        System.out.println("Host : " + hostName);
        System.out.println("Accept : " + acceptType);
//        System.out.println("Accept Language : " + acceptLang);
        System.out.println("Accept Encoding : " + acceptEnc);
//        System.out.println("Cache-Control : " + cacheCon);
        System.out.println("Cookie : " + cookie);
        System.out.println("Authorization : " + token);

        Claims claim = JwtUtils.getBody(token);
        System.out.println(claim.getSubject());

        return "test";
    }

    @RequestMapping("/test/filter")
    public void test(@RequestHeader HttpHeaders header) {
        System.out.println("/test/filter执行");
        System.out.println(header);
    }

}
