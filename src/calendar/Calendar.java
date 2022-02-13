package calendar;

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
	public static void main(String[] args) {

		Calendar cal = new Calendar();

		System.out.println(cal.getWeekDay(1970, 1, 1) == 3);
		System.out.println(cal.getWeekDay(1971, 1, 1) == 4);
		System.out.println(cal.getWeekDay(1972, 1, 1) == 5);
		System.out.println(cal.getWeekDay(1973, 1, 1) == 0);
		System.out.println(cal.getWeekDay(1974, 1, 1) == 1);
		
	}//main

}//Calendar
