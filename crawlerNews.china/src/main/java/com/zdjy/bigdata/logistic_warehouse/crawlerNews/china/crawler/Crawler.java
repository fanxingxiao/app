
package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
public class Crawler {
	private Set<String> set=new HashSet<>();
	private int num=0;
	public int getNum() {
		return num;
	}
	public List<News> crawle(){
		try {
			List<News> newses=new ArrayList<>();
		for(int s=1;s<3;s++){
		Document document = Jsoup.connect("http://www.chinaiiss.com/list/21/"+s+".html").get();
//		BufferedWriter writer = new BufferedWriter(new FileWriter("E:/test/pachong/"+s+".txt"));
		Elements select = document.select(".linkNews li");
		for(int i =0;i<select.size();i++){
			Elements eq = select.eq(i);
			//数据去除
			if(set.contains(eq.select("a").attr("href"))){
				//包含，重复
				break;
			}
			set.add(eq.select("a").attr("href"));
			try {
			News news=new News();
			String time=eq.select("li span").text();
			String title = eq.select("a").text();
			String url = eq.select("a").attr("href");
			news.setType(0);
			news.setTitle(title);
			news.setUrl(url);
			news.setpDate(DateUtils.getDate(time));
			news.setiDate(new Date());
			news.setWebSite("chinaiiss.com");
			news.setWebSiteName("战略网");
			Jsoups(news.getUrl(),news);
			newses.add(news);
			System.out.println(news);
//			writer.write(news.getContent()+"\n"+news.getDescription()+"\n"+news.getImgUrl()+"\n"+news.getKeywords()+"\n"+news.getTitle()+"\n"+news.getUrl()+"\n"+news.getWebSite()+"\n"+news.getWebSiteName()+"\n"+news.getiDate()+"\n"+news.getpDate()+"\n");
//			writer.flush();
			num++;
	} catch (Exception e) {
		e.printStackTrace();
	}	
		}
//		writer.close();
		}
		return newses;
		} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	public void Jsoups(String url,News news) throws Exception{
		String string ="";
		int a=0;
		String outerHtml = null;
		while(true){
			a+=1;
			String str = url.replace(".html", "_"+a+".html");
			Document document = Jsoup.connect(str).get();
			if(document.select(".right_text h2").text().equals("您刚才访问页面没有找到")){
				Document document1 = Jsoup.connect(url).get();
				Elements select = document1.select(".left_text_p");
				Elements select2 = select.select("p");
				for(int i =0;i<select2.size();i++){
					string+=select2.eq(i).outerHtml();
				}
				Elements select1 = select2.select("img");
				if(select1.size()>0){
					outerHtml = select1.eq(0).outerHtml();
				}
				break;
			}
			Elements select = document.select(".left_text_p");
			Elements select2 = select.select("p");
			for(int i =0;i<select2.size();i++){
				string+=select2.eq(i).outerHtml();
			}
		}
		Document document = Jsoup.connect(url).get();
		String keywords = document.select("meta[name='keywords']").attr("content");
		String description = document.select("meta[name='description']").attr("content");
		if(keywords=="")
			keywords = document.select("meta[name='subject']").attr("content");
		news.setKeywords(keywords);
		news.setDescription(description);
		news.setContent(string);
		news.setImgUrl(outerHtml);
	}
}
