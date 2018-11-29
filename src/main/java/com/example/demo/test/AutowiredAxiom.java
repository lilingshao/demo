package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AutowiredAxiom {
    @Autowired
    private CustomerDao customerDao;

    public static void main(String a[]) throws Exception{
        Class clazz = AutowiredAxiom.class;
        AutowiredAxiom tt = new AutowiredAxiom();
        Map<Object,Object> map = new HashMap<>();
        System.out.println("未注入我是空指针=="+tt.customerDao);
        CustomerDao dao2 = new CustomerDao("进去了");
        map.put(CustomerDao.class,dao2);

        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            boolean present = field.isAnnotationPresent(Autowired.class);
            if(present){
                System.out.println(field.getGenericType());
                System.out.println(field.getName());
                if(map.get(field.getGenericType())!=null){
                    field.setAccessible(true);
                    field.set(tt,map.get(field.getGenericType()));
                }
            }
            System.out.println("222=="+tt.customerDao.str);
        }


    }

}
class CustomerDao{
    String str = "uuu";
    CustomerDao(){
    }
    CustomerDao(String tt){
        this.str=tt;
    }
}
