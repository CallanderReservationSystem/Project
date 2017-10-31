package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CalendarEventModel;
import java.sql.Statement;
import java.util.ArrayList;
import com.google.gson.Gson;


@WebServlet("/CalendarEventFiller")
public class CalendarEventFiller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<CalendarEventModel> events = new ArrayList<CalendarEventModel>();

	public CalendarEventFiller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;
		Integer userId = null;
		Integer calendarId = Integer.parseInt(request.getParameter("cin"));
		System.out.println("cal id: " + calendarId );
		String title = null;
		String start = null;
		String end = null;
		String color = null;
		Integer tableCount = null;
		Integer seatsPerTable = null;

		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from event where cid =" + calendarId + " ";

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("id");
				title = rs.getString("tittle");
				start = rs.getString("start");
				end = rs.getString("end");
				events.add(new CalendarEventModel(id, userId, calendarId, title, start, end, color, tableCount, seatsPerTable));
			}
			//request.setAttribute("", events);
			new Gson().toJson(events);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}