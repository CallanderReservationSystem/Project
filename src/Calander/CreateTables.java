package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Table;

@WebServlet("/CreateTables")
public class CreateTables extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<Table> tables = new ArrayList<Table>();
	Integer eventId;
	String name;
	Integer usersCid = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getParameter("cid"));

		if (request.getParameter("id") != null) {
			eventId = Integer.parseInt(request.getParameter("id"));
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name");
		}
		if (request.getParameter("cid") != null) {
			usersCid = Integer.parseInt(request.getParameter("cid"));
		}
		
		System.out.println("passed title: " + request.getParameter("id"));
		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from tables where eventId=" + eventId + " ";

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			tables.clear();
			while (rs.next()) {

				Integer id = rs.getInt("id");
				Integer eventId = rs.getInt("eventId");
				Integer cid =rs.getInt("cid");
				String eventName = rs.getString("eventName");
				Integer tableAmount = rs.getInt("tableAmount");
				Integer seats = rs.getInt("seatsPerTable");
				
				// Integer uid = rs.getInt("uid");
				tables.add(new Table(id, eventId, cid, eventName, tableAmount, seats));
			}
			
			
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		request.setAttribute("eventId", eventId);
		request.setAttribute("eventName", name);
		request.setAttribute("cid", usersCid);
		request.setAttribute("tables", tables);
		request.getRequestDispatcher("Calendar/CreateTables.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}