package main;

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
 * Servlet implementation class EditCalendarPrivate
 */
@WebServlet("/EditCalendarPrivate")
public class EditCalendarPrivate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCalendarPrivate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("newPrivateCalendar");
		System.out.println(name);
		Connection c = null;
		Integer calID = null;
		if (request.getParameter("id") != null)
		{
			System.out.println("Good going!");
			calID = Integer.parseInt(request.getParameter("id"));
		}
		else
		{
			System.out.println("Other test");
			calID = Integer.parseInt(request.getParameter("calendarID"));
		}
		System.out.println(calID);
		String cal_name = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sqlGrab = "select cal_name from calendar where id = " + calID;
		String sqlName = "update calendar set cal_name = '" + name + "' where id = " + calID;
		
		if (name == null)
		{
			try
			{
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sqlGrab);
				
				while (rs.next())
				{
					cal_name = rs.getString("cal_name");
				}
				
				request.setAttribute("calID", calID);
				request.setAttribute("cal_name", cal_name);
				System.out.println("test print prior");
				request.getRequestDispatcher("EditCalendarPrivate.jsp").forward(request, response);
			}
			catch (SQLException e)
			{
				throw new ServletException(e);
			}
		}
		
		
		if (name != null)
		{
			try
			{
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				st.executeUpdate(sqlName);
				System.out.println("test print");
				System.out.println(name + " " + calID);
				response.sendRedirect("Member");
			}
			catch (SQLException e)
			{
				throw new ServletException(e);
			}
		}
		

//		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
