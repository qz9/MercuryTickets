package com.mercury.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mercury.dao.*;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.service.OrderService;
import com.mercury.beans.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	private OrderService os;
	
	public OrderService getOs() {
		return os;
	}

	public void setOs(OrderService os) {
		this.os = os;
	}
	
	@RequestMapping(params="orderId", method=RequestMethod.POST)
	public ModelAndView returnTicket(@RequestParam("orderId") int orderId) {
		ModelAndView mav = new ModelAndView();
		OrderInfo returnOrderInfo = os.getOrderInfo(orderId);
		os.addReturnOrder(returnOrderInfo);
		mav.setViewName("redirect:/transaction.html");
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView transaction() {
		ModelAndView mav = new ModelAndView();
		UserDao ud = new UserDaoImpl();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = ud.findByName(username);
		mav.setViewName("transaction");
		
		mav.addObject("username", user.getUsername());
		mav.addObject("email", user.getEmail());
		mav.addObject("firstName", user.getFirstName());
		mav.addObject("lastName", user.getLastName());
		mav.addObject("street", user.getStreet());
		mav.addObject("state", user.getState());
		mav.addObject("zipCode", user.getZip_code());
		List<OrderInfo> listOrders = os.getOrdersByUserId(user.getId());
		Collections.sort(listOrders, new Comparator<OrderInfo>() {
			@Override
			public int compare(OrderInfo order1, OrderInfo order2) {
				if (order1.getId() < order2.getId()) return -1;
				else if (order1.getId() == order2.getId()) return 0;
				else return 1;
			}
		});
		this.setReturnable(listOrders);
		String ordersTableHTML = this.generateOrdersTableHTML(listOrders);
		mav.addObject("ordersTableHTML", ordersTableHTML);
		
		return mav;
	}
	
	public void setReturnable(List<OrderInfo> listOrders) {
		Date timenow = new Date();
		for (int i = 0; i < listOrders.size(); i++) {
			if (listOrders.get(i).getTicketStartTime() == null) {
				listOrders.get(i).setReturnable(false);
				continue;
			}
			if (listOrders.get(i).getTicketNum() > 0
				&& timenow.before(listOrders.get(i).getTicketStartTime())) {
				listOrders.get(i).setReturnable(true);
			} else {
				Ticket ticket = listOrders.get(i).getTicket();
				int j = i;
				while(j > 0) {
					j--;
					if (ticket == listOrders.get(j).getTicket()
						&& listOrders.get(j).getTicketStartTime() != null
						&& listOrders.get(j).getTicketStartTime().after(timenow)
						&& listOrders.get(j).getTicketNum() == -listOrders.get(i).getTicketNum()
						&& listOrders.get(j).isReturnable() != false) {
						listOrders.get(j).setReturnable(false);
						break;
					}
				}
			}
		}
	}
	
	public String generateOrdersTableHTML(List<OrderInfo> listOrders) {
		String result = new String();
		if (listOrders.size() != 0) {
			result += "<tr><th>ID</th>" +
				"<th>From</th><th>To</th><th>Detail</th><th>Order Time</th><th>Train Start Time</th><th>" +
				"Action</th></tr>";
		} else {
			result += "<div id=\"no_order_notice\">You don't have any orders</div>";
		}
		for (OrderInfo orderinfo:listOrders) {
//			OrderInfo orderinfo = new OrderInfo(order);
			String orderTime;
//			Date timenow = new Date();
			if (orderinfo.getOrderTime() != null) {
				Timestamp time = orderinfo.getOrderTime();
				orderTime = new SimpleDateFormat("yyyy-MMM-dd HH:mm").format(time);
			} else {
				orderTime = "";
			}
			String ticketTime;
			if (orderinfo.getTicketStartTime() != null) {
				Timestamp time = orderinfo.getTicketStartTime();
				ticketTime = new SimpleDateFormat("yyyy-MMM-dd HH:mm").format(time);
			} else {
				ticketTime = "";
			}
			String orderDetail;
			if (orderinfo.getTicketNum() > 0) {
				orderDetail = "Buy "+orderinfo.getTicketNum();
			} else {
				orderDetail = "Return "+(-orderinfo.getTicketNum());
			}
			String linkContent;
			if (orderinfo.isReturnable()) {
				linkContent = "<a href='javascript:void(0)' id='return_ticket_"+orderinfo.getId()+"'>Return</a>";
			} else {
				linkContent = "";
			}
			String disabledClass;
			if (orderinfo.isReturnable() == false) {
				disabledClass = " class='unreturnable'";
			} else {
				disabledClass = "";
			}
			result += "<tr"+disabledClass+">" + "<td>" + orderinfo.getId() + "</td>"
					+ "<td style='width:70px'>" + orderinfo.getFromStr() + "</td>"
					+ "<td style='width:70px'>" + orderinfo.getToStr() + "</td>"
					+ "<td>" + orderDetail + "</td>"
					+ "<td>" + orderTime + "</td>"
					+ "<td>" + ticketTime + "</td>"
					+ "<td>" + linkContent + "</td></tr>";
		}
		
		return result;
	}
}
