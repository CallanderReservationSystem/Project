package Calander;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalendarReservationModel;
import models.Table;

import java.sql.*;
import java.util.ArrayList;

@WebServlet("/RegisterForEvent")
public class RegisterForEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Table> tables = new ArrayList<Table>();
	private ArrayList<Table> tables2 = new ArrayList<Table>();
	ArrayList<Integer> reservationCount = new  ArrayList<Integer>();;
//	private ArrayList<Table> optimalTables = new ArrayList<Table>();
	private ArrayList<CalendarReservationModel> currentReservationsAtTargetTime = new ArrayList<CalendarReservationModel>();
	private ArrayList<String> sqlArray = new ArrayList<String>();
	private ArrayList<ResultSet> resultSets = new ArrayList<ResultSet>();
	private Integer avaliableTablesAmount = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = null;

		Integer id = null;
		Integer userId = null;
		String eventName = null;
		String eventId = (String) request.getParameter("id");
		// System.out.println(+eventId);

		String user_name = (String) request.getSession().getAttribute("Username");
		// System.out.println(user_name);
		String location = null;
		String start = null;
		String end = null;

		String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
		String SQLuser = "cs3337stu03";
		String SQLpass = "K!c7YAg.";
		String sql = "SELECT * FROM events WHERE id = " + eventId;
		String sql2 = "SELECT * FROM tables WHERE eventId =" + eventId;

		try {
			c = DriverManager.getConnection(url, SQLuser, SQLpass);

			// for first sql query
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// for second sql query
			Statement st2 = c.createStatement();
			ResultSet rs2 = st2.executeQuery(sql2);

			while (rs.next()) {
				id = rs.getInt("id");
				userId = rs.getInt("uid");
				eventName = rs.getString("title");
				location = rs.getString("location");
			}

			tables2.clear();
			while (rs2.next()) {
				Integer tableId = rs2.getInt("id");
				Integer tableEventId = rs2.getInt("eventId");
				Integer cid = rs2.getInt("cid");
				Integer tableAmount = rs2.getInt("tableAmount");
				Integer seatsPerTable = rs2.getInt("seatsPerTable");
				String tableEventName = rs2.getString("eventName");

				tables2.add(new Table(tableId, tableEventId, cid, tableEventName, tableAmount, seatsPerTable));
			}

			System.out.println("Amount of tables in Table List: " + tables2.size());
			request.setAttribute("eventTable", tables2);
			request.setAttribute("user_name", user_name);
			request.setAttribute("eventName", eventName);
			request.setAttribute("id", id);
			request.setAttribute("title", eventName);
			request.setAttribute("start", start);
			request.setAttribute("end", end);

			request.getRequestDispatcher("/Calendar/RegisterForEvent.jsp").forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String reservation_name = request.getParameter("name");
		String username = request.getParameter("user_name");
		request.setAttribute("user_name", username);
		String details = request.getParameter("details");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Integer numOfPeople;
		if (request.getParameter("numOfPeople") == null || request.getParameter("numOfPeople").trim().length() == 0) {
			numOfPeople = null;
		} else {
			numOfPeople = Integer.parseInt(request.getParameter("numOfPeople"));
		}

		Integer id = Integer.parseInt(request.getParameter("id"));
		// Integer calId = Integer.parseInt(request.getParameter("calId"));
		Integer userId = (Integer) request.getSession().getAttribute("ssuid");

		boolean inValidName = (reservation_name == null) || (reservation_name.trim().length() == 0);
		boolean inValidUName = (username == null) || (username.trim().length() == 0);
		boolean inValidStartDate = (startDate == null) || (startDate.trim().length() == 0);
		boolean inValidEndDate = (endDate == null) || (endDate.trim().length() == 0);
		boolean inValidSTime = (startTime == null) || (startTime.trim().length() == 0);
		boolean inValidETime = (endTime == null) || (endTime.trim().length() == 0);
		boolean inValidnumOfPeople = (numOfPeople == null);

		// Checks to see if there are any blank or incorrect forms
		if (inValidName || inValidStartDate || inValidEndDate || inValidSTime || inValidETime || inValidnumOfPeople) {
			if (inValidName) {
				request.setAttribute("nameError", "Must Enter Name for Reservation.");
			}
			if (inValidStartDate || inValidEndDate) {
				request.setAttribute("dateError", "Either your Begining Date, End Date, or both are invalid. ");
			}
			if (inValidSTime || inValidETime) {
				request.setAttribute("timeError", "Either your Begining Time, End Time, or both are invalid.");
			}
			if (inValidnumOfPeople) {
				request.setAttribute("numOfPeopleError", "Must Enter a Party Size.");
			}
			doGet(request, response);
		} else {
			// Integer
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3337stu03";
			String SQLuser = "cs3337stu03";
			String SQLpass = "K!c7YAg.";

			Integer avaliableTables = null;
			Integer selectedTableId = null;
			Integer selectedCalId = null;
			Integer selectedUserId = (Integer) request.getSession().getAttribute("ssuid");
			Integer selcetedEventId = null;

			Connection c = null;
			Table optimalTable = null;

			// Selects a set of tables that exsist in the event that have at most 4 more
			// seats then the size of the party.
			try {
				String sql = "SELECT * FROM tables WHERE eventId =" + id + " AND seatsPerTable >=" + numOfPeople
						+ " AND seatsPerTable <" + (numOfPeople + 4);
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql);

				tables.clear();
				while (rs.next()) {

					Integer tableId = rs.getInt("id");
					Integer eventId = rs.getInt("eventId");
					Integer cid = rs.getInt("cid");
					String eventName = rs.getString("eventName");
					Integer tableAmount = rs.getInt("tableAmount");
					Integer seatsPerTable = rs.getInt("seatsPerTable");
					tables.add(new Table(tableId, eventId, cid, eventName, tableAmount, seatsPerTable));
				}
				System.out.println("This is the number of table objects found whose seat count is >= " + numOfPeople
						+ " : " + tables.size());

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

			try {
				
				Statement st = null;
				ResultSet rs = null;
				Statement st2 = null;
				ResultSet rs2 = null;
				c = DriverManager.getConnection(url, SQLuser, SQLpass);

				for (int counter = tables.size() - 1; counter >= 0; counter--) {
					System.out.println("This is the amount of seatsPerTable for each itteration of the table Array: "+ tables.get(counter).getSeatsPerTable());
					if (tables.get(counter).getSeatsPerTable() >= numOfPeople) {
						System.out.println("Number of people in party:" + numOfPeople);
						System.out.println(tables.get(counter).getSeatsPerTable());
						optimalTable = tables.get(counter);

						String sql = "SELECT * FROM tables WHERE id = " + optimalTable.getId();
						 st = c.createStatement();
						 rs = st.executeQuery(sql);
						
						while (rs.next()) {
							avaliableTablesAmount = rs.getInt("tableAmount");
							selectedTableId = rs.getInt("id");
							selectedCalId = rs.getInt("cId");
							selcetedEventId = rs.getInt("eventId");
						}
						
						String sql2 =  "SELECT tableId FROM reservations WHERE tableId =" + optimalTable.getId();
						 st2 = c.createStatement();
						 rs2 = st2.executeQuery(sql2);
						
						 reservationCount.clear();
						while (rs2.next()) {
							Integer reservationId = rs2.getInt("tableId");
							reservationCount.add(reservationId);
						}
						System.out.println(avaliableTablesAmount);
						System.out.println(reservationCount.size());
						System.out.println(avaliableTablesAmount >= reservationCount.size());
						if(avaliableTablesAmount > reservationCount.size()) {
							break;
						}

					} 
					numOfPeople++;
					System.out.println(numOfPeople);
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

//			try {
//				
//				String sql = "SELECT * FROM tables WHERE tableId =" + optimalTable.getId();
//				c = DriverManager.getConnection(url, SQLuser, SQLpass);
//				ResultSet rs = c.createStatement().executeQuery(sql);
//				
//				while (rs.next()) {
//					selectedTableId = null;
//					selectedCalId = null;
//					selcetedEventId = null;
//					
//				}
				
				
//				optimalTable = optimalTable(numOfPeople, c);
//
//				String sql = "SELECT tableAmount FROM tables WHERE id =" + optimalTable.getId();
//				c = DriverManager.getConnection(url, SQLuser, SQLpass);
//				ResultSet rs = c.createStatement().executeQuery(sql);
//
//				while (rs.next()) {
//					avaliableTablesAmount = rs.getInt("tableAmount");
//
//				}
//			} catch (SQLException e) {
//				throw new ServletException(e);
//			} finally {
//				try {
//					if (c != null)
//						c.close();
//				} catch (SQLException e) {
//					throw new ServletException(e);
//				}
//			}

			try {

				String sql = "SELECT * FROM reservations WHERE tableId =" + optimalTable.getId();
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				ResultSet rs = c.createStatement().executeQuery(sql);
				currentReservationsAtTargetTime.clear();
				while (rs.next()) {
					Integer targetCalId = rs.getInt("calId");
					Integer targetEventId = rs.getInt("eventId");
					Integer targetUserId = rs.getInt("userId");
					Integer targetTableId = rs.getInt("tableId");
					String targetStartTime = rs.getString("start_time");
					String targetEndTime = rs.getString("end_time");
					String targetStartDate = rs.getString("start_date");
					String targetEndDate = rs.getString("end_date");
					String targetUserName = rs.getString("user_name");
					String targetDetails = rs.getString("details");

					currentReservationsAtTargetTime.add(new CalendarReservationModel(targetStartTime, targetEndTime,
							targetStartDate, targetEndDate, targetUserName, targetDetails, targetCalId, targetEventId,
							targetUserId, targetTableId));
					System.out.println("This is the amount of reservation objects there are on the server:"
							+ currentReservationsAtTargetTime.size());
				}
				System.out.println("This is the amount of reservation objects there are on the server:"
						+ currentReservationsAtTargetTime.size());
				for (CalendarReservationModel crm : currentReservationsAtTargetTime) {
					String sql4 = "SELECT * FROM reservations WHERE '" + startTime + ":00' BETWEEN '"
							+ crm.getStart_time() + "' AND '" + crm.getEnd_time() + "' OR '" + endTime
							+ ":00' BETWEEN '" + crm.getStart_time() + "' AND '" + crm.getEnd_time() + "' OR '"
							+ startTime + ":00' >= '" + crm.getStart_time() + "' AND '" + endTime + "<= "
							+ crm.getEnd_time() + "' OR '" + startTime + ":00' <= '" + crm.getStart_time() + "' AND '"
							+ endTime + ":00' >= '" + crm.getEnd_time() + "'";
					sqlArray.add(sql4);
				}
				System.out.println("This is the size of the sqlStatement Array:" + sqlArray.size());

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

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				Statement st = null;
				ResultSet rs = null;
				resultSets.clear();
				for (String s : sqlArray) {
					String SQL = s;
					st = c.createStatement();
					rs = st.executeQuery(SQL);
					resultSets.add(rs);
				}

				System.out.println("this is the size of the result set array: " + resultSets.size());
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

			try {
				c = DriverManager.getConnection(url, SQLuser, SQLpass);
				System.out.println("This is the amount of available tables: " + avaliableTablesAmount);
				System.out.println("This is the size of the result set array: " + resultSets.size());
				if (resultSets.size() > avaliableTablesAmount) {
					request.setAttribute("timeConflictError",
							"There seems to be no tables availabe in the time slot you wanted. Please enter another time slot.");
					doGet(request, response);
				} else {
					String sql5 = "INSERT INTO reservations (calId,eventId,userId,tableId,start_time,end_time,start_date,end_date,user_name,reservation_name,details) VALUES "
							+ "('" + selectedCalId + "','" + selcetedEventId + "','" + selectedUserId + "','"
							+ selectedTableId + "','" + startTime + "','" + endTime + "','" + startDate + "','"
							+ endDate + "','" + username + "','" + reservation_name + "','" + details + "')";
					c.createStatement().executeUpdate(sql5);
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
