package com.zdjy.bigdata.temp_charts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zdjy.bigdata.temp_charts.entity.DianJi;

public interface DianJiRepository extends JpaRepository<DianJi,Integer>{

	List<DianJi> findByDate(String date);

}
