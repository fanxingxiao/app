package com.zdjy.bigdata.logistic_warehouse.crawlerNews.china.entity;

import com.google.gson.Gson;

public class ChinaNews {
	private String id;
	private String newschannelid;
	private String chinaname;
	private String newsid;
	private String newtitle;
	private String moretitle;
	private String video_id;
	private String fromstr;
	private String source;
	private String author;
	private String author_type;
	private String frommark;
	private String big_pic;
	private String isnewstitlehref;
	private String newsurl;
	private String newscdate;
	private String newstypeid;
	private String status;
	private String introduction;
	private String shorttitle;
	private String tag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewschannelid() {
		return newschannelid;
	}
	public void setNewschannelid(String newschannelid) {
		this.newschannelid = newschannelid;
	}
	public String getChinaname() {
		return chinaname;
	}
	public void setChinaname(String chinaname) {
		this.chinaname = chinaname;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getNewtitle() {
		return newtitle;
	}
	public void setNewtitle(String newtitle) {
		this.newtitle = newtitle;
	}
	public String getMoretitle() {
		return moretitle;
	}
	public void setMoretitle(String moretitle) {
		this.moretitle = moretitle;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getFromstr() {
		return fromstr;
	}
	public void setFromstr(String fromstr) {
		this.fromstr = fromstr;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor_type() {
		return author_type;
	}
	public void setAuthor_type(String author_type) {
		this.author_type = author_type;
	}
	public String getFrommark() {
		return frommark;
	}
	public void setFrommark(String frommark) {
		this.frommark = frommark;
	}
	public String getBig_pic() {
		return big_pic;
	}
	public void setBig_pic(String big_pic) {
		this.big_pic = big_pic;
	}
	public String getIsnewstitlehref() {
		return isnewstitlehref;
	}
	public void setIsnewstitlehref(String isnewstitlehref) {
		this.isnewstitlehref = isnewstitlehref;
	}
	public String getNewsurl() {
		return newsurl;
	}
	public void setNewsurl(String newsurl) {
		this.newsurl = newsurl;
	}
	public String getNewscdate() {
		return newscdate;
	}
	public void setNewscdate(String newscdate) {
		this.newscdate = newscdate;
	}
	public String getNewstypeid() {
		return newstypeid;
	}
	public void setNewstypeid(String newstypeid) {
		this.newstypeid = newstypeid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getShorttitle() {
		return shorttitle;
	}
	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
