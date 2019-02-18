package com.example.demo.customAop;

import java.lang.reflect.Method;

/**
 * 定义一个注解和实体类
 * 向实体类添加注解信息
 */
public class InitTestFactory {

    /**
     * 测试
     * @param args
     */
    public static void main(String args[]){
        InitTest1 test =InitTestFactory.create();
        System.out.println("id==========="+test.getId());
        System.out.println("name========="+test.getName());
    }

    public static InitTest1 create(){
        InitTest1 test1 = new InitTest1();
        // 获取User类中所有的方法（getDeclaredMethods也行）
        Method[] methods = InitTest1.class.getDeclaredMethods();
        try {
            for(Method method: methods){
                // 如果此方法有注解，就把注解里面的数据赋值到 InitTest1 对象
                if (method.isAnnotationPresent(Init.class)){

                    System.out.println("method.getName '"+ method.getName() );

                    Init methodAnno = method.getAnnotation(Init.class);
                    System.out.println(methodAnno.id());
                    System.out.println(methodAnno.value());
                    if(method.getName().toLowerCase().contains("id")) {
                        method.invoke(test1, methodAnno.id());
                    }else if(method.getName().toLowerCase().contains("name")){
                        method.invoke(test1, methodAnno.value());
                    }else{
                        System.out.println("------------------------------");
                    }
//                    Init init = method.getAnnotation(Init.class);
//                    method.invoke(test1, init.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test1;
    }

}
