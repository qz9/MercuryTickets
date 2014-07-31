package com.mercury.dao.impl;

import java.util.*;
import org.hibernate.*;
import com.mercury.utils.*;
import com.mercury.beans.Statistics;
import com.mercury.beans.Ads;
import com.mercury.dao.StatisticsDao;

import java.sql.Timestamp;

public class StatisticsDaoImpl implements StatisticsDao {

	@SuppressWarnings("unchecked")
	@Override
	public void newStatitcs(String username, Timestamp startTime) {
		Session session = HibernateUtil.currentSession();
		String hql = "from Ads";
		List<Ads> listAds = session.createQuery(hql).list();
		System.out.println(listAds.size());
		for (Ads a:listAds) {
			Transaction tx = session.beginTransaction();
			Statistics stat = new Statistics();
			stat.setAds(a);
			stat.setCount(0);
			stat.setUsername(username);
			stat.setLoginTime(startTime);
			stat.setLoginTime(null);
			session.save(stat);
			tx.commit();
		}
		HibernateUtil.closeSession();
	}

	@Override
	public void updateAdsCount(String username, int AdsId) {
		
	}

	@Override
	public List<Statistics> queryAll() {
		return null;
	}

}
