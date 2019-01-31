package com.example.demo.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String args[]){
        Map<String,String> map = new HashMap<>();
        String key = "12345563";
        map.put("key",key);
        System.out.println(map.get("key"));

        try {
            Field hello_field=String.class.getDeclaredField("value");
            System.out.println(hello_field.getName()+"--"+hello_field.toString()+"--"+hello_field.getType());
            hello_field.setAccessible(true);
            char[] value=(char[])hello_field.get(key);
            value[5]='_';
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.print(key);

    }
}
