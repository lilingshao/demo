package com.example.demo.redisPubSub;

import com.example.demo.redisSecKill.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 定义了一个订阅者
 */
public class SubThread extends Thread{

	private final Subscriber subscriber = new Subscriber();
	private final String channel = "mychannel";

	@Override
	public void run(){
		System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getInstance().getJedis();
			jedis.subscribe(subscriber,channel);
		} catch (Exception e) {
			System.out.println(String.format("subsrcibe channel error, %s", e));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
