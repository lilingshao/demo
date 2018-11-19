package com.example.demo.common.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.common.basic.IBasicControllerSupport;
import com.example.demo.utils.StringUtil;

public class BasicControllerSupport implements IBasicControllerSupport {
	private static Logger logger = Logger.getLogger(BasicControllerSupport.class);

	@Override
	public HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest();
	}

	@Override
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	@Override
	public Map<String, Object> getParameterMap() {
		Map<String, String[]> map = (Map<String, String[]>) getRequest().getParameterMap();
		Map<String, Object> result = new HashMap<String, Object>();
		Set<Entry<String, String[]>> set = map.entrySet();
		StringBuffer params = new StringBuffer("params: ");
		for (Entry<String, String[]> entry : set) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			String value = values[0];
			result.put(key, value);
			params.append(key + "=" + value + " ");
		}
		return result;
	}

	@Override
	public Object getParameterObject(Class<?> cls) {
		Object entity = null;
		try {
			entity = Class.forName(cls.getName()).newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Map<String, String[]> map = (Map<String, String[]>) getRequest().getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		StringBuffer params = new StringBuffer();
		StringBuffer noParams = new StringBuffer();
		for (Entry<String, String[]> entry : set) {
			String key = entry.getKey();
			if(key.contains("inputEl")) continue;
//			logger.debug(key);
			String[] values = entry.getValue();
			String value = values[0];
//			logger.debug(value);
			if(!value.equals("undefined")){
				try {
//					logger.debug(StringUtil.toFirstUpperCase(key));
					Class<?> type = cls.getMethod("get" + StringUtil.toFirstUpperCase(key)).getReturnType();
					Method method = cls.getMethod("set"	+ StringUtil.toFirstUpperCase(key), type);
//					logger.debug(value+"----"+type);
					method.invoke(entity, type.cast(StringUtil.castString(value,type)));
					params.append(key + "=" + value + " ");
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					noParams.append(key + "=" + value + " ");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("params: " + params.toString());
		if (noParams.toString().length() > 0) {
			logger.warn("no params: " + noParams.toString());
		}
		return entity;
	}

	
	
}
