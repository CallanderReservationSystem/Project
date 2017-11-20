package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	private List<CalendarModel> followedCalendar = new ArrayList<CalendarModel>();

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
			Integer uId = null;
			Integer cId = null;
			Integer fid = (Integer) request.getSession().getAttribute("ssuid");
			request.setAttribute("fid", fid);

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

					uId = rs.getInt("uid");
					found = true;
				}
				if (found) {
					Statement cs = c.createStatement();
					ResultSet eq = cs.executeQuery("SELECT * FROM calendar WHERE uid =" + uId + "");
					while (eq.next()) {
						calFound = true;

						cId = eq.getInt("id");
						String calName = eq.getString("cal_name");
//						String eventCount = eq.getString("event_count");
						calendars.add(new CalendarModel(cId, uId, calName));
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

						events.add(new CalendarEventModel(Integer.parseInt(eId), uId, Integer.parseInt(cid), title,
								start, end, startTime, endTime, detail, color, Integer.parseInt(tableCount),
								Integer.parseInt(seatsPerTable), location));
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

			// check if user following any calendars
			Integer id = null;
			String sql01 = null;
			Integer cid, uid;
			followedCalendar.clear();

			for (int i = 0; i < calendars.size(); i++) {
				id = calendars.get(i).cid;

				sql01 = "SELECT * FROM shared_calendars WHERE follower_id='" + fid + "' and cid='" + id + "' ";
				try {
					c = DriverManager.getConnection(url, SQLuser, SQLpass);
					Statement st = c.createStatement();
					ResultSet rs = st.executeQuery(sql01);

					while (rs.next()) {
						System.out.println("found followed calendar!");
						cid = rs.getInt("cid");
						uid = rs.getInt("owner_uid");
						followedCalendar.add(new CalendarModel(cid, uid));
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
			}

			request.setAttribute("followedCalendars", followedCalendar);
			request.setAttribute("sUser", username);
			request.getRequestDispatcher("/Calendar/View.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
