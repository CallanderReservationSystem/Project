package Calander;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/RegisterForEvent")
public class RegisterForEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;

		Integer id = null;
		Integer userId = null;
		String eventName = null;
		String eventId = (String) request.getParameter("id");
		System.out.println(eventId);
		
		String user_name = (String)request.getSession().getAttribute("Username");
		System.out.println(user_name);
		String location = null;
		String start = null;
		String end = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "SELECT * from events where id = " + eventId;

		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("id");
				userId = rs.getInt("uid");
				eventName = rs.getString("title");
				location = rs.getString("location");
				System.out.println(eventName);
			}
			System.out.println(eventName);
			
			request.setAttribute("user_name", user_name);
			request.setAttribute("eventName", eventName);
			request.setAttribute("id", id);
			request.setAttribute("title", eventName);
			request.setAttribute("start", start);
			request.setAttribute("end", end);

			request.getRequestDispatcher("/Calendar/RegisterForEvent.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}  finally {
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
		
		String reservation_name = request.getParameter("name");
		String username = request.getParameter("user_name");
		request.setAttribute("user_name", username);
		String details = request.getParameter("details");
		String startTime =request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		String startDate =request.getParameter("startDate");
		String endDate =request.getParameter("endDate");
		Integer numOfPeople;
		if(request.getParameter("numOfPeople") == null || request.getParameter("numOfPeople").trim().length() == 0) {
			numOfPeople = null;
		} else {
			numOfPeople = Integer.parseInt(request.getParameter("numOfPeople"));
		}
		
		Integer id = Integer.parseInt(request.getParameter("id")); 
	//	Integer calId = Integer.parseInt(request.getParameter("calId"));
		Integer userId = (Integer)request.getSession().getAttribute("ssuid");
		
		boolean inValidName = (reservation_name == null) || (reservation_name.trim().length() == 0);
		boolean inValidUName = (username == null) || (username.trim().length() == 0);
		boolean inValidStartDate = (startDate == null) || (startDate.trim().length() == 0);
		boolean inValidEndDate = (endDate == null) || (endDate.trim().length() == 0);
		boolean inValidSTime = (startTime == null) || (startTime.trim().length() == 0);
		boolean inValidETime = (endTime == null) || (endTime.trim().length() == 0);
		boolean inValidnumOfPeople = (numOfPeople == null);
		
		if (inValidName || inValidStartDate || inValidEndDate || inValidSTime || inValidETime || inValidnumOfPeople ) {
			if (inValidName) {
				request.setAttribute("nameError", "Must Enter Name for Reservation.");
				}
			if (inValidStartDate || inValidEndDate) {
				request.setAttribute("dateError", "Either your Begining Date, End Date, or both are invalid. ");
				}
			if (inValidSTime || inValidETime) {
				request.setAttribute("timeError", "Either your Begining Time, End Time, or both are invalid.");
				}
			if (inValidnumOfPeople) {
				request.setAttribute("numOfPeopleError", "Must Enter a Party Size.");
				}
			doGet(request,response);
			} else {
		request.getRequestDispatcher("Calendar/UserCalendar.jsp").forward(request, response);
		}
		
		
		
	}
}