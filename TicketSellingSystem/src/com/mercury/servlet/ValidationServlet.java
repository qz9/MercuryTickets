package com.mercury.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercury.beans.User;
import com.mercury.utils.JdbcUtil;

/**
 * Servlet implementation class ValidationServlet
 */
@WebServlet("/validation")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String sql = "select username from all_user where username=?";
		User user = null;
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (user==null) {
			out.println("false");
		} else {
			out.println("true");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("email");
		String sql = "select email from all_user where email=?";
		User user = null;
		try {
			Connection conn = new JdbcUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (user==null) {
			out.println("false");
		} else {
			out.println("true");
		}
	}

}
