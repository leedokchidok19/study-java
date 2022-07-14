package leedokchidok.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final String SAVE_FILE = "calendar.dat";

	private HashMap<Date, PlanItem> planMap;

	//생성자로 planMap 초기화
	public Calendar() {

		planMap = new HashMap<Date, PlanItem>();
		//데이터 불러오기
		File f = new File(SAVE_FILE);

		//파일이 없는 경우 불러오지 않는다
		if (!f.exists()) {
			System.err.println("no save file");
			return;
		}

		//파일이 있을 경우 불러오기
		try {

			Scanner s = new Scanner(f);
			while(s.hasNext()) {
				String line = s.nextLine();
				String[] words = line.split(",");
				String date = words[0];
				String detail = words[1].replaceAll("\"", "");
				//System.out.println("date:"+date+"detail:"+detail);

				PlanItem p = new PlanItem(date, detail);
				planMap.put(p.getDate(), p);

			}//while

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//Calendar

	/*
	 * @param date ex: "2017-06-20"
	 * @param paln
	 * */
	public void registerPlan(String strDate, String plan) {

		PlanItem p = new PlanItem(strDate, plan);
		planMap.put(p.getDate(), p);

		File f = new File(SAVE_FILE);
		String item = p.saveString();

		try {
			//f: 입력 정보, true: append 속성
			FileWriter fw = new FileWriter(f, true);//true 생략할 경우 덮어씌우기
			//파일 불러오는 형식 지정 - CSV: ","로 구분되는 형식
			fw.write(item);
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//registerPlan

	public PlanItem searchPlan(String strDate) {

		Date date = PlanItem.getDatefromString(strDate);
		return planMap.get(date);

	}//searchPlan

	public boolean isLeapYear(int year) {

		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) )
			return true;
		else
			return false;
	}

	public int getMaxDaysOfMonth(int year,  int month) {
		if(isLeapYear(year))
			return LEAP_MAX_DAYS[month - 1];
		else
			return MAX_DAYS[month - 1];
	}//maxDaysOfMonth


	public void printCalendar(int year, int month) {

		// %4d 4칸, %3d 3칸 의미
		System.out.printf("  <<%d년 %d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

		//get weekday automatically - 젤러 공식, 둠스데이 검색
		int weekday = getWeekDay(year, month, 1);
		//print blank space
		for(int i =0; i < weekday; i++) {
			System.out.printf("   ");
		}

		int maxDay= getMaxDaysOfMonth(year, month);
		int count = 7 - weekday;
		int delim = ( count < 7 ) ? count : 0;
		/* int delim = ( count < 7 ) ? count : 0;
		 * int delim;
		 * if(count < 7) {
		 * 	delim = count;
		 * } else {
		 * 	delim = 0;
		 * }
		 * */

		//print first line
		for(int i = 1; i <= count; i++) {
			System.out.printf("%3d", i);
		}

		System.out.println();

		//print from second line to last
		count++;//다음 줄도 동일한 숫자가 나오기 때문에 더하기 1을 해준다
		for(int i = count; i <= maxDay; i++) {

			System.out.printf("%3d", i);
			if(i % 7 == delim) System.out.println();

		}//for

		System.out.println("\n");

	}//printSampleCalendar

	private int getWeekDay(int year, int month, int day) {

		int standardYear = 1970;
		final int STANDAR_WEEKDAY = 4; //1970/Jan/1st Thursday 1970년 1월 1일 목요일

		int count = 0;

		for(int i = standardYear; i <  year; i++) {

			int delta = isLeapYear(i) ? 366 : 365;
			count += delta;

		}

		for(int i = 1; i <  month; i++) {
			
			int delta = getMaxDaysOfMonth(year, i);
			count += delta;
			
		}

		count += day - 1;

		int weekday = (count+STANDAR_WEEKDAY) % 7;
		return weekday;
	}

	//simple test code here
	public static void main(String[] args) throws ParseException {

		Calendar cal = new Calendar();

		System.out.println(cal.getWeekDay(1970, 1, 1) == 4);
		System.out.println(cal.getWeekDay(1971, 1, 1) == 5);
		System.out.println(cal.getWeekDay(1972, 1, 1) == 6);
		System.out.println(cal.getWeekDay(1973, 1, 1) == 1);
		System.out.println(cal.getWeekDay(1974, 1, 1) == 2);

		cal.registerPlan("2022-02-20", "Let's eat beaf!");
		System.out.println("6. "+cal.searchPlan("2022-02-20").equals("Let's eat beaf!"));

	}//main

}//Calendar
