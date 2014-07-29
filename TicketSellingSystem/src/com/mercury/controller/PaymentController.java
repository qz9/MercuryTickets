package com.mercury.controller;

import java.util.*;
import com.mercury.service.*;
import com.mercury.dao.*;
import com.mercury.dao.impl.*;
import com.mercury.beans.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	private CardService cs;
	private User private_user;
	
	public CardService getCs() {
		return cs;
	}

	public void setCs(CardService cs) {
		this.cs = cs;
	}
	
	@RequestMapping(params="card_type", method=RequestMethod.POST)
	public ModelAndView updateCard(@RequestParam("card_type") String cardType, 
									@RequestParam("exMonth") String exMonth,
									@RequestParam("exYear") String exYear,
									@RequestParam("cardNum") String cardNum,
									@RequestParam("originalCardNum") String originalCardNum) {
		ModelAndView mav = new ModelAndView();
		if (private_user == null) {
			UserDao ud = new UserDaoImpl();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			private_user = ud.findByName(username);
		}
		Card card = new Card(cardNum, private_user, exMonth, exYear, cardType);
		if (cs.updateCard(card, originalCardNum)) {
			mav.setViewName("redirect:/payment.html?card=updated");
		} else {
			mav.setViewName("redirect:/payment.html?card=fail");
		}
		return mav;
	}
	
	@RequestMapping(params="cardNumDelete", method=RequestMethod.POST)
	public ModelAndView deleteCard(@RequestParam("cardNumDelete") String cardNum) {
		cs.deleteCardByNumber(cardNum);
		ModelAndView mav = new ModelAndView("redirect:/payment.html?card=deleted");
		return mav;
	}

	@RequestMapping(params="cardNumber", method=RequestMethod.POST)
	public ModelAndView newCard(Card card) {
		ModelAndView mav = new ModelAndView();
		if (private_user == null) {
			UserDao ud = new UserDaoImpl();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			private_user = ud.findByName(username);
		}
		Card existed_card = cs.findCardByCardNumber(card.getCardNumber());
		if (existed_card != null) {
			mav.setViewName("redirect:/payment.html?card=fail");
		} else {
			mav.setViewName("redirect:/payment.html?card=success");
			card.setUser(private_user);
			cs.saveCard(card);
		}
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView viewPayment() {
		ModelAndView mav = new ModelAndView();
		UserDao ud = new UserDaoImpl();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = ud.findByName(username);
		if (private_user == null) {
			private_user = user;
		}
		
		mav.addObject("username", private_user.getUsername());
		mav.addObject("email", private_user.getEmail());
		mav.addObject("firstName", private_user.getFirstName());
		mav.addObject("lastName", private_user.getLastName());
		mav.addObject("street", private_user.getStreet());
		mav.addObject("state", private_user.getState());
		mav.addObject("zipCode", private_user.getZip_code());
		
		int id = private_user.getId();
		List<Card> listCards = cs.getCardsByUserId(id);
		
		String strCardsTableHTML = this.generateCardsTable(listCards);
		mav.addObject("cardsTableHTML", strCardsTableHTML);
		mav.addObject("card_entity", new Card());
		mav.setViewName("payment");
		return mav;
	}
	
	public String generateCardsTable(Collection<Card> setCards) {
		String result = new String();
		if (setCards.size() != 0) {
			result += "<tr><th width=\"120\">Card Number</th><th></th><th>EXP Date</th>" +
					"<th>Card Type</th><th width=\"90\">Actions</th></tr>";
		} else {
			result += "<div id=\"no_card_notice\">You don't have any cards</div>";
		}
		int count = 0;
		for (Card card:setCards) {
			count++;
			String exdate;
			if (Integer.parseInt(card.getExMonth().trim()) < 10) {
				exdate = "0" + card.getExMonth().trim() + "-" + card.getExYear();
			} else {
				exdate = card.getExMonth().trim() + "-" + card.getExYear();
			}
			result += "<tr><td id=\"card_number_" + count + "\" class=\"card_number\">" + card.getCardNumber() + "</td>" +
						"<td>" + "<a href=\"javascript:void(0)\" class=\"show_card\" id=\"show_card_" + count + "\">Show Card</a></td>" +
						"<td>" + exdate + "</td>" + "<td>" + card.getCardType() + "</td>" +
						"<td><a href=\"javascript:void(0)\" id=\"update_card_"+count+"\" class=\"update_card_link\">Update</a>" +
						" <a href=\"javascript:void(0)\" id=\"delete_card_"+count+"\" class=\"delete_card_link\">Delete</a></td></tr>";
		}
		return result;
	}
}
