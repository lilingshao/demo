package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface IUserService {
	public List<User> getUsers(User user);
    boolean addUser(User record);
    boolean delUser(User user);
}
