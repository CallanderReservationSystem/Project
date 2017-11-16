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
						// System.out.println(calFound);
						cId = eq.getString("id");
						String calName = eq.getString("cal_name");
						String eventCount = eq.getString("event_count");
						calendars.add(
								new CalendarModel(Integer.parseInt(cId), Integer.parseInt(uId), calName, eventCount));
					}

					if (calFound) {
						Statement cs1 = c.createStatement();
						// System.out.println(cId);
						ResultSet eq2 = cs1.executeQuery("SELECT * FROM events WHERE uid =" + uId + "");
						// System.out.println(eq2);
						while (eq2.next()) {
							eventFound = true;
							String eId = eq2.getString("id");
							String cid = eq2.getString("cid");
							// System.out.println(cId);
							String title = eq2.getString("title");
							String start = eq2.getString("start_date");
							String end = eq2.getString("end_date");
							String startTime = eq2.getString("start");
							String endTime = eq2.getString("end");
							String detail = eq2.getString("details");
							String color = eq2.getString("color");
							if (color == null) {
								color = "";
							}
							String tableCount = eq2.getString("tableCount");
							if (tableCount == null) {
								tableCount = "0";
							}
							String seatsPerTable = eq2.getString("seatspertable");
							if (seatsPerTable == null) {
								seatsPerTable = "0";
							}
							String location = eq2.getString("location");
							if (location == null) {
								location = "";
							}

							events.add(new CalendarEventModel(Integer.parseInt(eId), Integer.parseInt(uId),
									Integer.parseInt(cid), title, start, end, startTime, endTime, url, color,
									Integer.parseInt(tableCount), Integer.parseInt(seatsPerTable), location));
						}
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
					if (eventFound) {
						request.setAttribute("events", events);
					}
				} else {
					request.setAttribute("noCal", "This user has no calendars");
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
		String username = (String) request.getSession().getAttribute("Username");
		Integer uid = (Integer) request.getSession().getAttribute("ssuid");
		ArrayList<CalendarModel> calendars = new ArrayList<CalendarModel>();
		ArrayList<CalendarEventModel> events = new ArrayList<CalendarEventModel>();
		ArrayList<String> followingStrings = new ArrayList<String>();
		ArrayList<String> ids = new ArrayList<String>();
		int y = 0;
		String followingString = "0";
		// THIS IS NOT WORKING!!!!!!
		// String calendaridSelected = request.getParameter("cid");
		// System.out.println("This is the id you selected: "+calendaridSelected);

		// This is working
		for (String cid : request.getParameterValues("cid")) {
			String x = request.getParameter("get_" + cid);
			System.out.print(x + ",");
			ids.add(x);
		}

		// Working
		System.out.println();
		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql1 = "SELECT cidFollowing FROM users WHERE uid =" + uid + "";
		String z = null;

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql1);
			// Working
			while (rs.next()) {
				followingString = rs.getString("cidFollowing");
				followingStrings.add(followingString);
			}

			if (followingString == null || followingString.equals("null") || followingString.equals("")) {

				Statement cs = c.createStatement();
				int eq = cs.executeUpdate(
						"UPDATE users SET cidFollowing = '" + request.getParameter("cid") + "' WHERE uid =" + uid + "");

			} else {

				Statement cs = c.createStatement();
				int eq = cs.executeUpdate("UPDATE users SET cidFollowing = CONCAT(cidFollowing, ',"
						+ request.getParameter("cid") + "') Where uid =" + uid + "");

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

		doGet(request, response);
	}

}
