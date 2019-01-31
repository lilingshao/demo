package com.example.demo.redisQuene;

import com.example.demo.entity.User;
import com.example.demo.redisSecKill.RedisUtil;
import com.example.demo.utils.redis.RedisListUtil;

import java.io.IOException;

/**
 * redis实现消息队列
 */
public class RedisQueneTest {
	public static byte[] redisKey = "key".getBytes();
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加数据
	 * @throws IOException
	 */
	private static void init() throws IOException {
		for(int i=0;i<10;i++){
			System.out.println("---------"+i);
			User user = new User(i,"这是第 "+i+" 个内容");
			RedisListUtil.lpushByte("key",user);
		}
	}

	/**
	 * 取数据
	 * @throws Exception
	 */
	private void pop() throws Exception {
		while (true){
			User msg = null;
			msg = (User) RedisListUtil.rpopByte("key");
			if(msg != null){
				System.out.println(Thread.currentThread().getName()+"===="+msg.getId() + "----" + msg.getName());
			}else{
				break;
			}
		}
	}

	public static void main(String[] args) {
		Runnable runnable1 = new Runnable() {
			@Override public void run() {
				try {
					new RedisQueneTest().pop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Runnable runnable2 = new Runnable() {
			@Override public void run() {
				try {
					new RedisQueneTest().pop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Thread t1 = new Thread(runnable1, "t1");
		Thread t2 = new Thread(runnable2, "t2");
		t1.start();
		t2.start();
	}

}
