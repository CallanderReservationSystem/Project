package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UnfollowCalendar")
public class UnfollowCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer cid = Integer.parseInt(request.getParameter("cid"));
		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "DELETE FROM shared_calendars WHERE cid='" + cid + "' ";
		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("done!!!");
			response.sendRedirect("Member");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}