package Calander;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTable")
public class AddTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ArrayList<Table> tables = new ArrayList<Table>();
	private Boolean hasError = false;
	private Integer id, cid;
	private String name = "";
	private Integer amount;
	private Integer seats;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("eid") != null) {
			id = Integer.parseInt(request.getParameter("eid"));
		}

		if (request.getParameter("cid") != null) {
			cid = Integer.parseInt(request.getParameter("cid"));
		}
		System.out.println("event id: " + id);

		if (request.getParameter("name") != null) {
			name = request.getParameter("name");
		}

		request.getRequestDispatcher("Calendar/AddTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		amount = Integer.parseInt(request.getParameter("amount"));
		System.out.println("amount: " + amount);
		seats = Integer.parseInt(request.getParameter("seats"));
		System.out.println("seats: " + seats);
		System.out.println("name : " + name);

		if (id == null || id.SIZE == 0) {
			hasError = true;
			request.setAttribute("idError", "Please Enter table amount first.");
		}
		if (name == null || name.trim().length() == 0) {
			hasError = true;
			request.setAttribute("nameError", "Please Enter table amount first.");
		}
		if (amount == null || amount.SIZE == 0) {
			hasError = true;
			request.setAttribute("amountError", "Please Enter table amount first.");
		}
		if (seats == null || seats.SIZE == 0) {
			hasError = true;
			request.setAttribute("seatsError", "Please Enter seats per table first.");
		}

		if (hasError) {
			// doGet(request, response);
			System.out.println("error");
			hasError = false;
		} else {
			Connection c = null;
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";
			String sql = "INSERT INTO tables (eventId, cid, eventName, tableAmount, seatsPerTable) VALUES ('" + id
					+ "','" + cid + "','" + name + "','" + amount + "','" + seats + "')";

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				PreparedStatement ps = c.prepareStatement(sql);
				ps.executeUpdate();
				request.getRequestDispatcher("CreateTables").forward(request, response);
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
