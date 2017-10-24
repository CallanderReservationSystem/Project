package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateCalander")
public class CreateCalander extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private ArrayList<CalanderModel> calanders = new
	// ArrayList<CalanderModel>();
	private String CalName;
	private String UserName;
	private String eventCount; // change to Int
	private Boolean hasError = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("CreateCalendar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserName = (String) request.getSession().getAttribute("Username");
		System.out.println("passed username: " + UserName);

		CalName = request.getParameter("CalName");
		System.out.println("passed CalName: " + CalName);

		eventCount = request.getParameter("eventCount"); // change type to int
		System.out.println("passed event#: " + eventCount);

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

			if (eventCount == null || eventCount.trim().length() == 0 ) {
				System.out.println("empty count parameter");
				hasError = true;
				request.setAttribute("EventError", "Please Enter valid event count first!");
			}
			
			if (hasError) {
				doGet(request, response);
				System.out.println("input field is missing");

			} else {

				request.setAttribute("username", UserName);
				request.setAttribute("calanderName", CalName);
				request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
		}
		hasError = false;

	}

}