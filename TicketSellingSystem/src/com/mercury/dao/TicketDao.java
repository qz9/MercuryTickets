package com.mercury.dao;

import java.util.List;

import com.mercury.beans.Ticket;

public interface TicketDao {
	public List<Ticket> findByStation(int fromID, int toId) ;
	public void save(Ticket ticket) ;
	public void update(Ticket ticket) ;
	public void delete(Ticket ticket) ;
	public List<Ticket> queryAll() ;
}
