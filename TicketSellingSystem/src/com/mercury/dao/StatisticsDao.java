package com.mercury.dao;

import java.sql.Timestamp;
import java.util.*;
import com.mercury.beans.Statistics;

public interface StatisticsDao {
	public void newStatitcs(String username, Timestamp startTime);
	public void updateAdsCount(String username, int AdsId);
	public void setLogoutTime(String username, Timestamp logoutTime);
	public List<Statistics> queryAll();
}
