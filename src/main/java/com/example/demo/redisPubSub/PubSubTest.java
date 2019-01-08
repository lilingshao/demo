package com.example.demo.redisPubSub;

/**
 * 发布者发布消息,订阅者接收消息
 */
public class PubSubTest {
	public static void main(String args[]){
		SubThread subThread = new SubThread();  //订阅者
		subThread.start();

		Publisher publisher = new Publisher();    //发布者
		publisher.start();
	}
}
