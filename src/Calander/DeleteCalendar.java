package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalendarModel;

@WebServlet("/DeleteCalendar")
public class DeleteCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
  
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CalendarModel> calendars = new ArrayList<CalendarModel>();
		
		calendars = (List<CalendarModel>) request.getSession().getAttribute("myCalanders");
		System.out.println("passed values: " + calendars);

		String id0 = request.getParameter("id");
		if (id0 == null) {
			request.getRequestDispatcher("Calendar/DeleteCalendar.jsp").forward(request, response);
		}
		Integer id = Integer.parseInt(id0);
		System.out.println("passed id: "+ id);
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "delete FROM calendar WHERE id =" + id + "";
			
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("done!!!");

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
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "delete FROM events WHERE cid =" + id + "";
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("done!!! again");
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	 
	 try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "delete FROM tables WHERE cid =" + id + "";
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("done!!! again, for the last Time");
			response.sendRedirect("Member");
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
		 
	 }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
