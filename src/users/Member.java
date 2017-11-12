package users;

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
import javax.servlet.http.HttpSession;

import models.CalendarModel;

@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<CalendarModel> calanders = new ArrayList<CalendarModel>();
	private ArrayList<CalendarModel> UserCalanders = new ArrayList<CalendarModel>();
	private ArrayList<CalendarModel> FollowingCalanders = new ArrayList<CalendarModel>();

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
		String userposition = (String) request.getSession().getAttribute("Userpos");
		Integer ssuid = (Integer) request.getSession().getAttribute("ssuid");

		// System.out.println("passed user id: " + ssuid);
		if (username == null) {
			System.out.println("No user was found");
			request.setAttribute("NoUser", "You Must Login First!");
			response.sendRedirect("Main");
		} else {
			calanders.clear();
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";

			String sql = "select * from calendar where uid = '" + ssuid + "'";
			String sql2 = "SELECT cidFollowing FROM users WHERE uid ='" + ssuid + "'";

			try {

				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				Statement st2 = c.createStatement();
				Statement st3 = c.createStatement();
				// for Users' Own Calendars
				ResultSet rs = st.executeQuery(sql);
				// For Following Calendars
				ResultSet rs2 = st2.executeQuery(sql2);
				System.out.println(rs2);

				while (rs.next()) {
					Integer calId = rs.getInt("id");
					Integer userId = Integer.parseInt(rs.getString("uid"));
					String calanderName = rs.getString("cal_name");
					String events = rs.getString("event_count");

					calanders.add(new CalendarModel(calId, userId, calanderName, events));
					System.out.println("Done retreving data!!!");

				}

				for (CalendarModel cal : calanders) {
					System.out.println("user id: " + ssuid);
					System.out.println("Cal user id: " + cal.uid);
					//
					UserCalanders.clear();
					// if (cal.uid.equals(ssuid)) {
					// System.out.println("we have a match");
					//
					Integer uid = cal.uid;
					Integer cid = cal.cid;
					String calName = cal.calName;
					String eventCount = cal.events;
					//// UserCalanders.clear();
					UserCalanders.add(new CalendarModel(uid, cid, calName, eventCount));
				}

				
				while (rs2.next()) {
					String cidFollowingString = rs2.getString(1);
					System.out.println(rs2.getString(1));
					String[] individualCids = cidFollowingString.split(",");
					for (String id : individualCids) {
						System.out.println("Im an id! :"+ id);
						String sql3 = "SELECT * FROM calendar WHERE id = '" + id + "'";
						ResultSet rs3 = st3.executeQuery(sql3);
						while (rs3.next()) {
							Integer followCalId = rs3.getInt("id");
							System.out.println(followCalId);
							System.out.println("jeff");
							Integer followUserId = Integer.parseInt(rs3.getString("uid"));
							System.out.println(followUserId);
							String followCalendarName = rs3.getString("cal_name");
							System.out.println(followCalendarName);
							String followEvents = rs3.getString("event_count");
							System.out.println(followEvents);
							FollowingCalanders.add(
									new CalendarModel(followCalId, followUserId, followCalendarName, followEvents));

						}
					}
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

			System.out.println("user cal size: " + calanders.size());
			HttpSession session = request.getSession();
			session.setAttribute("followCalanders", FollowingCalanders);
			request.setAttribute("followCalenders", FollowingCalanders);
			session.setAttribute("myCalanders", calanders);
			request.setAttribute("myCalanders", calanders);
			request.setAttribute("username", username);
			request.setAttribute("userpostion", userposition);
			request.setAttribute("ssuid", ssuid);
			request.getRequestDispatcher("users/Home.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected ArrayList<CalendarModel> calendarFollowing(HttpServletRequest request, HttpServletResponse response) {

		return FollowingCalanders;

	}
}