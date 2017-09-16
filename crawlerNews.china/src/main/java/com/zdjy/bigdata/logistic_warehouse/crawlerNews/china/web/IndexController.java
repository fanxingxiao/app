package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.crawler.Crawler0;

@Controller
public class IndexController {
	@Autowired
	Crawler0 crawler;
	
	@RequestMapping({"","/","/index"})
	public String toIndex(){
		return "index";
	}
	@RequestMapping("/data")
	@ResponseBody
	public int getData(){
		return crawler.getNum();
	}
}
