package icu.bleuweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "SecondFilter", urlPatterns = {"/test/*"})
public class SecondFilter implements Filter {

    //这个过滤器咋不起作用了呢？？
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("/test  http请求被拦截");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
