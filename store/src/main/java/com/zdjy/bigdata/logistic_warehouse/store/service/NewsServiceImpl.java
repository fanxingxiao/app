package com.zdjy.bigdata.logistic_warehouse.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.logistic_warehouse.store.entity.News;
import com.zdjy.bigdata.logistic_warehouse.store.repository.NewsRepository;
@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;
	@Override
	public int save(News news) {
		News save = newsRepository.save(news);
		if(save!=null)
			return 1;
		return 0;
	}

}
