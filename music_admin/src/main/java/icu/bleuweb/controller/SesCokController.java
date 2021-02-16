package icu.bleuweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class SesCokController {

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("cookie")
    public void writeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("user", "wuxy");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        cookie = new Cookie("pw", "123");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
    }

    @GetMapping("session")
    public String readSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "test");
        return session.getAttributeNames().toString();
    }
}
