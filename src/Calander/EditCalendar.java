package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalendarModel;

@WebServlet("/EditCalendar")
public class EditCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Boolean Found = true;
	private Integer id00;
	private Integer uid0;
	private String cal_name;
	private String AdminUsers = "";
	private Integer id;
	private String admin;
	private String name = null;
	private Integer owner_id;
	private Integer user_id;
	private List<String> admins = new ArrayList<String>();
	private List<CalendarModel> calendars = new ArrayList<CalendarModel>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection c = null;
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";

		if (request.getParameter("Cal-id") != null) {
			// id01 = Integer.parseInt(request.getParameter("Cal-id"));
		}
		if (request.getParameter("CalName") != null) {
			cal_name = request.getParameter("CalName");

		}
		if (request.getParameter("AdminUser") != null) {
			AdminUsers = request.getParameter("AdminUser");
			System.out.println("input user: " + AdminUsers);

			//////////// -- PULL UID FROM SQL SERVER--//////////////
			String sql00 = "select * from users where username like '" + AdminUsers + "' ";
			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql00);
				if (rs.next()) {
					System.out.println("found!!!");
					user_id = rs.getInt("uid");
					Found = true;
					while (rs.next()) {
						
						
					}
				} else {
					System.out.println("not found!");
					Found = false;
					request.setAttribute("AdminError", "username was not found!"); //
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
		// System.out.println("new values: " + id01 + " " + cal_name + " " +
		// AdminUsers);
		if (request.getParameter("id") != null) {
			id00 = Integer.parseInt(request.getParameter("id"));
		}

		id = id00;
		// System.out.println("passed new admin: " + AdminUsers);

		owner_id = (Integer) request.getSession().getAttribute("ssuid");
		// System.out.println("owner id: " + owner_id);

		//////////// -- PULL CAL INFO FROM SQL SERVER--//////////////
		String sql01 = "select * from calendar where id = " + id + "";
		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql01);
			while (rs.next()) {
				uid0 = rs.getInt("uid");
				name = rs.getString("cal_name");
				calendars.add(new CalendarModel(id, uid0, name));
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

		//////////// -- UPDATE CAL NAME ON SQL SERVER--//////////////
		String sql02 = "update calendar set  cal_name = '" + cal_name + "' where id = " + id;
		if (cal_name != null) {
			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				st.executeUpdate(sql02);
				// System.out.println("updated!");
				cal_name = null;
				// request.getRequestDispatcher("Member").forward(request,
				// response);
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

		admin = "";
		admins = new ArrayList<String>();
		//////////// -- PULL ADMIN & CAL LINKING FROM SQL SERVER--//////////////
		String sql04 = "select * from admin_users where cal_id = " + id + "";
		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql04);
			while (rs.next()) {
				user_id = rs.getInt("user_id");
				admin = rs.getString("username");
				admins.add(admin);
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

		//////////// -- UPDATE ADMIN & CAL LINKING ON SQL SERVER--//////////////
		String sql03 = "INSERT INTO admin_users (owner_id, user_id, username, cal_id) values ('" + owner_id + "','"
				+ user_id + "','" + AdminUsers + "','" + id + "')";
		// System.out.println("admins: " + admins);
		System.out.println("status: " + Found);
		if (Found) {
			if (AdminUsers != null && AdminUsers.trim().length() != 0) {
				try {
					c = DriverManager.getConnection(url, SQLuser, SQLpass);
					Statement st = c.createStatement();
					st.executeUpdate(sql03);
					System.out.println("UPDATE ADMIN & CAL");
					AdminUsers = null;
					request.getRequestDispatcher("Member").forward(request, response);
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
		}

		// System.out.print("name: " + name);
		request.setAttribute("admins", admins);
		request.setAttribute("name", name);
		request.setAttribute("cid", id);

		request.getRequestDispatcher("Calendar/EditCalendar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
