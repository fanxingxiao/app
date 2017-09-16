package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.test;

import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.google.gson.Gson;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.ChinaNews;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.ChinaResponse;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.News;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.util.DateUtils;

public class CrawlerNewsTest {
	@Test
	public void test001() throws Exception{
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient client = builder.build();
		
		
		HttpGet get=new HttpGet("http://rollnews.china.com/interface/rollnews.php?method=getNews&channelid=41&page=1&limit=60&_=1505110710161");
		
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity entity = response.getEntity();
		
		String string = EntityUtils.toString(entity, "utf-8");
		
		ChinaResponse chinaResponse = new Gson().fromJson(string, ChinaResponse.class);
		List<ChinaNews> info = chinaResponse.getInfo();
		for(ChinaNews chinaNews:info){
			News news=new News();
			news.setTitle(chinaNews.getNewtitle());
			news.setUrl(chinaNews.getNewsurl().replace(".html", "_all.html#page_2"));
			news.setpDate(DateUtils.getDate(chinaNews.getNewscdate()));
			news.setiDate(new Date());
			news.setWebSite("military.china.com");
			news.setWebSiteName("中华网");
			test002(news.getUrl(),news);
			System.out.println(news);
		}
	}
	public void test002(String url,News news) throws Exception{
		Document document = Jsoup.connect(url).get();
		Elements select = document.select("#chan_newsDetail");
		news.setContent(select.html());
		Elements select2 = select.select("img");
		if(select2.size()>0){
			String outerHtml = select2.eq(0).outerHtml();
			news.setImgUrl(outerHtml);
		}
		String keywords = document.select("meta[name='keywords']").attr("content");
		String description = document.select("meta[name='description']").attr("content");
		news.setKeywords(keywords);
		news.setDescription(description);
	}
}
