package com.mercury.security;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mercury.dao.StatisticsDao;
import com.mercury.dao.impl.StatisticsDaoImpl;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
		UserDetails ud;
		for (SecurityContext securityContext: lstSecurityContext) {
			ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
			String username = ud.getUsername();
			Timestamp logoutTime = new Timestamp(new Date().getTime());
			System.out.println("Username: " + username + "\tLogoutTime: " + logoutTime);
			StatisticsDao statd = new StatisticsDaoImpl();
			statd.setLogoutTime(username, logoutTime);
		}
	}

}
