package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	
	private ArrayList<CalendarModel> calendars = new ArrayList<CalendarModel>();
	private ArrayList<MyModel> users = new ArrayList<MyModel>();
	private ArrayList<CalendarEventModel> events = new ArrayList<CalendarEventModel>();
	private ArrayList<Table> tabels = new ArrayList<Table>();
	private String CalName;
	private String UserName;
	private Integer eventCount; // change to Int
	private Integer uid = 0;
	private Boolean hasError = false;
	private Boolean found = false;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}
	
	
	private static final long serialVersionUID = 1L;
    public CreateEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CreateEvent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values
		String eventName = request.getParameter("eventName");
		String date = request.getParameter("date");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String pr = request.getParameter("pr");
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		String seats = request.getParameter("seats");
		String checkTables = request.getParameter("tablesCheck");
		String tables = request.getParameter("tables");
		
		//validate input
		boolean inValidName = (eventName == null) || (eventName.trim().length() == 0);
		boolean inValidDate = (date == null) || (date.trim().length() == 0);
		boolean inValidSTime = (startTime == null) || (startTime.trim().length() == 0);
		boolean inValidETime = (endTime == null) || (endTime.trim().length() == 0);
		boolean inValidPri = (pr == null);
		boolean inValidLocation = (location == null) || (location.trim().length() == 0);
		boolean inValidSeats = (seats == null) || (seats.trim().length() == 0);
		boolean checkTable = checkTables == null;
		boolean inValidTables = (tables == null) || (tables.trim().length() == 0);
		
		//Message so the user knows what they need to do
		if(inValidName || inValidDate || inValidSTime || inValidETime || inValidPri || inValidLocation || inValidSeats) {
			if(inValidName) {
				request.setAttribute("eventError", "Must Enter Valid Event Name");
			}
			if(inValidDate) {
				request.setAttribute("dateError", "Must Enter Valid Date");
			}
			if(inValidSTime) {
				request.setAttribute("timeSError", "Must Enter Starting Time");
			}
			if(inValidETime) {
				request.setAttribute("timeEError", "Must Enter Ending Time");
			}
			if(inValidPri) {
				request.setAttribute("pri", "Must Select Private or Public Event");
			}
			if(inValidLocation) {
				request.setAttribute("locaError", "Must Enter Valid Location");
			}
			if(inValidSeats) {
				request.setAttribute("seatsError", "Must Enter Ammount of Seats");
			}
			if(checkTable) {
				if(inValidTables) {
					request.setAttribute("tableError", "Must Enter Ammount of Tables");
				}
			}
			doGet(request, response);
		} else {
			Integer userId = getId();
			Integer calendarId = getCalendarId();
			Integer eventId = getEventId();
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "INSERT INTO events (id, uid, cid, title, start, end, details) VALUES ('"+ eventId + "','" + userId + "','" + calendarId
					+ "','" + eventName + "','"+ startTime +"','"+endTime+"','"+description +")";
			
			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				PreparedStatement ps = c.prepareStatement(sql);
				ps.executeUpdate();
				System.out.println("Done!!!");
				
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
			
		response.sendRedirect("Member.jsp");	
	}
}
	private Integer getId() throws ServletException {

		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from users";
		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("username");
				String pas = rs.getString("password");
				String status = rs.getString("status");
				String upos = rs.getString("position");
				Integer uid0 = rs.getInt("uid");
				users.add(new MyModel(name, pas, status, upos, uid0));
			}

			for (MyModel u : users) {
				if (u.name.equals(UserName)) {
					found = true;
					return u.uid;
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
		return null;
	}
	
	private Integer getCalendarId() throws ServletException {

		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from calendar";
		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// will need to make changes further to Calendar Model, both on PHPadmin and in 
			// the CalendarModel.java

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer uid0 = rs.getInt("uid");
				String calendarName = rs.getString("cal_name");
				Integer eventCount = rs.getInt("event_count");
				calendars.add(new CalendarModel(id, uid0, calendarName,eventCount));
			}

			for (CalendarModel c1 : calendars) {
				if (c1.calName.equals(CalName)) {
					found = true;
					return c1.id;
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
		return null;
	}
	
	private Integer getEventId() throws ServletException {

		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from events";
		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// will need to make changes further to Calendar Model, both on PHPadmin and in 
			// the CalendarModel.java

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer uid = rs.getInt("uid");
				Integer cid = rs.getInt("cid");
				String title = rs.getString("title");
				String start = rs.getString("start");
				String end = rs.getString("end");
				String color = rs.getString("color");
				Integer tableCount = rs.getInt("tableCount");
				Integer seatsPerTable = rs.getInt("seatsPerTable");
				events.add(new CalendarEventModel(id, title, start, end, color, tableCount,seatsPerTable));
			}

			for (CalendarEventModel e  : events) {
				if (e.title.equals("title")) {
					found = true;
					return e.id;
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
		return null;
	}

}