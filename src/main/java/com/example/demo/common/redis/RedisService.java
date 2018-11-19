package com.example.demo.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/1.
 */
@Service
public class RedisService {

	 @Autowired
	 private RedisTemplate<String, Object> redisTemplate;
	 @Resource
	 private ValueOperations<String,Object> valueOperations;
	 @Autowired
	 private ListOperations<String, Object> listOperations;
	 
//---------------------list
	 /**
		 * 列表添加
		 * 
		 * @param k
		 * @param v
		 */
		public void lPush(String k, Object v) {
			listOperations.rightPush(k, v);
		}
	 /**
		 * 列表获取
		 * @param k
		 * @param l
		 * @param l1
		 * @return
		 */
		public List<Object> lRange(String k, long l, long l1) {
			return listOperations.range(k, l, l1);
		}
//---------------------list
//---------------------value
	 /**
	  * 获得数据
	  * @param key
	  * @return
	  */
	 public Object get(String key) {
		 return valueOperations.get(key);
	 }
	 /**
	  * 可添加任意类型的数据--string,List,Map,Bean
	  * @param key
	  * @param value
	  */
	 public void put(String key,Object value) {
		 valueOperations.set(key, value);
	 }
//---------------------value
	    /**
	     * 默认过期时长，单位：秒
	     */
	    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

	    /**
	     * 不设置过期时长
	     */
	    public static final long NOT_EXPIRE = -1;




	    public boolean existsKey(String key) {
	        return redisTemplate.hasKey(key);
	    }

	    /**
	     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
	     *
	     * @param oldKey
	     * @param newKey
	     */
	    public void renameKey(String oldKey, String newKey) {
	        redisTemplate.rename(oldKey, newKey);
	    }

	    /**
	     * newKey不存在时才重命名
	     *
	     * @param oldKey
	     * @param newKey
	     * @return 修改成功返回true
	     */
	    public boolean renameKeyNotExist(String oldKey, String newKey) {
	        return redisTemplate.renameIfAbsent(oldKey, newKey);
	    }

	    /**
	     * 删除key
	     *
	     * @param key
	     */
	    public void deleteKey(String key) {
	        redisTemplate.delete(key);
	    }

	    /**
	     * 删除多个key
	     *
	     * @param keys
	     */
	    public void deleteKey(String... keys) {
	        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
	        redisTemplate.delete(kSet);
	    }

	    /**
	     * 删除Key的集合
	     *
	     * @param keys
	     */
	    public void deleteKey(Collection<String> keys) {
	        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
	        redisTemplate.delete(kSet);
	    }

	    /**
	     * 设置key的生命周期
	     *
	     * @param key
	     * @param time
	     * @param timeUnit
	     */
	    public void expireKey(String key, long time, TimeUnit timeUnit) {
	        redisTemplate.expire(key, time, timeUnit);
	    }

	    /**
	     * 指定key在指定的日期过期
	     *
	     * @param key
	     * @param date
	     */
	    public void expireKeyAt(String key, Date date) {
	        redisTemplate.expireAt(key, date);
	    }

	    /**
	     * 查询key的生命周期
	     *
	     * @param key
	     * @param timeUnit
	     * @return
	     */
	    public long getKeyExpire(String key, TimeUnit timeUnit) {
	        return redisTemplate.getExpire(key, timeUnit);
	    }

	    /**
	     * 将key设置为永久有效
	     *
	     * @param key
	     */
	    public void persistKey(String key) {
	        redisTemplate.persist(key);
	    }


	}