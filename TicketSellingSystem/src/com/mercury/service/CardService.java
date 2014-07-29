package com.mercury.service;

import com.mercury.beans.*;
import com.mercury.dao.CardDao;
import java.util.*;

public class CardService {
	private CardDao cd ;

	public CardDao getCd() {
		return cd;
	}
	public void setCd(CardDao cd) {
		this.cd = cd;
	}
	
	public void saveCard(Card card) {
		cd.save(card);
	}
	
	public Card findCardByCardNumber(String cardNum) {
		return cd.getCardByNumber(cardNum);
	}
	
	public void deleteCardByNumber(String cardNum) {
		Card card = cd.getCardByNumber(cardNum);
		cd.delete(card);
	}
	
	public List<Card> getCardsByUserId(int id) {
		return cd.queryAll(id);
	}
	
	public boolean updateCard(Card card, String originCardNum) {
		Card existed = cd.getCardByNumber(card.getCardNumber());
		if (existed != null && (!card.getCardNumber().equals(originCardNum))) {
			return false;
		} else {
			cd.updateCardById(card, originCardNum);
			return true;
		}
	}
}
