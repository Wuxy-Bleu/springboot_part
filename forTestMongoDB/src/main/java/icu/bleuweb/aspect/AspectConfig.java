package icu.bleuweb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectConfig {

        //切入点1：匹配xxx类下的方法名以demo开头、参数类型为int的public方法
        @Pointcut("execution(public * icu.bleuweb.service.DemoService.demo*(int))")
        public void matchCondition() {}

        //切入点2：匹配xxx类下的方法名以demo开头、参数类型为long的public方法
        @Pointcut("execution(public * icu.bleuweb.service.DemoService.demo1*(long))")
        public void matchCondition_() {}

        //前置
        @Before("matchCondition()")
        public void before(JoinPoint joinPoint) {
            System.out.println("我是前置通知!!!");
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            System.out.println(obj);
            //AOP代理类的信息
            joinPoint.getThis();
            //代理的目标对象
            joinPoint.getTarget();
            //用的最多 通知的签名
            Signature signature = joinPoint.getSignature();
            //代理的是哪一个方法
            System.out.println(signature.getName());
            //AOP代理类的名字
            System.out.println(signature.getDeclaringTypeName());
            //AOP代理类的类（class）信息
            System.out.println(signature.getDeclaringType());
        }

        //全局后置
        @After("matchCondition()")
        public void after(){
            System.out.println("after");
        }

        //返回后置
        @AfterReturning("matchCondition()")
        public void afterReturning(){
            System.out.println("afterReturning");
        }

        //抛出后置
        @AfterThrowing("matchCondition()")
        public void afterThrowing(){
            System.out.println("afterThrowing");
        }

        @Around("matchCondition_()")
        public Object around(ProceedingJoinPoint joinPoint) {
            Object result = null;
            System.out.println("before");
            try{
                result = joinPoint.proceed(joinPoint.getArgs());//获取参数
                System.out.println("after");
            } catch (Throwable e) {
                System.out.println("after exception");
                e.printStackTrace();
            } finally {
                System.out.println("finally");
            }
            return result;
        }


}
