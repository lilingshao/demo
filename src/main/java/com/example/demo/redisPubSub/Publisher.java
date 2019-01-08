package com.example.demo.redisPubSub;

import com.example.demo.redisSecKill.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 创建发布者
 */
public class Publisher extends Thread {

	@Override
	public void run(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Jedis jedis = RedisUtil.getInstance().getJedis();
		while (true){
			String line=null;
			try {
				line = reader.readLine();
				if(!"quit".equals(line)){
					jedis.publish("mychannel",line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
