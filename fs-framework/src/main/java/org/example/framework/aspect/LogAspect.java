package org.example.framework.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.framework.annotation.SystemLog;
import org.example.framework.domain.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(org.example.framework.annotation.SystemLog)")
    public void point(){}

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            handleBefore(joinPoint);
            Object proceed = joinPoint.proceed();
            handleAfter(proceed);
            return proceed;
        } finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }

    }

    private void handleAfter(Object proceed) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(proceed));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) throws Throwable {

        /**
         * 从请求中获取url
         */
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();


        /**
         * 从注解获取方法上的注解信息
         */
        SystemLog systemLog = getBusinessName(joinPoint);
        String businessName = systemLog.BusinessName();

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", businessName);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),(((MethodSignature)joinPoint.getSignature()).getName()));
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 获取方法上的注解信息
     * @param joinPoint
     * @return
     */
    private SystemLog getBusinessName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemLog annotation = signature.getMethod().getAnnotation(SystemLog.class);
        return annotation;
    }


}
