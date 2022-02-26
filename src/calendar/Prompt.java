package calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

	public void printMenu() {
		System.out.println("+---------------+");
		System.out.println("│ 1. 일정 등록		│");
		System.out.println("│ 2. 일정 검색		│");
		System.out.println("│ 3. 달력 보기		│");
		System.out.println("│ h. 도움말		│");
		System.out.println("│ q. 종료			│");
		System.out.println("+---------------+");
	}

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
		}//switch
	}//parseDay

	public void runPrompt() throws ParseException {

		printMenu();
		//숫자를 입력받아 해당하는 달의 최대 일수를 출력하는 프로그램
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		boolean isLoop = true;
		while(isLoop) {

			System.out.println("명령(1, 2, 3, h, q)");
			String cmd = scanner.next();

			switch (cmd) {
			case "1":
				cmdRegister(scanner, cal);
				break;
			case "2":
				cmdSearch(scanner, cal);
				break;
			case "3":
				cmdCal(scanner, cal);
				break;
			case "h":
				printMenu();
				break;
			case "q":
				isLoop = false;
				break;
			}//switch

		}//while

		System.out.println("종료되었습니다");
		scanner.close();

	}//runPrompt

	private void cmdCal(Scanner s, Calendar c) {

		int month = 1;
		int year = -1;

		System.out.println("년도를 입력하세요.");
		System.out.print("YEAR> ");
		year = s.nextInt();

		System.out.println("달을 입력하세요");
		System.out.print("MONTH> ");
		month = s.nextInt();

		//잘못된 입력
		if(month > 12 || month < 1) {
			System.out.println("1~12월 사이로 입력해 주세요");
			return;
		}

		c.printCalendar(year, month);
		
	}//cmdCal

	private void cmdSearch(Scanner s, Calendar c) {

		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해주세여 (yyyy-MM-dd)");

		String date = s.next();
		PlanItem plan = c.searchPlan(date);

		if(plan != null) System.out.println(plan.detail);
		else System.out.println("일정이 없습니다.");

	}//cmdSearch

	private void cmdRegister(Scanner s, Calendar c) throws ParseException {

		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해주세여 (yyyy-MM-dd)");

		String date = s.next();
		String text = "";
		System.out.println("일정을 입력해 주세요(끝문자=;)");
		String word;
		while(!(word = s.next()).endsWith(";")) {
			text+= word + " ";
		}

		word = word.replace(";", "");
		text += word;

/*	입력이 제대로 안되는 문제 발생
 	한영 전환 등
		System.out.println("일정을 입력해 주세요(문장의 끝에 ;을 입력해 주세요)");

		while(true) {
			String word = s.next();
			text += word + " ";
			if(word.endsWith(";")) break;
		}
*/
		c.registerPlan(date, text);
		
	}//cmdRegister

	public static void main(String[] args) throws ParseException {

		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();

	}//main

}//Prompt
