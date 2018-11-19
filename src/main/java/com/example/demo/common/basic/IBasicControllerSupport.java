package com.example.demo.common.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IBasicControllerSupport {
	public HttpServletRequest getRequest();
	public HttpSession getSession();
	public Map<String, Object> getParameterMap();
	public Object getParameterObject(Class<?> cls);
}
