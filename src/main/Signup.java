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

	private Boolean hasError = false;
	private String firstname = "";
	private String lastname = "";
	private String username = "";
	private String password = "";
	private String email = "";

	private String status = "A"; // A for Active, I for Inactive.
	private String position = "U"; // U for User, A for admin.

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
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		status = request.getParameter("status");
		position = request.getParameter("");

		if (firstname == null || firstname.trim().length() == 0) {
			hasError = true;
			request.setAttribute("firstError", "Please Enter firstname first.");
		}
		if (lastname == null || lastname.trim().length() == 0) {
			hasError = true;
			request.setAttribute("lastError", "Please Enter lastname first.");
		}
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
			System.out.println("error");
			hasError = false;
		} else {
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "INSERT INTO users (firstname, lastname, username, password, email_address, status, position) VALUES ('" + firstname + "','" + lastname + "','" + username + "','"
					+ password + "','" + email + "','" + status + "','" + position + "')";

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				PreparedStatement ps = c.prepareStatement(sql);
				ps.executeUpdate();
				response.sendRedirect("success.jsp");
//				String message = "Registration is Complete. PLease login!";
//				request.setAttribute("message", message);
//				request.getRequestDispatcher("Signup.jsp").forward(request, response);
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