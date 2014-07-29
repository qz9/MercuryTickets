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
		String hql = "from Card where user_id=" + userid ;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Card getCardByNumber(String cardNum) {
		String hql="from Card where card_number='" + cardNum + "'";
		List<Card> cards = template.find(hql);
		if (cards.size() == 1) {
			return cards.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCardById(Card card, String cardNum) {
		String hql = "from Card where cardNumber='" + cardNum + "'";
		System.out.println(cardNum);
		List<Card> list = template.find(hql);
		if (list.size() == 1) {
			Card card_old = list.get(0);
			card_old.setCardNumber(card.getCardNumber());
			card_old.setCardType(card.getCardType());
			card_old.setExMonth(card.getExMonth());
			card_old.setExYear(card.getExYear());
			card_old.setUser(card.getUser());
			this.update(card_old);
		} else {
			System.out.println("No such card!");
		}
	}
}
