package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalendarModel;
import models.CalendarEventModel;

@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String username;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("Username");

		if (username == null) {
			request.setAttribute("NoUser", "You Must Login First!");
			response.sendRedirect("Main");
			return;
		}
		String[] name = request.getParameterValues("name");
		if (name == null) {
			response.sendRedirect("Search");
		} else {
			ArrayList<CalendarModel> calendars = new ArrayList<CalendarModel>();
			ArrayList<CalendarEventModel> events = new ArrayList<CalendarEventModel>();
			boolean found = false;
			boolean calFound = false;
			boolean eventFound = false;
			String user = name[0];
			String uId = "";
			String cId = "";
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "SELECT uid FROM users WHERE username='" + user + "' ";

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {

					uId = rs.getString("uid");
					found = true;
				}
				if (found) {
					Statement cs = c.createStatement();
					ResultSet eq = cs.executeQuery("SELECT * FROM calendar WHERE uid =" + uId + "");
					while (eq.next()) {
						calFound = true;

						cId = eq.getString("id");
						String calName = eq.getString("cal_name");
						String eventCount = eq.getString("event_count");
						calendars.add(
								new CalendarModel(Integer.parseInt(cId), Integer.parseInt(uId), calName, eventCount));
					}
				}
				if (calFound) {
					Statement cs = c.createStatement();
					ResultSet eq = cs.executeQuery("SELECT * FROM events WHERE uid =" + uId + "");
					while (eq.next()) {
						eventFound = true;
						System.out.println("event");
						String eId = eq.getString("id");
						String cid = eq.getString("cid");
						String title = eq.getString("title");
						String start = eq.getString("start_date");
						String end = eq.getString("end_date");
						String startTime = eq.getString("start");
						String endTime = eq.getString("end");
						String detail = eq.getString("details");
						String color = eq.getString("color");
						if (color == null) {
							color = "";
						}
						String tableCount = eq.getString("tableCount");
						if (tableCount == null) {
							tableCount = "0";
						}
						String seatsPerTable = eq.getString("seatspertable");
						if (seatsPerTable == null) {
							seatsPerTable = "0";
						}
						String location = eq.getString("location");
						if (location == null) {
							location = "";
						}

						events.add(new CalendarEventModel(Integer.parseInt(eId), Integer.parseInt(uId),
								Integer.parseInt(cid), title, start, end, startTime, endTime, detail, color,
								Integer.parseInt(tableCount), Integer.parseInt(seatsPerTable), location));
					}
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			} finally {
				try {
					if (c != null) {
						c.close();
					}
				} catch (SQLException e) {
					throw new ServletException(e);
				}
			}

			if (!found) {
				request.setAttribute("noUser", "No such user was found!");
			} else {
				if (calFound) {
					request.setAttribute("calendars", calendars);
				} else {
					request.setAttribute("noCal", "This user has no calendars");
				}
				if (eventFound) {
					request.setAttribute("events", events);
				} else {
					request.setAttribute("noEvt", "This user has no events");
				}

			}
			if (user.equals(username)) {
				request.setAttribute("sameUser", "These are your Calendar(s)");
			}
			request.setAttribute("sUser", username);
			request.getRequestDispatcher("/Calendar/View.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}