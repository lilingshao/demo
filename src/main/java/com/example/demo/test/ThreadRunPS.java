package com.example.demo.test;


public class ThreadRunPS extends Thread {
    @Override
    public void run() {
//        try {
//            for (int i = 0; i < 50000000; i++) {
//                if (this.interrupted()) {
//                    System.out.println("�Ѿ���ֹͣ״̬�ˣ���Ҫ�˳���");
//                    throw new InterruptedException();
//                }
//                System.out.println(i);
//            }
//            System.out.println("for����");
//        } catch (InterruptedException e) {
//            System.out.println("����run�е�catch��");
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {
//        try {
//            ThreadRunPS myThread = new ThreadRunPS();
//            myThread.start();
//            Thread.sleep(1000);
//            myThread.interrupt();
//            System.out.println("end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
//Java���̳߳������ߡ���ͣ��ֹͣ
class MythreadY extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 500; i++) {
            /**
             * ����ʹ��yield����������ͣ��
             * yield()�����������Ƿ�����ǰ��CPU��Դ�������ø���������ȥռ��CPUִ��ʱ�䡣��������ʱ�䲻ȷ�����п��ܸոշ����������ֻ��CPUʱ��Ƭ��
             * ��ȥ�� ��ʱ: 21ms
             * ȥ��ע��//Thread.yield()��
             * ��ʱ: 12004ms
             */
//            Thread.yield();
            count = count + (i + 1);
            System.out.println(i);
            if(i==10){
//                Thread.currentThread().stop();
                Thread.currentThread().interrupt();
                System.out.println("�Ƿ�ֹͣ1�� " + Thread.interrupted());
                System.out.println("�Ƿ�ֹͣ2�� " + Thread.interrupted());
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("��ʱ: " + (endTime - beginTime) + "ms");
    }

    public static void main(String[] args) {
        MythreadY myThread = new MythreadY();
        myThread.start();
    }
}
