package icu.bleuweb.interceptor;

import icu.bleuweb.api.WriteFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    WriteFile writeFile;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("拦截器被调用");
        log.info("request请求地址path[{}]\n" +
                "uri[{}]\n" +
                "远程主机名[{}]\n" +
                "远程主机[{}]", request.getServletPath(), request.getRequestURI(), request.getRemoteHost(), request.getRemoteAddr());

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间

        System.out.println(request);
        writeFile.witer(sdf.format(date) + "  ---  " +
                String.format("请求路径:%s  远程的ip:%s  端口:%s  请求状态码:%s",
                        request.getServletPath(),
                        request.getRemoteAddr(),
                        request.getRemotePort(),
                        response.getStatus()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
