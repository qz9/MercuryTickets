package com.mercury.dao;

import java.util.List;
import com.mercury.beans.Order;

public interface OrderDao {
	public void saveTransaction(Order ts) ;
	public void deleteTransaction(Order ts) ;
	public void updateTransaction(Order ts) ;
	public List<Order> queryTransaction(int userid) ;
}
