package calendar;

import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

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

	/*
	 * - 월을 입력하면 해당월의 달력을 출력한다.
	 * - 달력은 모양은 1단계에서 작성한 모양으로 만든다.
	 * - 1일은 일요일로 정해도 무관하다.
	 * - -1을 입력받기 전까지 반복 입력받는다.
	 * - 프롬프트를 출력한다.
	 * */

	public void printCalendar(int year, int month, int weekday) {
System.out.println(weekday);
		// %4d 4칸, %3d 3칸 의미
		System.out.printf("  <<%4d년%3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

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

}//Calendar
