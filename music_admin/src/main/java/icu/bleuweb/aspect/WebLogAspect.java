package icu.bleuweb.aspect;

import com.google.gson.Gson;
import icu.bleuweb.bean.LogBean;
//import icu.bleuweb.utils.MyUtils;
import icu.bleuweb.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//TODO 我如何用这个包下的两个类，实现注解在一个类上，下面所有的方法实现这 这个切点类。
@Aspect
@Component
//@Profile({"dev", "test"})
@Slf4j
public class WebLogAspect {

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 以自定义 @WebLog 注解为切点
     */
    //加了这个注解的方法就是切点了,然后@Aspect类中的这个@Pointcut方法就是用来指定切点的，所以下面的切点前后都是用的这个方法名
    @Pointcut("@annotation(icu.bleuweb.aspect.WebLog)")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     * 必须是controller包下 且标注了weblog注解的方法
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog() && execution(* icu.bleuweb.controller..*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        log.info(LINE_SEPARATOR);
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        try {
            try {
                Object[] args = joinPoint.getArgs();
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            System.out.println(new Gson().toJson(joinPoint.getArgs()));
            log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }


    //TODO 我这个around after执行顺序有点问题啊  但是有了around 其实其他所有的都不需要了
//    /**
//     * 在切点之后织入
//     *
//     * @throws Throwable
//     */
//    @After("webLog()")
//    public void doAfter() throws Throwable {
//        // 接口结束后换行，方便分割查看
//        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
//        log.info("这里执行顺序不对啊？？？？？？？？？？？？？？？？？");
//    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();

            //保存到mongodb中
            LogBean logBean = new LogBean();
            logBean.setCreateDate(new Date());
            //获取类名
            String classname = proceedingJoinPoint.getTarget().getClass().getSimpleName();
            logBean.setClassName(classname);
            //获取方法名
            String method = proceedingJoinPoint.getSignature().getName();
            logBean.setMethod(method);
            //获取request对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //获取请求参数
            logBean.setReqParam(new Gson().toJson(proceedingJoinPoint.getArgs()));
            //获取controller返回值  响应报文
            if (result != null) {
                logBean.setReqParam(new Gson().toJson(result));
            }
            //获取ip地址是封装好的一个类
            String ip = IPUtils.getUserIP(request);
            logBean.setIp(ip);
            //请求path
            logBean.setUrl(request.getRequestURL().toString());
            //请求方法
            logBean.setHttpMethod(request.getMethod());
            mongoTemplate.save(logBean);

        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("something went wrong");
        } finally {
            // 打印出参
            log.info("Response Args  : {}", new Gson().toJson(result));
            // 执行耗时
            log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);

            log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
            return result;
        }

    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}