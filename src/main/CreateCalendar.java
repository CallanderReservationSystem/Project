package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.dhtmlx.planner.*;
//import com.dhtmlx.planner.data.*;

@WebServlet("/CreateCalendar")
public class CreateCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CalendarEventModel> x = new ArrayList<CalendarEventModel>();

		CalendarEventModel c = new CalendarEventModel();
		c.setId(1);
		c.setStart("2013-07-28");
		c.setEnd("2013-07-29");
		c.setTitle("Task in Progress");

		CalendarEventModel d = new CalendarEventModel();
		d.setId(2);
		d.setStart("2013-07-26");
		d.setEnd("2013-08-28");
		d.setTitle("Task in Progress");

		x.add(c);
		x.add(d);

		request.setAttribute("calendar", x);
		request.getRequestDispatcher("Calendar.jsp").forward(request, response);
		response.sendRedirect("Calendar.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// String getPlanner(HttpServletRequest request) throws Exception {
	//
	// HttpSession session = request.getSession();
	// int uid = (Integer) session.getAttribute("ssuid");
	// CalendarSetting cs = new CalendarSetting();
	// DHXPlanner s;
	//
	// s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
	//
	// //change calendar skins
	// String ch3 = cs.skinChange(uid);
	//
	// if (ch3.equals("sk1")) {
	// s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
	//
	// } else if (ch3.equals("sk2")) {
	//
	// s = new DHXPlanner("./codebase/", DHXSkin.CLASSIC);
	//
	// } else if (ch3.equals("sk3")) {
	//
	// s = new DHXPlanner("./codebase/", DHXSkin.GLOSSY);
	//
	// } else if (ch3.equals("sk4")) {
	//
	// }
	//
	// s.setWidth(1000);
	//
	// s.setInitialDate(2015, 00, 01);
	//
	// s.lightbox.clear();
	//
	// //change the language
	//
	// String LNG = cs.changeLanguage(uid);
	//
	// if (LNG.equals("Arabic")) {
	// s.localizations.set(DHXLocalization.Arabic);
	//
	// } else if (LNG.equals("English")) {
	// s.localizations.set(DHXLocalization.English);
	//
	// } else if (LNG.equals("French")) {
	// s.localizations.set(DHXLocalization.French);
	//
	// } else if (LNG.equals("German")) {
	// s.localizations.set(DHXLocalization.German);
	//
	// } else if (LNG.equals("Greek")) {
	// s.localizations.set(DHXLocalization.Greek);
	//
	// } else if (LNG.equals("Italian")) {
	// s.localizations.set(DHXLocalization.Italian);
	//
	// } else if (LNG.equals("Japanese")) {
	// s.localizations.set(DHXLocalization.Japanese);
	//
	// } else if (LNG.equals("Russian")) {
	// s.localizations.set(DHXLocalization.Russian);
	//
	// } else if (LNG.equals("Spanish")) {
	// s.localizations.set(DHXLocalization.Spanish);
	//
	// }
	//
	// //add tooltips for events
	// s.extensions.add(DHXExtension.TOOLTIP);
	//
	// // add event details field
	// DHXLightboxText notes = new DHXLightboxText("text", "Event Description");
	// notes.setHeight(40);
	// s.lightbox.add(notes);
	//
	// //set Mini calendar giving time period in the lightbox
	// s.lightbox.add(new DHXLightboxMiniCalendar("name", "Time Period"));
	// //set recurring events in lightbox
	// s.lightbox.add(new DHXLightboxRecurringBlock("event_length",
	// "Recurring"));
	// //set event length in lightbox
	// s.lightbox.add(new DHXLightboxTime("time", "Event Length"));
	//
	// //set event colors
	// DHXLightboxSelect select = new DHXLightboxSelect("color", "Event type");
	// select.addOption(new DHXLightboxSelectOption("", ""));
	// select.addOption(new DHXLightboxSelectOption("#FF6699", "Official"));
	// select.addOption(new DHXLightboxSelectOption("#E6B800", "Business"));
	// select.addOption(new DHXLightboxSelectOption("#999966", "Education"));
	// select.addOption(new DHXLightboxSelectOption("#8e671e", "Social"));
	// select.addOption(new DHXLightboxSelectOption("#76b007", "Family"));
	// select.addOption(new DHXLightboxSelectOption("#1796b0", "Other"));
	// s.lightbox.add(select);
	//
	// //Highlight important events
	// DHXLightboxCheckbox check = new DHXLightboxCheckbox("highlighting",
	// "Important Event");
	// check.setMapTo("textColor");
	// check.setCheckedValue("black");
	// s.lightbox.add(check);
	//
	// //Enables autoresizing for the scheduler container
	//
	// s.extensions.add(DHXExtension.CONTAINER_AUTORESIZE);
	//
	// //Enables the keyboard navigation.
	//
	// s.extensions.add(DHXExtension.KEYBOARD_NAVIGATION);
	//
	// //export to PDF
	//
	// String ch1 = cs.activepdf(uid);
	// if (ch1.equals("pdfA")) {
	//
	// s.extensions.add(DHXExtension.PDF);
	// s.toPDF();
	// } else {
	// s.extensions.clear();
	// }
	//
	// String ch2 = cs.activeminc(uid);
	//
	// if (ch2.equals("mcA")) {
	//
	// s.calendars.attachMiniCalendar();
	// } else {
	// s.calendars.clear();
	// }
	//
	// //export to ical
	//
	// String ical = cs.activeIcal(uid);
	//
	// if (ical.equals("icA")) {
	// s.extensions.add(DHXExtension.ICAL);
	// s.toICal("ical.jsp");
	// }
	//
	// //connect to map
	//
	// DHXMapView view = new DHXMapView();
	// String map = cs.activeMap(uid);
	//
	// if (map.equals("mapA")) {
	// s.views.add(view);
	// }
	//
	// //Agenda
	//
	// String agn = cs.activeAgenda(uid);
	// if (agn.equals("agA")) {
	//
	// s.views.add(new DHXAgendaView());
	// }
	//
	// //s.templates.map_text = function(start,end,ev){
	// // return ev.text;
	// //};
	//
	// //Limiting the number of events per cell in the Month view
	// s.config.setMaxMonthEvents(3);
	//
	// s.load("events.jsp", DHXDataFormat.JSON);
	// s.data.dataprocessor.setURL("events.jsp");
	//
	// return s.render();
	//
	// }

}
