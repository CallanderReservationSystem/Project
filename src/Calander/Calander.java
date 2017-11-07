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

		String name = (String) request.getSession().getAttribute("Username");
		System.out.println("name: " + name);
		Integer uid = (Integer) request.getSession().getAttribute("ssuid");
		System.out.println("id: " + uid);
		Integer calId = Integer.parseInt(request.getParameter("cid"));
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

			printEvents(request, response);

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

	private void printEvents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CalendarEventModel> events = new ArrayList<CalendarEventModel>();

		Integer id;
		Integer uid;
		Integer cid = (Integer) request.getSession().getAttribute("cId"); // need to change to Integer
		System.out.println("event cal id: " + cid);
		String title = null;
		String start;
		String end;
		String start_time = null;
		String end_time = null;
		// String details;
		String color = null;
		String url;
		Integer tableCount = null;
		Integer seatsPerTable = null;
		Connection c = null;

		String sqlUrl = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from events where cid =" + cid + " ";

		try {

			c = DriverManager.getConnection(sqlUrl, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// events.clear();

			while (rs.next()) {
				id = rs.getInt("id");
				System.out.println("Id is :" + id);
				uid = rs.getInt("uid");
				System.out.println("uId is :" + uid);
				title = rs.getString("title");
				System.out.println("title is :" + title);
				start = rs.getString("start_date");
				System.out.println("start_date is :" + start);
				end = rs.getString("end_date");
				System.out.println("end_date is :" + end);
				start_time = rs.getString("start");
				System.out.println("start_time is :" + start_time);
				end_time = rs.getString("end");
				System.out.println("end_time is :" + end_time);
				url = "RegisterForEvent?id=" + cid + "";
				events.add(new CalendarEventModel(id, uid, cid, title, start, end, start_time, end_time, url, color,
						tableCount, seatsPerTable));
				System.out.println(events.size());
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

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(events));
		for (CalendarEventModel e : events) {
			System.out.println(e.id);
		}

	}
}