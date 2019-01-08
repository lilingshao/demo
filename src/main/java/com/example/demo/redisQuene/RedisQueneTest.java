package com.example.demo.redisQuene;

import com.example.demo.entity.User;
import com.example.demo.redisSecKill.RedisUtil;

import java.io.IOException;

/**
 * redis实现消息队列
 */
public class RedisQueneTest {
	public static byte[] redisKey = "key".getBytes();
	static {
		try {
			init();
		} catch (IOException e) {
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
			RedisUtil.getInstance().lpush(redisKey,ObjectUtil.object2Bytes(user));
		}
	}

	/**
	 * 取数据
	 * @throws Exception
	 */
	private static void pop() throws Exception {
		while (true){
			byte[] bytes = RedisUtil.getInstance().rpop(redisKey);
			if(bytes!=null && bytes.length>0){
				User msg = (User) ObjectUtil.bytes2Object(bytes);
				if (msg != null) {
					System.out.println(msg.getId() + "----" + msg.getName());
				}
			}else{
				break;
			}
		}
	}

	public static void main(String[] args) {
		try {
			pop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
