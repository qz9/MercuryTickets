package com.mercury.beans;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="orders")
public class Order {
	private int id;
	private User user;
	private Ticket ticket;
	private String orderCode;
	private int ticketNum;
	private Timestamp orderTime;
	
	
	public Order() {}
	public Order (User user, Ticket ticket, Timestamp orderTime,
			String orderCode, int ticketNum) {
		this.user = user;
		this.ticket = ticket;
		this.orderTime = orderTime;
		this.orderCode = orderCode;
		this.ticketNum = ticketNum;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + "\tUserID: " + this.user.getId() +
				"\tTicketID: " + this.ticket.getId() + "\tOrderTime: "
				+ this.orderTime + "\tOrderCode: " + this.orderCode +
				"\tTicketNumber: " + this.ticketNum;
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
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="ticket_id")
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@Column(name="order_code")
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	@Column(name="ticket_num")
	public int getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}
	
	@Column(name="order_time")
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
}
