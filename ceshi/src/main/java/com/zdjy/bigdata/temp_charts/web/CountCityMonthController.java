package com.zdjy.bigdata.temp_charts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.temp_charts.entity.ShengZhanBi;
import com.zdjy.bigdata.temp_charts.service.ShengZhanBiService;

@RestController
@RequestMapping("/ShengZhanBi")
public class CountCityMonthController {
	@Autowired
	private ShengZhanBiService ShengZhanBiService;
	@RequestMapping("/findBySheng")
	public List<ShengZhanBi> findById(String nian){
		return ShengZhanBiService.findByNian(nian);
	}
}
