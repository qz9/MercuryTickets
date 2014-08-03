package com.mercury.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercury.utils.JdbcUtil;

@WebServlet("/add")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Integer amount = Integer.valueOf(request.getParameter("amount"));
		String sql = "update ticket set max_num = max_num+? where id=?";
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(amount));
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;
		Integer from = Integer.parseInt(request.getParameter("from"));
		Integer to = Integer.parseInt(request.getParameter("to"));
		Integer max = Integer.parseInt(request.getParameter("max"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String start = request.getParameter("start");
		String arrive = request.getParameter("arrive");
		String sql = "select max(id)+1 from ticket";
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql = "insert into ticket values(?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, from);
			ps.setInt(3, to);
			ps.setInt(4, max);
			ps.setDouble(5, price);
			ps.setString(6, start);
			ps.setString(7, arrive);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
