package com.example.demo.redisPubSub;

import redis.clients.jedis.JedisPubSub;

/**
 * 建立一个订阅者
 */
public class Subscriber extends JedisPubSub {
	public Subscriber(){}
	//收到消息会调用
	public void onMessage(String channel,String message){
		System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
	}
	//订阅了频道会调用
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
				channel, subscribedChannels));
	}
	//取消订阅 会调用
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
				channel, subscribedChannels));

	}

}
