package main;

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

@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<CalendarModel> calanders = new ArrayList<CalendarModel>();
	private ArrayList<CalendarModel> UserCalanders = new ArrayList<CalendarModel>();

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
			String sql = "select * from calendar where uid = '"+ ssuid +"'";

			try {

				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					Integer userId = Integer.parseInt(rs.getString("uid"));
					Integer id = Integer.parseInt(rs.getString("id"));
					String calanderName = rs.getString("cal_name");
					Integer events = Integer.parseInt(rs.getString("event_count"));
					
					calanders.add(new CalendarModel(id, userId, calanderName, events));
					System.out.println();
				}
				
//				for (CalendarModel cal : calanders) {
//
//						UserCalanders.clear();
//						System.out.println("we have a match");
//						Integer id = cal.uid;
//						String calName = cal.calName;
//						String eventCount = cal.events;
////						UserCalanders.clear();
//						UserCalanders.add(new CalendarModel(id, calName, eventCount));
//					} else {
//						System.out.println("no match found");
//						UserCalanders.clear();
//					}
//						
			

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
			
			System.out.println("user cal size: " + UserCalanders.size());
			request.setAttribute("myCalanders", calanders);
			request.setAttribute("username", username);
			request.setAttribute("userpostion", userposition);
			request.setAttribute("ssuid", ssuid);
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}