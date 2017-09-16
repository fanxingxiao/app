package com.zdjy.bigdata.temp_charts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zdjy.bigdata.temp_charts.entity.ShengZhanBi;

public interface ShengZhanBiRepository extends JpaRepository<ShengZhanBi,Integer>{

	List<ShengZhanBi> findByNian(String nian);

}
