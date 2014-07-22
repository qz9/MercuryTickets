package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.Station;
import com.mercury.dao.StationDao;

public class StationDaoImpl implements StationDao {
	private HibernateTemplate template ;
	public void setSessionFactory(SessionFactory sessionFactory){
		template = new HibernateTemplate(sessionFactory) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Station> queryAll() {
		String hql = " from station" ;
		return template.find(hql) ;
	}

	@Override
	public void save(Station station) {
		template.save(station) ;
	}

	@Override
	public void delete(Station station) {
		template.delete(station) ;
	}

	@Override
	public Station find(int sid) {
		String hql = " from station where id=sid";
		return (Station) template.find(hql) ;
	}

	@Override
	public void update(Station station) {
		template.update(station) ;
	}

}
