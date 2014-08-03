package com.mercury.controller ;

import org.springframework.stereotype.Controller ;
import org.springframework.web.bind.annotation.RequestMethod ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.servlet.ModelAndView ;

import com.mercury.beans.Card;
import com.mercury.beans.User;
import com.mercury.service.JavaMailService;
import com.mercury.service.UserService;

@Controller
public class LoginController{
	private JavaMailService js;
	private UserService us;
	private String viewPage;
	
	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	@RequestMapping(value="/login")
	public String login(){
		return "login" ;
	}
	
	@RequestMapping(value="/register")
	public String register(){
		return "register" ;
	}
	
	@RequestMapping(value="/admin")
	public String admin(){
		return "admin" ;
	}
	
	@RequestMapping(value="/main")
	public String main(){
		return "majorpage" ;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(User user, Card card){
		card.setUser(user);
		if (!card.getCardType().equals("None"))
			user.addCard(card);
		us.saveUser(user);
		js.send(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPage);
		return mav ;
	}

	public JavaMailService getJs() {
		return js;
	}

	public void setJs(JavaMailService js) {
		this.js = js;
	}
}