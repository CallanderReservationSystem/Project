package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Signup() {
		super();
	}

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
		request.getRequestDispatcher("Signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean hasError = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		if (username == null || username.trim().length() == 0) {
			hasError = true;
			request.setAttribute("userError", "Please Enter username first.");
		}
		if (password == null || password.trim().length() == 0) {
			hasError = true;
			request.setAttribute("passError", "Please Enter password first.");
		}
		if (email == null || email.trim().length() == 0) {
			hasError = true;
			request.setAttribute("emailError", "Please Enter email first.");
		}

		if (hasError) {
			doGet(request, response);
		} else {
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "INSERT INTO calendar (username, password, email_address) VALUES ('" + username + "','" + password + "','" + email + "')";

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				PreparedStatement ps = c.prepareStatement(sql);
				ps.executeUpdate();
				String message = "Registration is Complete. PLease login!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("Signup.jsp").forward(request, response);
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
}