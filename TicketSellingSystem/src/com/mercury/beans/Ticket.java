package com.mercury.beans;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="ticket")
public class Ticket {
	private int id;
	private Station fromStation;
	private Station toStation;
	private int maxNumber;
	private double price;
	private Timestamp startTime;
	private Timestamp arriveTime;
	private Set<Order> orders;
	
	public Ticket() {}
	public Ticket(Station from,
			Station to, int maxNumber, double price,
			Timestamp startTime, Timestamp arriveTime) {
		this.fromStation = from;
		this.toStation = to;
		this.maxNumber = maxNumber;
		this.price = price;
		this.startTime = startTime;
		this.arriveTime = arriveTime;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + "\tfromID: " + this.fromStation.getId() + "\ttoID: " +
				this.toStation.getId() + "\tmaxNumber: " + this.maxNumber + "\tPrice: "
				+ this.price + "\tStartTime: " + this.startTime
				+ "\tArriveTime: " + this.arriveTime;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="from_id", nullable=false)
	public Station getFromStation() {
		return fromStation;
	}
	public void setFromStation(Station fromStation) {
		this.fromStation = fromStation;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="to_id", nullable=false)
	public Station getToStation() {
		return toStation;
	}
	public void setToStation(Station toStation) {
		this.toStation = toStation;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ticket")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@Column(name="max_num")
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	
	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name="start_time")
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="arrive_time")
	public Timestamp getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Timestamp arriveTime) {
		this.arriveTime = arriveTime;
	}
}
