package com.zdjy.bigdata.temp_charts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.temp_charts.entity.DianJi;
import com.zdjy.bigdata.temp_charts.service.DianJiService;

@RestController
@RequestMapping("/dianji")
public class Controller {
	@Autowired
	private DianJiService dianjiService;
	@RequestMapping("/findByDate")
	public List<DianJi> findByDate(String date){
		return dianjiService.findByDate(date);
	}
	
}
