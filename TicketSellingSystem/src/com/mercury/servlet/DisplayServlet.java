package com.mercury.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercury.utils.JdbcUtil;


@WebServlet("/display")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DisplayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String[]> tickets = new ArrayList<String[]>();
		HashMap<Integer, Integer> sum = new HashMap<Integer, Integer>();
		String[] ticket;
		String sql = "select ticket.id, f.name, t.name, price, start_time, arrive_time, max_num " +
				"from ticket, station f, station t " +
				"where from_id = f.id and to_id = t.id " +
				"order by ticket.id";
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ticket = new String[7];
				ticket[0] = String.valueOf(rs.getInt(1));
				ticket[1] = rs.getString(2);
				ticket[2] = rs.getString(3);
				ticket[3] = String.valueOf(rs.getDouble(4));
				ticket[4] = rs.getString(5);
				ticket[5] = rs.getString(6);
				ticket[6] = String.valueOf(rs.getInt(7));
				tickets.add(ticket);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql = "select ticket_id, sum(ticket_num) " +
				"from orders " +
				"group by ticket_id";
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sum.put(rs.getInt(1), rs.getInt(2));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String[] t:tickets) {
			Integer sold = sum.get(Integer.parseInt(t[0]));
			if (sold == null) {
				sold = 0;
			}
			t[6] = String.valueOf(Integer.parseInt(t[6])-sold);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<tickets>");
		for (String[] t:tickets) {
			sb.append("<ticket>");
			sb.append("<id>" + t[0] + "</id>");
			sb.append("<from>" + t[1] + "</from>");
			sb.append("<to>" + t[2] + "</to>");
			sb.append("<price>" + t[3] + "</price>");
			sb.append("<start>" + t[4] + "</start>");
			sb.append("<arrive>" + t[5] + "</arrive>");
			sb.append("<left>" + t[6] + "</left>");
			sb.append("</ticket>");
			
		}
		sb.append("</tickets>");
		out.print(sb.toString());
	}

}
