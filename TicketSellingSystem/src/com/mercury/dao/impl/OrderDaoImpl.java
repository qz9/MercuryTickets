package com.mercury.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.Order;
import com.mercury.dao.OrderDao;

public class OrderDaoImpl implements OrderDao{
	private HibernateTemplate template ;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory); 
	}

	@Override
	public void saveTransaction(Order order) {
		template.save(order) ;
		
	}

	@Override
	public void deleteTransaction(Order order) {
		template.delete(order) ;
	}

	@Override
	public void updateTransaction(Order order) {
		template.update(order) ;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryTransaction(int userid) {
		String hql = "from orders where user_id=userid" ;
		return template.find(hql) ;
	}
}
