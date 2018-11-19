package com.example.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {
	
	/**
	 * @param response
	 * http://localhost:8080/tst
	 */
	@RequestMapping(value="tst",method = { RequestMethod.POST,RequestMethod.GET })
	public String getTest(HttpServletResponse response){
		System.out.println("999999999999");
		return "index";
	}
	
}
