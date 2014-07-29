package com.mercury.beans;

import java.sql.Timestamp;

public class OrderInfo {
	private Order order;
	private String fromStr;
	private String toStr;
	
	private boolean returnable;
	
	public OrderInfo() {
		this.returnable = false;
	}
	public OrderInfo(Order order) {
		this.order = order;
		Ticket ticket = order.getTicket();
		this.fromStr = ticket.getFromStation().getName();
		this.toStr = ticket.getToStation().getName();
		this.returnable = false;
	}
	
	public boolean isReturnable() {
		return returnable;
	}
	public void setReturnable(boolean returnable) {
		this.returnable = returnable;
	}
	
	public Order getOrder() {
		return order;
	}
	public String getOrderCode() {
		return order.getOrderCode();
	}
	public Timestamp getOrderTime() {
		return order.getOrderTime();
	}
	public int getTicketNum() {
		return order.getTicketNum();
	}
	public int getId() {
		return order.getId();
	}
	public String getFromStr() {
		return fromStr;
	}
	public String getToStr() {
		return toStr;
	}
	public Timestamp getTicketStartTime() {
		return order.getTicket().getStartTime();
	}
	public Ticket getTicket() {
		return order.getTicket();
	}
}
