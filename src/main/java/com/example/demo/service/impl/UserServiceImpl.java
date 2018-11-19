package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
    private UserDao userDao;

	@Override
	public boolean addUser(User record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUsers(Map<String, Object> params) {
		return userDao.getUsers(params);
	}

}
