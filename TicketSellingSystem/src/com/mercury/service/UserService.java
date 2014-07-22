package com.mercury.service;

import com.mercury.beans.User;
import com.mercury.beans.UserInfo;
import com.mercury.dao.UserDao;

public class UserService {
	private UserDao ud ;
	
	
	public UserDao getUd(){
		return ud ;
	}
	public void setUd(UserDao ud){
		this.ud = ud ;
	}
	public UserInfo saveUser(User user){
		ud.save(user) ; //insert a user into database
		
		UserInfo userInfo = new UserInfo() ;
		
		return userInfo ;
	}
	
}
