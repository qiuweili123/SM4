package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/7/6.
 */
@Component
@Aspect
public class AopDataType {
    @Pointcut("execution(* com.spring.aop.*.*(..))")
    //  (execution(* com.xxx.*.controller.*.*(..))
    public void postCut() {

    }

    @Pointcut("@annotation(com.spring.aop.Money)")
    public void annotionMoney() {

    }

    @Around("annotionMoney()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("------------------");
        System.out.println(joinPoint);
        return joinPoint.proceed();
    }
}
