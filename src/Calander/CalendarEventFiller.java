package Calander;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CalendarEventModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

@WebServlet("/CalendarEventFiller")
public class CalendarEventFiller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CalendarEventModel> events = new ArrayList<CalendarEventModel>();

		Integer id;
		Integer uid;
		String cid = (String) request.getSession().getAttribute("cId"); // need to change to Integer
		System.out.println("event cal id: " + cid);
		String title = null;
		String start;
		String end;
		String start_time = null;
		String end_time = null;
//		String details;
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

			events.clear();
			while (rs.next()) {
				id = rs.getInt("id");
				uid = rs.getInt("uid");
				title = rs.getString("title");
				start = rs.getString("start_date");
				end = rs.getString("end_date");
				start_time = rs.getString("start");
				end_time = rs.getString("end");
				url = "RegisterForEvent?id=" + cid + "";
				events.add(new CalendarEventModel(id, uid, cid, title, start, end, start_time, end_time, url, color,
						tableCount, seatsPerTable));
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
		// CalendarEventModel c = new CalendarEventModel();
		// c.setId(1);
		// c.setStart("2017-10-02");
		// c.setEnd("2017-10-20");
		// c.setTitle("Task in Progress 1");
		// CalendarEventModel d = new CalendarEventModel();
		// d.setId(2);
		// d.setStart("2017-10-21");
		// d.setEnd("2017-11-29");
		// d.setTitle("Task in Progress 2");
		// events.add(c);
		// events.add(d);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(new Gson().toJson(events));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}