package leedokchidok.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {

	public Date planDate;
	public String detail;
	public String peoples = "";

	public static Date getDatefromString(String strDate) {

		Date date = null;

		try {
			//java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
			date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println();
		}

		return date;

	}//getDatefromString

	public PlanItem(String date, String detail) {

		this.planDate = getDatefromString(date);
		this.detail = detail;

	}//Planitem

	public Date getDate() {
		return planDate;
	}

	public void addPeople(String name) {
		peoples += name + ",";
	}

	public String saveString() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = formatter.format(planDate);

		return sDate + ",\"" + detail + "\"\n";

	}//saveString

}//Planitem
