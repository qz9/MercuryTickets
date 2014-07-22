package com.mercury.dao;

import com.mercury.beans.Card ;
import java.util.* ;
public interface CardDao {
	public List<Card> queryAll(int userid) ;
	public void save(Card card) ;
	public void delete(Card card) ;
	public void update(Card card) ;
}
