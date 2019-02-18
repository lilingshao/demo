package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.customAop.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.basic.impl.BasicControllerSupport;
import com.example.demo.common.redis.RedisService;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.utils.SerializeUtil;

@RestController
@RequestMapping("demo")
public class ReportRestController extends BasicControllerSupport{
	 @Resource
	 private IUserService userService;
	 @Autowired
	 private RedisService redisService;
	/**
	 * redis
	 * @param response
	 */
	@RequestMapping(value="redis",method = { RequestMethod.POST,RequestMethod.GET })
	public void redis(HttpServletResponse response){
		{
			System.out.println("------string--------------");
			redisService.put("mykey","hello,world");
			System.out.println(redisService.existsKey("mykey"));;
			redisService.put("mykey","hello");
			System.out.println("string=="+redisService.get("mykey"));
			System.out.println("------string--------------");
		}
		{
			System.out.println("------list--------------");
			List<String> list = new ArrayList<>();
			list.add("1");
			list.add("2");
			list.add("3");
			list.add("4");
			redisService.put("list", list);
			redisService.put("list", list);
			Object rlist = redisService.get("list");
			List<String> ll = (List<String>) rlist;
			System.out.println("list=="+rlist.toString());
			System.out.println("existsKey=="+redisService.existsKey("list"));
			System.out.println("------list--------------");
		}{
			//存储多条数据
			System.out.println("------bean--------------");
			User u2 = new User();u2.setId(1);u2.setName("2好");
			redisService.put("u2",u2);
			System.out.println("bean=="+((User)redisService.get("u2")).getName());
			System.out.println("------bean--------------");
		}{
			System.out.println("------listOperations--------------");
			redisService.deleteKey("lpList"); //不删除,多次运行会叠加,而上面的只会覆盖,不会叠加
			redisService.lPush("lpList", "mykey");
			redisService.lPush("lpList", "26");
			System.out.println(redisService.lRange("lpList", 0, 5));
			System.out.println("------listOperations--------------");
		}
		
	}
	/**
	 * @param response
	 */
	@RequestMapping(value="getTest",method = { RequestMethod.POST,RequestMethod.GET })
	public String getTest(HttpServletResponse response){
		return "index";
	}

	/**
	 * 获得用户
	 * @return
	 * http://localhost:8070/demo/showUser?id=1
	 * MyLog--注解
	 */
	@MyLog("测试自定义注解")
	@RequestMapping(value = "/showUser",method = {RequestMethod.GET,RequestMethod.POST})
    public List<User> showUser(User user){
		System.out.println(user.getId()==null?null:user.getId());
		if(user.getId() ==null) user.setId(1);
		System.out.println(user.getId());
		List<User> list = this.userService.getUsers(user);
//        String json = JSON.toJSONString(list);
//        return json;
		return list;
    }
	 
}
