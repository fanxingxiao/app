package com.zdjy.bigdata.logistic_warehouse.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zdjy.bigdata.logistic_warehouse.store.entity.News;

public interface NewsRepository extends JpaRepository<News,Integer>{
	
}
