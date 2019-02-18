package com.example.demo.customAop;

import java.lang.annotation.*;

/**
 * 自定义注解--可实现权限控制,日志控制
 * https://blog.csdn.net/u013825231/article/details/80468167
 * */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    //因为存在value才可以这样写: @MyLog("测试自定义注解")
    //否则双引号要去掉
    String value();
}
