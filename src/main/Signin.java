package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MyModel> users = new ArrayList<MyModel>();
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		Boolean hasError = false;
		Boolean found = false;
		String status = "";
		String upos = "";
		int uid = 0;

		if (user == null || user.trim().length() == 0) {
			hasError = true;
			request.setAttribute("error0", "Please Enter Username!");
		}
		if (pass == null || pass.trim().length() == 0) {
			hasError = true;
			request.setAttribute("error1", "Please Enter Password!");
		}
		if (hasError) {
			doGet(request, response);
		} else {
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
					status = rs.getString("status");
					upos = rs.getString("position");
					uid = rs.getInt("uid");
					users.add(new MyModel(name, pas, status, upos, uid));
				}

				for (MyModel u : users) {
					if (u.name.equals(user) && u.pass.equals(pass)) {
						found = true;
						status = u.status;
						upos = u.upos;
						uid = u.uid;
					}
				}

				if (found) {
					if (status.equals("A")) {
						HttpSession session = request.getSession();
						String un = (String) request.getParameter("user");
						
//						System.out.println("Username: " + un);
//						System.out.println("User-pos: " + upos);
						
						session.setAttribute("Username", un);
						session.setAttribute("Userpos", upos);
						session.setAttribute("ssuid", uid);
						response.sendRedirect("Member");
					}else {
						 response.sendRedirect("Index.jsp?id=stwrong");
					}
				} else {
					request.setAttribute("error2", "Invalid Username or Password");
					doGet(request, response);
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

	}

}