package com.mercury.dao.impl;

import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

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
		for (Ads a:listAds) {
			Transaction tx = session.beginTransaction();
			Statistics stat = new Statistics();
			stat.setAds(a);
			stat.setCount(0);
			stat.setUsername(username);
			stat.setLoginTime(startTime);
			stat.setLogoutTime(null);
			session.save(stat);
			tx.commit();
		}
		HibernateUtil.closeSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateAdsCount(String username, int adsId) {
		Session session = HibernateUtil.currentSession();
		List<Statistics> list = session.createCriteria(Statistics.class).
				add(Restrictions.eq("username", username)).
				add(Restrictions.isNull("logoutTime")).list();
		if (list.size() != 3) {
			System.out.println("No such user!");
			return;
		} else {
			for (Statistics s: list) {
				if (s.getAds().getId() == adsId) {
					Transaction tx = session.beginTransaction();
					s.setCount(s.getCount() + 1);
					session.saveOrUpdate(s);
					tx.commit();
				}
			}
		}
		HibernateUtil.closeSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Statistics> queryAll() {
		Session session = HibernateUtil.currentSession();
		String hql = "from Statistics order by id";
		List<Statistics> listStat = session.createQuery(hql).list();
		HibernateUtil.closeSession();
		return listStat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setLogoutTime(String username, Timestamp logoutTime) {
		Session session = HibernateUtil.currentSession();
		List<Statistics> listStat = session.createCriteria(Statistics.class).
				add(Restrictions.eq("username", username)).list();
		for (Statistics stat:listStat) {
			Transaction tx = session.beginTransaction();
			stat.setLogoutTime(logoutTime);
			session.saveOrUpdate(stat);
			tx.commit();
		}
		HibernateUtil.closeSession();
	}

}
