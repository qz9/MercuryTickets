package com.mercury.service;

import com.mercury.dao.TicketDao;

public class TicketService {
	private TicketDao td ;
	
	public TicketDao getTd(){
		return td ;
	}
	public void setTd(TicketDao td){
		this.td = td ;
	}
	
	/*public TicketInfo process(Ticket ticket){
		return 
	}*/
}