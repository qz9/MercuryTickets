package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.User;
import com.mercury.dao.UserDao;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate template ;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory) ;
	}
	
	@Override
	public User findByName(String name){
		return (User)template.load(User.class, name) ;
	}
	
	@Override
	public void save(User user){
		template.save(user) ;
	}
	
	@Override
	public void update(User user){
		template.update(user) ;
	}
	
	@Override
	public void delete(User user){
		template.delete(user) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll(){
		String hql = "from all_user" ;
		return template.find(hql) ;
	}
}