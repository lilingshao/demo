package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@Slf4j
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	public UserDao userDao;
	@Autowired
	private RedisTemplate redisTemplate;//实现redis缓存

	@Override
	public boolean addUser(User record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delUser(User user) {
		String key = "userids_"+user.getId();
		boolean hasKey = redisTemplate.hasKey(key);
		if(hasKey){
			redisTemplate.delete(key);
		}
		return false;
	}

	/**
	 * redis缓存使用---dao里面也使用了缓存
	 * dao中全是注解的使用,和本方式没关系
	 * @param user
	 * @return
	 */
	@Override
	public List<User> getUsers(User user) {
		String key = "userids_"+user.getId();
		ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
		boolean hasKey = redisTemplate.hasKey(key);
		log.info("Key=="+key);
		log.info("hasKey=="+hasKey);
		if(hasKey) return operations.get(key);
		List<User> list = userDao.getUsers(user);
		operations.set(key,list);
		return list;
	}

}
