package com.mercury.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mercury.beans.Order;
import com.mercury.beans.OrderInfo;
import com.mercury.dao.OrderDao;

public class OrderService {
	private OrderDao od ;

	public OrderDao getOd() {
		return od;
	}

	public void setOd(OrderDao od) {
		this.od = od;
	}
	public OrderInfo process(Order t){
		OrderInfo oi = new OrderInfo() ;
		return oi ;
	}
	
	public List<OrderInfo> getOrdersByUserId(int id) {
		List<Order> list = od.queryTransaction(id);
		List<OrderInfo> listOrderInfo = new ArrayList<OrderInfo>();
		for (Order o:list) {
			listOrderInfo.add(new OrderInfo(o));
		}
		return listOrderInfo;
	}
	
	public OrderInfo getOrderInfo(int orderId) {
		return od.getOrderById(orderId);
	}
	
	public void addReturnOrder(OrderInfo orderinfo) {
		Order order = new Order();
		order.setOrderTime(new Timestamp(new Date().getTime()));
		order.setUser(orderinfo.getOrder().getUser());
		order.setOrderCode("RETURNED");
		order.setTicket(orderinfo.getTicket());
		order.setTicketNum(-orderinfo.getTicketNum());
		od.saveTransaction(order);
	}
}
