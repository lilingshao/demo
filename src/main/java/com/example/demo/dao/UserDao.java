package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.example.demo.entity.User;
@CacheConfig(cacheNames = "users")
public interface UserDao {
	 int insert(Map<String,Object> params);
	 @Cacheable
	 List<User> getUsers(Map<String,Object> params);
}
