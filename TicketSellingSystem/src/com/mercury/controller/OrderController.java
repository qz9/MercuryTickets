package com.mercury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.Order;
//import com.mercury.beans.OrderInfo;
import com.mercury.service.OrderService;

@Controller
@SessionAttributes
public class OrderController {
	private OrderService os;
	private String viewPage;
	
	public OrderService getOs() {
		return os;
	}
	public void setOs(OrderService os) {
		this.os = os;
	}
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@RequestMapping(value="/orderpage", method=RequestMethod.POST)
	public ModelAndView process(@ModelAttribute("transaction") 
			Order order, BindingResult result) {
		//OrderInfo orderInfo= os.process(order);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPage);
		//mav.addObject("userInfo", orderInfo);
		return mav;
	}	
}

