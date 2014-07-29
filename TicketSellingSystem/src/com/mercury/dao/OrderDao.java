package com.mercury.dao;

import java.util.List;
import com.mercury.beans.Order;
import com.mercury.beans.OrderInfo;

public interface OrderDao {
	public void saveTransaction(Order ts) ;
	public void deleteTransaction(Order ts) ;
	public void updateTransaction(Order ts) ;
	public List<Order> queryTransaction(int userid) ;
	public OrderInfo getOrderById(int orderId);
}
