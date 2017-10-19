package events;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreateEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CreateEvent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values
		String eventName = request.getParameter("eventName");
		String date = request.getParameter("date");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String pr = request.getParameter("pr");
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		String seats = request.getParameter("seats");
		String checkTables = request.getParameter("tablesCheck");
		String tables = request.getParameter("tables");
		
		//validate input
		boolean inValidName = (eventName == null) || (eventName.trim().length() == 0);
		boolean inValidDate = (date == null) || (date.trim().length() == 0);
		boolean inValidSTime = (startTime == null) || (startTime.trim().length() == 0);
		boolean inValidETime = (endTime == null) || (endTime.trim().length() == 0);
		boolean inValidPri = (pr == null);
		boolean inValidLocation = (location == null) || (location.trim().length() == 0);
		boolean inValidSeats = (seats == null) || (seats.trim().length() == 0);
		boolean checkTable = checkTables == null;
		boolean inValidTables = (tables == null) || (tables.trim().length() == 0);
		
		//Message so the user knows what they need to do
		if(inValidName || inValidDate || inValidSTime || inValidETime || inValidPri || inValidLocation || inValidSeats) {
			if(inValidName) {
				request.setAttribute("eventError", "Must Enter Valid Event Name");
			}
			if(inValidDate) {
				request.setAttribute("dateError", "Must Enter Valid Date");
			}
			if(inValidSTime) {
				request.setAttribute("timeSError", "Must Enter Starting Time");
			}
			if(inValidETime) {
				request.setAttribute("timeEError", "Must Enter Ending Time");
			}
			if(inValidPri) {
				request.setAttribute("pri", "Must Select Private or Public Event");
			}
			if(inValidLocation) {
				request.setAttribute("locaError", "Must Enter Valid Location");
			}
			if(inValidSeats) {
				request.setAttribute("seatsError", "Must Enter Ammount of Seats");
			}
			if(checkTable) {
				if(inValidTables) {
					request.setAttribute("tableError", "Must Enter Ammount of Tables");
				}
			}
			doGet(request, response);
		}
		
		//save to information somewhere
		
		
		
		
		//redirect somewhere
		
		response.sendRedirect("Member.jsp");
		
		
		
		
		
		
	
		
		
		
		
		
	}

}
