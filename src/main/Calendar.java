package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calendar
 */
@WebServlet("/Calendar")
public class Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList x = new ArrayList();
		
		CalendarEventModel c = new CalendarEventModel();
		 c.setId(1);
		 c.setStart("2013-07-28");
		 c.setEnd("2013-07-29");
		 c.setTitle("Task in Progress");
		 
		 CalendarEventModel d =new CalendarEventModel();
		 d.setId(2);
		 d.setStart("2013-07-26");
		 d.setEnd("2013-08-28");
		 d.setTitle("Task in Progress");
		 
		 x.add(c);
		 x.add(d);
		 
		 request.setAttribute("calendar", d);
		 response.sendRedirect("Calander.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
