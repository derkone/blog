package com.zzeng.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DERK
 * @Version 1.0
 */

//拦截所有带controller注解的控制器
@ControllerAdvice
public class ControllerExceptionHandler {

    //创建信息对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //该注解作用是让该方法可以做异常处理
    @ExceptionHandler(Exception.class)
    //返回类型是页面+后台输出到前端的信息
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //记录异常信息
        logger.error("Request URL: {}, Exception : {}",request.getRequestURI(),e);

        //判断是否制定了状态码
        if((AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)) != null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        //添加请求的url路径
        mv.addObject("url",request.getRequestURL());
        //添加异常
        mv.addObject("exception",e);
        //设置报错后查找的页面位置
        mv.setViewName("error/error");
        return mv;
    }
}
