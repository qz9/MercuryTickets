package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.User;
import com.mercury.dao.UserDao;
import com.mercury.utils.HibernateUtil;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate template ;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByName(String name){
		String hql = "from User where username = '" + name + "'";
		Session session = HibernateUtil.currentSession();
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		if (list.size() != 1) {
			return null;
		} else {
			return list.get(0);
		}
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