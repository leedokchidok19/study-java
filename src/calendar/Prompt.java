package calendar;

import java.util.Scanner;

public class Prompt {


	/*
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6 = Saturday)
	 * */
	public int parseDay(String week) {
		switch (week) {
		case "su": return 0;
		case "mo": return 1;
		case "tu": return 2;
		case "we": return 3;
		case "th": return 4;
		case "fr": return 5;
		case "sa": return 6;
		default: return 0;
			//throw new IllegalArgumentException("Unexpected value: " + week);
		}
	}

	public void runPrompt() {

		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		System.out.println("반복횟수를 입력하세요");

		int month = 1;
		int year = -1;

		while(true) {

			System.out.println("년도를 입력하세요 (exit: -1)");
			System.out.print("YEAR> ");
			year = scanner.nextInt();

			//종료
			if(year == -1) break;

			System.out.println("달을 입력하세요");
			System.out.print("MONTH> ");
			month = scanner.nextInt();

			//잘못된 입력
			if(month > 12 || month < 1) {
				System.out.println("1~12월 사이로 입력해 주세요");
				continue;
			}

			cal.printCalendar(year, month);

		}//while

		System.out.println("종료되었습니다");

		scanner.close();

	}//runPrompt

	public static void main(String[] args) {

		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();

	}//main

}//Prompt
