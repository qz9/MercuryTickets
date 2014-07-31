package com.mercury.tests;

import java.util.List;

import org.hibernate.*;
import com.mercury.beans.*;
import com.mercury.utils.HibernateUtil;

public class HibernateTest02 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Ads";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.save(new Ads("http://www.amtrak.com/"));
		session.save(new Ads("http://www.njtransit.com/"));
		session.save(new Ads("http://www.metrolinktrains.com/"));
		tx.commit();
		hql = "from Ads";
		query = session.createQuery(hql);
		List<Ads> listAds = query.list();
		for (Ads a:listAds) {
			System.out.println(a);
		}
	}
}
