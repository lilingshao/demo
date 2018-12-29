package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.demo.entity.User;

//配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中
@CacheConfig(cacheNames = "users")
public interface UserDao {
	/**
	 * 新增用户--添加缓存
	 */
//	@CachePut(key = "#user.id")
	int insert(User user);

	/**
	 * 获取使用缓存
	 * @param user
	 * @return
	 */
//	@Cacheable
//	@Cacheable(value = "userinfocache",key = "#user.id")
	List<User> getUsers(User user);

	/**
	 * 通过id删除单个用户--删除缓存
	 */
//	@CacheEvict(key = "#id")
	boolean delUser(int id);
}
