package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.News;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.util.DateUtils;
@Component
public class Crawler0 {
	private Set<String> set=new HashSet<>();
	private int num=0;
	public int getNum() {
		return num;
	}
	public List<News> crawle(){
		try {
			List<News> newses=new ArrayList<>();
			for(int s=1;s<3;s++){
				String wangzhi ="http://news.67.com/zixun/index_"+s+".html";
				if(s==1){
					wangzhi.replace("_1.html", ".html");
				}else{
				Document document = Jsoup.connect(wangzhi).get();
				Elements select = document.select(".characterList");
		for(int i =0;i<select.size();i++){
			Elements eq = select.eq(i);
			//数据去除
			if(set.contains(eq.select("a").attr("href"))){
//				包含，重复
				break;
			}
			set.add(eq.select("a").attr("href"));
			try {
			News news=new News();
			String url = eq.select("a").attr("href");
			String imgUrl = eq.select("a img").outerHtml();
			String title = eq.select("h3 a").text();
			news.setType(1);
			news.setTitle(title);
			news.setUrl(url);
			news.setImgUrl(imgUrl);
			news.setiDate(new Date());
			news.setWebSite("news.67.com.com");
			news.setWebSiteName("中国娱乐网");
			Jsoups(news.getUrl(),news);
			newses.add(news);
			num++;
	} catch (Exception e) {
		e.printStackTrace();
	}	
		}
		}
				}
		return newses;
		} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	public void Jsoups(String url,News news) throws Exception{
		String string ="";
		Document document = Jsoup.connect(url).get();
		String keywords = document.select("meta[name='keywords']").attr("content");
		String description = document.select("meta[name='description']").attr("content");
		String string1="<p>"+document.select(".artLeft .artCon .daodu").text()+"</p>";
		Elements select2 =document.select(".artLeft .artCon #pageContent p");
		for(int i =0;i<select2.size();i++){
			string+=select2.eq(i).outerHtml();
		}
		String time =document.select(".artCon .artInfo #pubtime_baidu").text();
		String shijian=time.substring(3,19)+":00";
		if (keywords.equals("")){
			news.setKeywords(description);
			}else{
				news.setKeywords(keywords);
			}
		System.out.println(news.getKeywords()+"\n");
		news.setDescription(description);
		news.setContent(string1+string);
		news.setpDate(DateUtils.getDate(shijian));
	}
}