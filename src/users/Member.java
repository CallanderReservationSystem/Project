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
			response.sendRedirect("../Index.jsp");
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
				ArrayList<Integer> eventcount = new ArrayList<Integer>();
				Statement st = c.createStatement();
				
				
				Statement st0 = c.createStatement();
				// for Users' Own Calendars
				ResultSet rs = st.executeQuery(sql);
				// For Following Calendars

				ResultSet rs0 = null;

				while (rs.next()) {
					String sql0 = "SELECT id FROM events WHERE cid =" + rs.getInt("id");
					Integer calId = rs.getInt("id");
					Integer userId = Integer.parseInt(rs.getString("uid"));
					Integer events = 0;
					String calanderName = rs.getString("cal_name");
					rs0 = st0.executeQuery(sql0);
					
					eventcount.clear();
					while(rs0.next()) {
						events = rs0.getInt("id");
						eventcount.add(events);
					}
					events = eventcount.size();
					calanders.add(new CalendarModel(calId, userId, calanderName, events));
					System.out.println("Done retreving data!!!");

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


			try {		
				
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				ArrayList<Integer> followingEventCount = new ArrayList<Integer>();
				Statement st2 = c.createStatement();
				ResultSet rs2 = st2.executeQuery(sql2);
				Statement st3 = c.createStatement();
				Statement st0 = c.createStatement();
				String sql3 = "";
				ResultSet rs3 = null;
				ResultSet rs0 = null;
				String sql0 = "";
				
				
				while (rs2.next()) {
					FollowingCalanders.clear();
					String cidFollowingString = rs2.getString(1);
					if(cidFollowingString == null) {
						
					} else {
					System.out.println(rs2.getString(1));
					String[] individualCids = cidFollowingString.split(",");
					for (String id : individualCids) {
						System.out.println("Im an id! :"+ id);
						 sql3 = "SELECT * FROM calendar WHERE id = '" + id + "'";
						 rs3 = st3.executeQuery(sql3);
						while (rs3.next()) {
							sql0 = "SELECT id FROM events WHERE cid ="+rs3.getInt("id");
							Integer followCalId = rs3.getInt("id");
							System.out.println(followCalId);
							System.out.println("jeff");
							Integer followUserId = Integer.parseInt(rs3.getString("uid"));
							System.out.println(followUserId);
							String followCalendarName = rs3.getString("cal_name");
							Integer followEvents = 0;
							rs0 = st0.executeQuery(sql0);
							
							followingEventCount.clear();
							while(rs0.next()) {
								followEvents = rs0.getInt("id");
								followingEventCount.add(followEvents);
							}
							
							followEvents = followingEventCount.size();
							
							
							FollowingCalanders.add(
									new CalendarModel(followCalId, followUserId, followCalendarName, followEvents));

						}
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