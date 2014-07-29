package com.mercury.tests;

import java.util.List;

import com.mercury.beans.*;
import com.mercury.utils.HibernateUtil;

import org.hibernate.*;

public class HibernateTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		String hql = "from User where username='user1'";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		System.out.println(list.get(0));
	}
}
