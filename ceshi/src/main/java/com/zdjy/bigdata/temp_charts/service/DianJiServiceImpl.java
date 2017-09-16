package com.zdjy.bigdata.temp_charts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.temp_charts.dao.DianJiRepository;
import com.zdjy.bigdata.temp_charts.entity.DianJi;
@Service
public class DianJiServiceImpl implements DianJiService {
	@Autowired
	private DianJiRepository dianjiRepository;
	@Override
	public List<DianJi> findByDate(String date) {
		return dianjiRepository.findByDate(date);
	}
}
