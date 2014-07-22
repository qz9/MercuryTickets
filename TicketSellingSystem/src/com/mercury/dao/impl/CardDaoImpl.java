package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.Card;
import com.mercury.dao.CardDao;

public class CardDaoImpl implements CardDao {

	private HibernateTemplate template ;
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Card> queryAll(int userid) {
		String hql = "from card where user_id=userid" ;
		return template.find(hql) ;
	}

	@Override
	public void save(Card card) {
		template.save(card) ;
	}

	@Override
	public void delete(Card card) {
		template.delete(card) ;
	}

	@Override
	public void update(Card card) {
		template.update(card) ;
	}

}
