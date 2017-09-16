package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.crawler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.ChinaNews;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.ChinaResponse;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity.News;
import com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.util.DateUtils;
@Component
public class Crawler2 {
	private HttpClientBuilder builder = HttpClientBuilder.create();
	private CloseableHttpClient client = builder.build();
	private Set<String> set=new HashSet<>();
	private int num=0;
	public int getNum() {
		return num;
	}
	public List<News> crawle(){
		try {
			List<News> newses=new ArrayList<>();
			HttpGet get=new HttpGet("http://rollnews.china.com/interface/rollnews.php?method=getNews&channelid=41&page=1&limit=60&_=1505110710161");
			
			CloseableHttpResponse response = client.execute(get);
			
			HttpEntity entity = response.getEntity();
			
			String string = EntityUtils.toString(entity, "utf-8");
			
			ChinaResponse chinaResponse = new Gson().fromJson(string, ChinaResponse.class);
			List<ChinaNews> info = chinaResponse.getInfo();
			for(ChinaNews chinaNews:info){
				//数据去除
				String id = chinaNews.getId();
				if(set.contains(id)){
					//包含，重复
					break;
				}
				set.add(id);
				try {
					News news=new News();
					news.setType(0);
					news.setTitle(chinaNews.getNewtitle());
					news.setUrl(chinaNews.getNewsurl().replace(".html", "_all.html#page_2"));
					news.setpDate(DateUtils.getDate(chinaNews.getNewscdate()));
					news.setiDate(new Date());
					news.setWebSite("military.china.com");
					news.setWebSiteName("中华网");
					test002(news.getUrl(),news);
					newses.add(news);
					num++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return newses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
