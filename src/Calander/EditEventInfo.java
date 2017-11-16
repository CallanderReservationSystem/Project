package Calander;


import java.io.IOException;
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

/**
 * Servlet implementation class EditEventInfo
 */
@WebServlet("/EditEventInfo")
public class EditEventInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection c = null;
		
		Integer eventID = null;
		if ((request.getParameter("id")) != null)
		{
			System.out.println("ok");
			eventID = Integer.parseInt(request.getParameter("id"));
		}
		else
		{
			System.out.println("ok2");
			eventID = Integer.parseInt(request.getParameter("eventID"));
		}
		String oldTitle = null;
		String oldLocation = null;
		String oldStart = null;
		String oldEnd = null;
		String oldStart_date = null;
		String oldEnd_date = null;
		Integer oldTableCount = null;
		Integer oldSeatsPerTable = null;
		
		String title = request.getParameter("newTitle");
		String location = request.getParameter("newLocation");
		String start = request.getParameter("newStart");
		String end = request.getParameter("newEnd");
		String start_date = request.getParameter("newStart_date");
		String end_date = request.getParameter("newEnd_date");
		Integer tableCount = Integer.parseInt(request.getParameter("newTableCount"));
		Integer seatsPerTable = Integer.parseInt(request.getParameter("newSeatsPerTable"));
		
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sqlGrab = "select * from events where id = " + eventID;
		String sqlThrow = "update event "
				+ "set title = '" + title + 
				"', location = '" + location + 
				"', start = '" + start + 
				"', end = '" + end +
				"', start_date = '" + start_date +
				"', end_date = '" + end_date +
				"', tableCount = '" + tableCount +
				"', seatsPerTable = '" + seatsPerTable +
				"' where id = " + eventID;
		
		if (title == null)
		{
			try
			{
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sqlGrab);
				
				while (rs.next())
				{
					oldTitle = rs.getString("title");
					oldLocation = rs.getString("location");
					oldStart = rs.getString("start");
					oldEnd = rs.getString("end");
					oldStart_date = rs.getString("start_date");
					oldEnd_date = rs.getString("end_date");				
					oldTableCount = rs.getInt("tableCount");
					oldSeatsPerTable = rs.getInt("seatsPerTable");
				}
				
				request.setAttribute("title", oldTitle);
				request.setAttribute("location", oldLocation);
				request.setAttribute("start", oldStart);
				request.setAttribute("end", oldEnd);
				request.setAttribute("start_date", oldStart_date);
				request.setAttribute("end_date", oldEnd_date);
				request.setAttribute("tableCount", oldTableCount);
				request.setAttribute("seatsPerTable", oldSeatsPerTable);
				request.setAttribute("eventID", eventID);
				request.getRequestDispatcher("Calendar/EditEventInfo.jsp").forward(request, response);
			}
			catch (SQLException e)
			{
				throw new ServletException(e);
			}
		}
		if (title != null)
		{
			try
			{
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				st.executeUpdate(sqlThrow);
				response.sendRedirect("Member");
			}
			catch (SQLException e)
			{
				throw new ServletException(e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
