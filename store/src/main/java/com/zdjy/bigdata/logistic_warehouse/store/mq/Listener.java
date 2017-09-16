package com.zdjy.bigdata.logistic_warehouse.store.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.zdjy.bigdata.logistic_warehouse.store.entity.News;
import com.zdjy.bigdata.logistic_warehouse.store.service.NewsService;

/**
 * 监听队列
 * 将收到的消息发送给方法
 * @author zdjy
 *
 */


@Configuration
@RabbitListener(queues="xinwen")
public class Listener {
	@Autowired
	private NewsService newsService;
	@RabbitHandler
	public void process(String message){
		News news = new Gson().fromJson(message, News.class);
		System.out.println(news);
		newsService.save(news);
	}
}
