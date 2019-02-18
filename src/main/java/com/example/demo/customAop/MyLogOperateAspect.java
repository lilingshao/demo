package com.example.demo.customAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyLogOperateAspect {

    @Pointcut("@annotation(com.example.demo.customAop.MyLog)")
    public void annotationPointCut(){}

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        System.out.print("打印："+annotation.value()+" 开始前");
    }
    @Around("annotationPointCut()")
    public Object advice(ProceedingJoinPoint joinPoint){
        System.out.println("通知之开始");
        Object retmsg=null;
        try {
            //执行目标方法,权限判断中,可用来判断是否有权限执行
            System.out.println("执行目标方法,权限判断中,可用来判断是否有权限执行");
            retmsg = joinPoint.proceed();
            System.err.println("++++++++"+retmsg);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("通知之结束");
        return retmsg;
    }
    @After("annotationPointCut()")
    public void after() {
        System.out.println("after方法执行后");
    }

}
