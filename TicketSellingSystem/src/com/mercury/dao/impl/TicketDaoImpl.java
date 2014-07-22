package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.Ticket;
import com.mercury.dao.TicketDao;

public class TicketDaoImpl implements TicketDao {
	private HibernateTemplate template ;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findByStation(int fromID, int toId) {
		String hql = "from ticket where from_id=fromID AND to_id=toID" ;
		return template.find(hql) ;
	}

	@Override
	public void save(Ticket ticket) {
		template.save(ticket);
	}

	@Override
	public void update(Ticket ticket) {
		template.update(ticket) ;
	}

	@Override
	public void delete(Ticket ticket) {
		template.delete(ticket) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> queryAll() {
		String hql = "from ticket" ;
		return template.find(hql) ;
	}
}
