package com.example.demo.test;

public class InnerClass {
    private int k=0;
    private static int kk=0;
    public class In1{
        private final static String tt="234";
        public void tt(){
            System.out.println("in1=="+k);
        }
    }
    static class In2{
        private String tt="234";
        public void tt(){
            System.out.println(kk+"---in1=="+kk);
        }
    }
    public void In3( Bird bird){
        System.out.println(bird.sk2+"--"+k);
    }

    public static void  main(String ar[]){
        System.out.println("----------");
        new InnerClass().new In1();

        new InnerClass().In3(new Bird(){

        });

        new InnerClass.In2();
    }
}
abstract class Bird{
    private String sk = "88";
    public String sk2 = "898";
}