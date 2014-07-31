package com.mercury.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.Statistics;
import com.mercury.dao.StatisticsDao;
import com.mercury.dao.impl.StatisticsDaoImpl;

@Controller
public class StatisticsController {
	@RequestMapping(value="/stat", params="adsId", method=RequestMethod.POST)
	public ModelAndView updateStats(@RequestParam("adsId") int id) {
		StatisticsDao statd = new StatisticsDaoImpl();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		statd.updateAdsCount(username, id);
		return new ModelAndView("redirect:/transaction.html");
	}
	
	@RequestMapping(value="/ads_report", method=RequestMethod.GET)
	public ModelAndView showAdsTable() {
		ModelAndView mav = new ModelAndView("ads_report");
		StatisticsDao statd = new StatisticsDaoImpl();
		List<Statistics> list = statd.queryAll();
		mav.addObject("tableContent", generateAdsStatTable(list));
		return mav;
	}
	
	private String generateAdsStatTable(List<Statistics> list) {
		StringBuffer sb = new StringBuffer();
		if (list.size() == 0) {
			return "no content!";
		} else {
			sb.append("<tr><th>ID</th>" +
					"<th>Username</th>" +
					"<th>AdsURL</th>" +
					"<th>Count</th>" +
					"<th>LoginTime</th>" +
					"<th>LogoutTime</th></tr>");
		}
		int i = 0;
		for (Statistics s:list) {
			String loginTime = new SimpleDateFormat("yyyy-MMM-dd HH:mm").format(s.getLoginTime());
			String logoutTime;
			if (s.getLogoutTime() == null) {
				logoutTime = "Not logged out yet";
			} else {
				logoutTime = new SimpleDateFormat("yyyy-MMM-dd HH:mm").format(s.getLogoutTime());
			}
			if (i%3 == 0) {
				sb.append("<tr><td>"+s.getId()+"</td>" +
						"<td rowspan='3'>"+s.getUsername()+"</td>" +
						"<td>"+s.getAds().getAdURL()+"</td>" +
						"<td>"+s.getCount()+"</td>" +
						"<td rowspan='3'>"+loginTime+"</td>" +
						"<td rowspan='3'>"+logoutTime+"</td></tr>");
			} else {
				sb.append("<tr><td>"+s.getId()+"</td>" +

						"<td>"+s.getAds().getAdURL()+"</td>" +
						"<td>"+s.getCount()+"</td></tr>");
			}
			i++;
		}
		return sb.toString();
	}
}
