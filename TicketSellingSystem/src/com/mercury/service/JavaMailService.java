package com.mercury.service;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.*;

import com.mercury.beans.User;
public class JavaMailService {
	public void send(User user) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");   
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);   
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");   
        prop.setProperty("mail.smtp.port", "465");   
        prop.setProperty("mail.smtp.socketFactory.port", "465");   
        prop.put("mail.smtp.auth", "true");   
        final String address = "qzhaor@gmail.com";
        final String password = "lckyhvgoqgpcipik";
        String content;
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { 
            	return new PasswordAuthentication(address, password); 		} 
            });
        try {		
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            msg.setSubject("Test from Java Mail"); msg.setSentDate(new Date());
            content = "Dear " + user.getFirstName() + " " + user.getLastName() + 
            		",\nThank you for registering with Mercury Tickets, your account has been created.";
            msg.setText(content); Transport.send(msg);
        } catch (Exception e) { System.out.println(e); }
    }
}
