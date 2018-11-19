package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * servlet3新特性:
 * 		1. 添加若干注解,不再需要web.xml文件了
 * 		2. 异步处理支持, Servlet 线程不再需要一直阻塞
 * 我们需要类似于web.xml的配置方式来启动spring上下文了，
 *在Application类的同级添加一个SpringBootStartApplication类
 * @author lilei
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }
}