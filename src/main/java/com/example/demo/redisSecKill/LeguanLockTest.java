package com.example.demo.redisSecKill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis乐观锁实例
 * @author linbingwen
 *
 */
public class LeguanLockTest {

	public static void main(String[] args) throws InterruptedException {
		 long starTime=System.currentTimeMillis();

		 initPrduct();
		 initClient();
		 printResult();

		long endTime=System.currentTimeMillis();
		long Time=endTime-starTime;
		System.out.println("程序运行时间： "+Time+"ms");

	}

	/**
	 * 输出结果
	 */
	public static void printResult() {
		Jedis jedis = RedisUtil.getInstance().getJedis();
		Set<String> set = jedis.smembers("clientList");

		int i = 1;
		for (String value : set) {
			System.out.println("第" + i++ + "个抢到商品，"+value + " ");
		}

		RedisUtil.returnResource(jedis);
	}

	/*
	 * 初始化顾客开始抢商品
	 */
	public static void initClient() {
		/**
		 * 各个线程池的优劣
		 * newCachedThreadPool: 速度最慢,但是10000个里面可以随机
		 * newSingleThreadExecutor 单线程执行,顺序执行,只会执行前1000个取到商品,打印结果不是顺序,因为redis存储不是link顺序存储
		 * newFixedThreadPool固定并发数执行,根据大小,很可能是前1000个取到商品
		 */
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(50);
//		ExecutorService cachedThreadPool = Executors.newSingleThreadExecutor ();
//		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		int clientNum = 10000;// 模拟客户数目
		for (int i = 0; i < clientNum; i++) {
			cachedThreadPool.execute(new ClientThread(i));
		}

		cachedThreadPool.shutdown();

		while(true){
	            if(cachedThreadPool.isTerminated()){
	                System.out.println("所有的线程都结束了！");
	                break;
	            }
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	}

	/**
	 * 初始化商品个数
	 */
	public static void initPrduct() {
		int prdNum = 1000;// 商品个数
		String key = "prdNum";
		String clientList = "clientList";// 抢购到商品的顾客列表
		Jedis jedis = RedisUtil.getInstance().getJedis();

		if (jedis.exists(key)) {
			jedis.del(key);
		}

		if (jedis.exists(clientList)) {
			jedis.del(clientList);
		}

		jedis.set(key, String.valueOf(prdNum));// 初始化
		RedisUtil.returnResource(jedis);
	}

}

/**
 * 顾客线程
 *
 * @author linbingwen
 *
 */
class ClientThread implements Runnable {
	Jedis jedis = null;
	String key = "prdNum";// 商品主键
	String clientList = "clientList";//// 抢购到商品的顾客列表主键
	String clientName;

	public ClientThread(int num) {
		clientName = "编号=" + num;
	}

	public void run() {
//		try {
//			Thread.sleep((int)(Math.random()*5000));// 随机睡眠一下
//		} catch (InterruptedException e1) {
//		}
		while (true) {
			System.out.println("顾客:" + clientName + "开始抢商品");
			jedis = RedisUtil.getInstance().getJedis();
			try {
				jedis.watch(key);
				int prdNum = Integer.parseInt(jedis.get(key));// 当前商品个数
				if (prdNum > 0) {
					Transaction transaction = jedis.multi();
					transaction.set(key, String.valueOf(prdNum - 1));
					List<Object> result = transaction.exec();
					if (result == null || result.isEmpty()) {
						System.out.println("悲剧了，顾客:" + clientName + "没有抢到商品");// 可能是watch-key被外部修改，或者是数据操作被驳回
					} else {
						jedis.sadd(clientList, clientName);// 抢到商品记录一下
						System.out.println("好高兴，顾客:" + clientName + "抢到商品");
						break;
					}
				} else {
					System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jedis.unwatch();
				RedisUtil.returnResource(jedis);
			}

		}
	}

}