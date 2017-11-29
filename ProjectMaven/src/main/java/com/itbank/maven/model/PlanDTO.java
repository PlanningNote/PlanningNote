package com.itbank.maven.model;

import org.springframework.web.multipart.MultipartFile;

public class PlanDTO {
	private int group_no;
	private String writer;
	private String subject;
	private String day;
	private String pwd;
	private int count;
	private int tag_no_sequence;
	private String country;
	private String city;
	private String thumbnail;
	private int totalprice;
	private String travel_period;
	private String travel_seasion;
	private String travel_theme;
	private int recom;
	private MultipartFile thumbfile;
	private String thumbPath;
	
	public int getTag_no_sequence() {
		return tag_no_sequence;
	}
	public void setTag_no_sequence(int tag_no_sequence) {
		this.tag_no_sequence = tag_no_sequence;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public MultipartFile getThumbfile() {
		return thumbfile;
	}
	public void setThumbfile(MultipartFile thumbfile) {
		this.thumbfile = thumbfile;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getTravel_period() {
		return travel_period;
	}
	public void setTravel_period(String travel_period) {
		this.travel_period = travel_period;
	}
	public String getTravel_seasion() {
		return travel_seasion;
	}
	public void setTravel_seasion(String travel_seasion) {
		this.travel_seasion = travel_seasion;
	}
	public String getTravel_theme() {
		return travel_theme;
	}
	public void setTravel_theme(String travel_theme) {
		this.travel_theme = travel_theme;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	
	

}
