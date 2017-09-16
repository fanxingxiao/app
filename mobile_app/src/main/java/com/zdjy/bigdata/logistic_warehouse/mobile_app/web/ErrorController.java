package com.zdjy.bigdata.logistic_warehouse.mobile_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping({"/400"})
	public String to400(){
		return "400";
	}
	@RequestMapping({"/404"})
	public String to404(){
		return "404";
	}
	@RequestMapping({"/500"})
	public String to500(){
		return "500";
	}
}
