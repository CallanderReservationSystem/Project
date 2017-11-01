package Calander;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;

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
	String Id;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ArrayList<CalanderModel> calander = new ArrayList<CalanderModel>();
		Integer id = null;
		Integer userId = null;
		String calName = null;
		String eventCount = null;
		// request.getRequestDispatcher("Calendar.jsp").forward(request,
		// response);
		String name = (String) request.getSession().getAttribute("Username");
		System.out.println("name: " + name);
		Integer uid = (Integer) request.getSession().getAttribute("ssuid");
		System.out.println("id: " + uid);
		String calId = request.getParameter("cid");
		if (calId != null)
			Id = calId;
		System.out.println("new cal id: " + calId);
		
		System.out.println("my id: " + Id);

		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from calendar where id = " + calId + "";

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

			// printEvents(request, response);

			request.getRequestDispatcher("Calander/Calendar.jsp").forward(request, response);

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

	private void printEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		Integer userId = null;
		String calendarId = Id; // change back to Integer
		System.out.println("cal id 2: " + calendarId);
		String title = null;
		String start_date;
		String end_date;
		String start = null;
		String end = null;
		String color = null;
		String eventUrl = null;
		Integer tableCount = null;
		Integer seatsPerTable = null;

		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from events where cid =" + calendarId + " ";

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			events.clear();
			while (rs.next()) {
				id = rs.getInt("id");
				title = rs.getString("title");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				start = rs.getString("start");
				end = rs.getString("end");
				eventUrl = "RegisterForEvent?id= " + calendarId + " ";
				events.add(new CalendarEventModel(id, userId, calendarId, title, start_date, end_date, start, end, eventUrl, color, tableCount,
						seatsPerTable));
			}
			// request.setAttribute("events", events);
			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 PrintWriter out = response.getWriter();
			 out.write(new Gson().toJson(events));
			// System.out.println("new data: " + new Gson().toJson(events));
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

}