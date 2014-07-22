package com.mercury.dao;

import java.util.List;

import com.mercury.beans.Station;

public interface StationDao {
	public List<Station> queryAll() ;
	public void save(Station station);
	public void delete(Station station) ;
	public Station find(int sid) ;
	public void update(Station station) ;
}
