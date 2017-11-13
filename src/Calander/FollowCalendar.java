package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FollowCalendar")
public class FollowCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer fid;
	private Integer id;
	private Integer uid, uid01, cid;
	private String title;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		fid = (Integer) request.getSession().getAttribute("ssuid");
		id = Integer.parseInt(request.getParameter("cid"));
		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";

		////////////// --SELECT--/////////////////
		String sql01 = "select * from calendar where id = '" + id + "'";
		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql01);
			while (rs.next()) {
				uid = rs.getInt("uid");
				title = rs.getString("cal_name");
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
		System.out.println("calendar id to follow: " + id);
		/////// --CHECK-USER-AUTHORIZATION--////////// IF USER IS ADMIN ON THAT
		/////// CALENDAR
		String sql02 = "select * from admin_users where user_id = '" + fid + "' and cal_id = '" + id + "' ";
		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql02);
			if (rs.next()) {
				while (rs.next()) {
					uid01 = rs.getInt("user_id");
					cid = rs.getInt("cal_id");
				}

				////////////// --INSERT--/////////////////
				String sql03 = "INSERT INTO shared_calendars (owner_uid, follower_id, cid, title) values('" + uid
						+ "','" + fid + "','" + id + "','" + title + "')";
				try {
					c = DriverManager.getConnection(url, SQLuser, SQLpass);
					PreparedStatement ps = c.prepareStatement(sql03);
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
				response.sendRedirect("Member");

			} else {
				System.out.println("not found!");
				request.setAttribute("userAuth", "User does not have admin level over this calendar");
				request.getRequestDispatcher("Search").forward(request, response);
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}