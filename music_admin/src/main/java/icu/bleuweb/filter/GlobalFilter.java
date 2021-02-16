package icu.bleuweb.filter;

import icu.bleuweb.error.NoAuthError;
import icu.bleuweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的过滤器
 * 之后需要@configuration类注册到容器中
 * 可不可以直接用@component直接注入到容器中呢？？？
 */
@Slf4j
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //过滤器初始化时执行，虽然我也不知道它什么时候初始化，什么时候销毁
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //主要的过滤器业务代码所在位置
//        System.out.println("过滤器执行了");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println(request.getRequestURI());

//        String token = request.getHeader("Authorization");
//        if (token != null) {
//            //这里可以搞一个service,如果这个用户在数据库中查不到或者token无法解析，抛出invalid token错误
//            //TODO 我现在搞不懂什么叫做token不合法了，难道只要token能解析出来就没问题吗
//            try {
//                log.info("ok hastoken can do anything");
//                JwtUtils.getBody(token).getSubject();
//            } catch (Exception exception) {
//                log.info("valid token");
//            }
//        } else
//            throw new NoAuthError("lackof Auth");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //过滤器销毁时执行
    }
}
