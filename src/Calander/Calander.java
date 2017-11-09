package Calander;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.CalendarEventModel;

@WebServlet("/Calander")
public class Calander extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<CalendarEventModel> events = new ArrayList<CalendarEventModel>();
	Integer Id;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ArrayList<CalanderModel> calander = new ArrayList<CalanderModel>();
		Integer id = null;
		Integer userId = null;
		String calName = null;
		String eventCount = null;
		Integer calId;
		String name = (String) request.getSession().getAttribute("Username");
		System.out.println("name: " + name);
		Integer uid = (Integer) request.getSession().getAttribute("ssuid");
		System.out.println("id: " + uid);
		
		String calIdString = request.getParameter("cid");
		System.out.println(calIdString);
		if (calIdString != null) {
			calId = Integer.parseInt(calIdString);
			Id = calId;
		}
		else {
			Id = (Integer) request.getSession().getAttribute("cid");
		}
	
		
		System.out.println("new cal id: " + Id);

		System.out.println("my id: " + Id);

		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from calendar where id = " + Id + "";

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("id");
				userId = rs.getInt("uid");
				calName = rs.getString("cal_name");
				eventCount = rs.getString("event_count");

				// calander.add(new CalanderModel(id, userId, calName,
				// eventCount));
			}
			HttpSession session = request.getSession();
			session.setAttribute("cId", Id);
			request.setAttribute("cid", id);
			request.setAttribute("uid", userId);
			request.setAttribute("username", name);
			request.setAttribute("cName", calName);
			request.setAttribute("eCount", eventCount);

	

			request.getRequestDispatcher("Calendar/Calendar.jsp").forward(request, response);

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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}