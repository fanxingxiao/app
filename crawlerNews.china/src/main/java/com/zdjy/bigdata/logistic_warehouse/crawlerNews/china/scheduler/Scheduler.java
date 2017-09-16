package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.crawler.Crawler0;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.News;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.mq.Sender;

@Component
public class Scheduler {
	@Autowired
	private Crawler0 crawler;
	@Autowired
	private Sender sender;
	@Scheduled(cron="0/30 * * * * ?")
	public void schedule(){
		//爬去互联网上的数据
		List<News> crawle = crawler.crawle();
		if(crawle==null||crawle.size()==0)
			return;
		for(News news:crawle){
			sender.send(news.toString());
		}
		System.out.println(crawle.size());
	}
}
