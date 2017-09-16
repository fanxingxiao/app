package com.zdjy.bigdata.logistic_warehouse.mobile_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping({"","/","/index"})
	public String toIndex(){
		return "index";
	}
	@RequestMapping({"/register"})
	public String toRegister(){
		return "register";
	}
	@RequestMapping({"/login"})
	public String toLogin(){
		return "login";
	}
	@RequestMapping({"/news"})
	public String toNews(Integer id){
		return "article";
	}
}
