package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class RegisterForEvent
 */
@WebServlet("/RegisterForEvent")
public class RegisterForEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterForEvent() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection c = null;
		
		Integer id = null;
		Integer userId = null;
		String eventName = null;
		Integer calId = Integer.parseInt(request.getParameter("id"));
		System.out.println(calId);
		String location = null;
		String start = null;
		String end = null;
		
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "select * from events where cid = " + calId; // event is placeholder name of the event that the user wants to register for
		
		try
		{
			c = DriverManager.getConnection(url, SQLuser, SQLpass);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				id = rs.getInt("id");
				userId = rs.getInt("uid");
				eventName = rs.getString("title");
				location = rs.getString("location");
				start = rs.getString("start");
				end = rs.getString("end");
			}
			request.setAttribute("id", id);
			request.setAttribute("location", location);
			request.setAttribute("start", start);
			request.setAttribute("end", end);

			request.getRequestDispatcher("RegisterForEvent.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
