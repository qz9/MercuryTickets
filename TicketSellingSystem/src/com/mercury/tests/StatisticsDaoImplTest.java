package com.mercury.tests;

import com.mercury.beans.*;
import org.hibernate.*;
import com.mercury.dao.*;
import com.mercury.dao.impl.*;
import com.mercury.utils.HibernateUtil;

import java.sql.Timestamp;
import java.util.*;

public class StatisticsDaoImplTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Statistics";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		tx.commit();
		
		StatisticsDao statd = new StatisticsDaoImpl();
		statd.newStatitcs("for_test", new Timestamp(new Date().getTime()));
		
		statd.updateAdsCount("for_test", 2);
		statd.updateAdsCount("for_test", 2);
		statd.updateAdsCount("for_test", 2);
		
		statd.updateAdsCount("for_test", 1);
		
		session = HibernateUtil.currentSession();
		List<Statistics> listStat = statd.queryAll();
		for (Statistics s: listStat) {
			System.out.println(s + " Ads: " + s.getAds().toString());
		}
		
		HibernateUtil.closeSession();
	}
}
