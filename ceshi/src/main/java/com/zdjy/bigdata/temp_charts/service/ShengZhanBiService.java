package com.zdjy.bigdata.temp_charts.service;

import java.util.List;

import com.zdjy.bigdata.temp_charts.entity.ShengZhanBi;

public interface ShengZhanBiService {
	List<ShengZhanBi> findByNian(String nian);
}
