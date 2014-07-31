package com.mercury.tests;

import com.mercury.beans.*;
import org.hibernate.*;
import com.mercury.dao.*;
import com.mercury.dao.impl.*;
import com.mercury.utils.HibernateUtil;

import java.sql.Timestamp;
import java.util.*;

public class StatisticsDaoImplTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Statistics";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		tx.commit();
		
		StatisticsDao statd = new StatisticsDaoImpl();
		statd.newStatitcs("for_test", new Timestamp(new Date().getTime()));
		
		session = HibernateUtil.currentSession();
		hql = "from Statistics";
		query = session.createQuery(hql);
		List<Statistics> listStats = query.list();
		for (Statistics stat:listStats) {
			System.out.println(stat);
		}
		
		HibernateUtil.closeSession();
	}
}
