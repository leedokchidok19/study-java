package calendar;

import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public int maxDaysOfMonth(int month) {
		return MAX_DAYS[month - 1];
	}//maxDaysOfMonth

	public static void main(String[] args) {

		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		String PROMPT = "calendar> ";
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		System.out.println("반복횟수를 입력하세요");

		int month = 1;

		while(true) {

			System.out.println("달을 입력하세요");
			System.out.print(PROMPT);

			month = scanner.nextInt();
			
			if(month  < 1) break;
			
			if(month >= MAX_DAYS.length) {
				System.out.println("1~12월 사이로 입력해 주세요");
				continue;
			}

			System.out.printf("%d월은 %d일까지 있습니다\n", month, cal.maxDaysOfMonth(month));

		}

		System.out.println("종료되었습니다");

		scanner.close();

	}//main

	
}//Calendar
