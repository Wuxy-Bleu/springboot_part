package icu.bleuweb.interceptor;

import icu.bleuweb.error.LoginError;
import icu.bleuweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.tsp.TSPUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        log.info("查看cookie是否存在登录信息");
//        Cookie[] cookies = request.getCookies();
//        boolean isLogin = false;
//        for (Cookie tmp : cookies) {
//
//            System.out.println(tmp.getName());
//            System.out.println(tmp.getValue());
////            这里逻辑实现错误了。
//            if (tmp.getName() == "wuxy" && tmp.getValue() == "123") {
//
//                isLogin = true;
//            } else
//                continue;
//        }
//        if (isLogin == true)
//            return true;
//        else {
//            throw new LoginError("请登录再访问");
//        }

        return true;

    }
}
