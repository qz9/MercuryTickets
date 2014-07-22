package com.mercury.beans;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="station")
public class Station {
	private int id;
	private String name;
	private String state;
	private String city;
	
	public Station() {}
	public Station(String name, String state, String city) {
		this.name = name;
		this.state = state;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + "\tName: " + this.name
				+ "\tState: " + this.state + "\tCity: " + this.city;
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
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="state", nullable=false)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="city", nullable=false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
