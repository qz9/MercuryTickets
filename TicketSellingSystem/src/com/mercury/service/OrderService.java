package com.mercury.service;

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
}
