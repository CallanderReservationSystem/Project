package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = "";
	private String search;
	private boolean notFound;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		username = (String) request.getSession().getAttribute("Username");
		request.setAttribute("user", username);
//		if (username == null) {
//			request.setAttribute("noUser", "You Must Login First!");
//			response.sendRedirect("Main");
//
//		} else {
			request.getRequestDispatcher("Calendar/Search.jsp").forward(request, response);
		// }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		username = (String) request.getSession().getAttribute("Username");
		//System.out.println("passed username: " + username);
		request.setAttribute("user", username);
		if (username == null) {
			request.setAttribute("noUser", "You Must Login First!");
			response.sendRedirect("Main");
			return;

		}
		ArrayList<String> users = new ArrayList<String>();
		search = request.getParameter("search");
		if (search == null || search.trim().length() == 0) {
			System.out.println("empty entry");
			request.setAttribute("notFound", "No users with that username found.");
			request.getRequestDispatcher("Calendar/Search.jsp").forward(request, response);
		} else {
			System.out.println("we got here ");
			notFound = true;
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "select username from users";

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					String name = rs.getString("username");

					if (name.toLowerCase().contains(search.toLowerCase())) {
						if (!(username.equals(name))) {
							users.add(name);
							notFound = false;
						}
					}
				}
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
			
			if (notFound) {
				request.setAttribute("notFound", "No users with that username found.");
				doGet(request, response);
			} else {
				request.setAttribute("users", users);
			}
		}
		
		doGet(request, response);
	}

}