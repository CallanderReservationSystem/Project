package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = (String) request.getSession().getAttribute("Username");
		if (name == null) {
			System.out.println("No user was found");
			request.setAttribute("NoUser", "You Must Login First!");
			response.sendRedirect("Main");
		} else {
			request.setAttribute("user", name);
			request.getRequestDispatcher("Member.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}