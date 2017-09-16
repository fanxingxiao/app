package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity;

import java.util.List;

public class ChinaResponse {
	private Integer count;
	private List<ChinaNews> info;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<ChinaNews> getInfo() {
		return info;
	}
	public void setInfo(List<ChinaNews> info) {
		this.info = info;
	}
	
}
