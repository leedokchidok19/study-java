package calendar;

import java.util.Scanner;

public class Prompt {


	public final static String PROMPT = "calendar> ";

	public void runPrompt() {

		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		System.out.println("반복횟수를 입력하세요");

		int month = 1;
		int year = -1;

		while(true) {

			System.out.println("년도를 입력하세요");
			System.out.print("YEAR> ");
			year = scanner.nextInt();
			System.out.println("달을 입력하세요");
			System.out.print("MONTH> ");
			month = scanner.nextInt();
					
			if(month < 1) break;
					
			if(month > 12) {
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
