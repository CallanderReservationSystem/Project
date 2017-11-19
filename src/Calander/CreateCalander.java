package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import models.CalendarModel;
import models.MyModel;

@WebServlet("/CreateCalander")
public class CreateCalander extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArrayList<MyModel> users = new ArrayList<MyModel>();
	private String CalName;
	private String UserName;
	// private Integer uid = 0;
	private Boolean hasError = false;
	// private Boolean found = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Calendar/CreateCalander.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserName = (String) request.getSession().getAttribute("Username");
		System.out.println("passed username: " + UserName);

		CalName = request.getParameter("CalName");
		System.out.println("passed CalName: " + CalName);

		// eventCount = request.getParameter("eventCount"); // change type to
		// int
		// System.out.println("passed event#: " + eventCount);

		if (UserName == null || UserName.trim().length() == 0) {

			// hasError = true;
			System.out.println("No user was found");
			request.setAttribute("NoUser", "You Must Login First!");
			// response.sendRedirect("Main");
			request.getRequestDispatcher("Main").forward(request, response);

		} else {

			if (CalName == null || CalName.trim().length() == 0) {
				System.out.println("empty name parameter");
				hasError = true;
				request.setAttribute("CalError", "Please Enter calander Name first!");
			}

			// if (eventCount == null || eventCount.trim().length() == 0) {
			// System.out.println("empty count parameter");
			// hasError = true;
			// request.setAttribute("EventError", "Please Enter valid event
			// count first!");
			// }

			if (hasError) {
				doGet(request, response);
				System.out.println("input field is missing");

			} else {
				Integer userId = getId();
				Connection c = null;
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
				String SQLuser = "cs3337stu03";
				String SQLpass = "K!c7YAg.";
				String sql = "INSERT INTO calendar (uid, cal_name) VALUES ('" + userId + "','" + CalName + "')";

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
				request.setAttribute("username", UserName);
				request.setAttribute("calanderName", CalName);
				request.getRequestDispatcher("Member").forward(request, response);
			}
		}
		hasError = false;

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
					// found = true;
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

}