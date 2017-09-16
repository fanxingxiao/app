package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化类
 * @author zdjy
 *
 */
public class DateUtils {
	public static Date getDate(String dateString){
		try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
