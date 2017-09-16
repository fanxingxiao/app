package com.zdjy.bigdata.temp_charts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DianJi {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String date;
	private String time;
	private Integer dianji;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getDianji() {
		return dianji;
	}
	public void setDianji(Integer dianji) {
		this.dianji = dianji;
	}
}
