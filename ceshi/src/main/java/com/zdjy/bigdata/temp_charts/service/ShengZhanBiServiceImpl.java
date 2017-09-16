package com.zdjy.bigdata.temp_charts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.temp_charts.dao.ShengZhanBiRepository;
import com.zdjy.bigdata.temp_charts.entity.ShengZhanBi;
@Service
public class ShengZhanBiServiceImpl implements ShengZhanBiService {
	@Autowired
	private ShengZhanBiRepository shengRepository;
	@Override
	public List<ShengZhanBi> findByNian(String nian) {
		return shengRepository.findByNian(nian);
	}
}
