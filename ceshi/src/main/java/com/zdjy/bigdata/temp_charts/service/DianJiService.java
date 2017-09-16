package com.zdjy.bigdata.temp_charts.service;

import java.util.List;

import com.zdjy.bigdata.temp_charts.entity.DianJi;

public interface DianJiService {
	List<DianJi> findByDate(String date);
}
