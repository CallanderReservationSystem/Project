package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calander")
public class Calander extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ArrayList<CalanderModel> calander = new ArrayList<CalanderModel>();
		Integer id = null;
		Integer userId = null;
		String calName = null;
		String eventCount = null;
		// request.getRequestDispatcher("Calendar.jsp").forward(request,
		// response);
		String name = (String) request.getSession().getAttribute("Username");
		System.out.println("name: " + name);
		Integer uid = (Integer) request.getSession().getAttribute("ssuid");
		System.out.println("id: " + uid);
		String calId = request.getParameter("cid");
		System.out.println("cal id: " + calId);

		Connection c = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from calendar where id = " + calId + "";

		try {

			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("id");
				userId = rs.getInt("uid");
				calName = rs.getString("cal_name");
				eventCount = rs.getString("event_count");

				// calander.add(new MyModel(id, userId, calName, eventCount));
			}
			request.setAttribute("cid", id);
			request.setAttribute("uid", userId);
			request.setAttribute("username", name);
			request.setAttribute("cName", calName);
			request.setAttribute("eCount", eventCount);
			request.getRequestDispatcher("Calendar.jsp").forward(request, response);;

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
		Boolean notFound = false;

		if (notFound) {
			doGet(request, response);
		} else {

		}

	}

}