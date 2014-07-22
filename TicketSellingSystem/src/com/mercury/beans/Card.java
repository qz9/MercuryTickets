package com.mercury.beans;

import javax.persistence.*;

@Entity
@Table(name="card")
public class Card {
	// private int id;
	private String cardNumber;
	private User user;
	private String exMonth;
	private String exYear;
	private String cardType;
	
	public Card() {}
	public Card(String cardNumber, User user, String exMonth,
			String exYear, String cardType) {
		this.cardNumber = cardNumber;
		this.user = user;
		this.exMonth = exMonth;
		this.exYear = exYear;
		this.cardType = cardType;
	}
	
	@Override
	public String toString() {
		return "CardNumber: " + this.cardNumber + "\tUserID: " + this.user.getId()
				+ "\tExMonth: " + this.exMonth + "\tExYear: "
				+ this.exYear + "\tCardType: " + this.cardType;
	}
	
	@Id
	@Column(name="card_number", unique=true, nullable=false)
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="ex_month", length=2)
	public String getExMonth() {
		return exMonth;
	}
	public void setExMonth(String exMonth) {
		this.exMonth = exMonth;
	}
	
	@Column(name="ex_year", length=4)
	public String getExYear() {
		return exYear;
	}
	public void setExYear(String exYear) {
		this.exYear = exYear;
	}
	
	@Column(name="card_type", length=20)
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
