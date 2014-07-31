package com.mercury.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="statistics")
public class Statistics {
	private int id;
	private String username;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private Ads ads;
	private int count;
	
	public Statistics() {}
	public Statistics(int id, String username, Timestamp loginTime, Timestamp logoutTime, Ads ads) {
		this.id = id;
		this.username = username;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.ads = ads;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\tUserName: " + username
				+ "\tLoginTime: " + loginTime + "\tLogoutTime: " + logoutTime;
	}
	
	@Id
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="login_time")
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	
	@Column(name="logout_time")
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	public Ads getAds() {
		return ads;
	}
	public void setAds(Ads ads) {
		this.ads = ads;
	}
	
	@Column(name="count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
