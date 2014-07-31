package com.mercury.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ads")
public class Ads {
	private int id;
	private String adURL;
	
	public Ads() {}
	public Ads(String adURL) {
		this.adURL = adURL;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\tURL: " + adURL;
	}
	
	@Id
	@GenericGenerator(name="kaugen", strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ad_url")
	public String getAdURL() {
		return adURL;
	}
	public void setAdURL(String adURL) {
		this.adURL = adURL;
	}
}
