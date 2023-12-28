package com.zzeng.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author DERK
 * @Version 1.0
 */

//开启AOP切面操作
@Aspect
//开启成分扫描
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义切面
    //拦截web下所有的类的所有方法
    @Pointcut("execution(* com.zzeng.web.*.*(..))")
    public void log() {
    }

    //该方法在log切面前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);


        logger.info("Request : {}",requestLog);
    }

    @After("log()")
    public void doAfter() {
//        logger.info("----------------doAfter--------------");
    }

    //方法执行完返回的时候进行拦截
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result : {}", result);
    }

    private class RequestLog{
        private String url;
        private String id;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String id, String classMethod, Object[] args) {
            this.url = url;
            this.id = id;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", id='" + id + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
