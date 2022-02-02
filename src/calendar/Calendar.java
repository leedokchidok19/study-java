package calendar;

import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {

		int a, b;
		//new Scanner(File)
		Scanner scanner = new Scanner(System.in);//System.in == keyboard

		String s1, s2;

		System.out.println("두 수를 입력해 주세요.");

		s1 = scanner.next();
		s2 = scanner.next();

		a = Integer.parseInt(s1);
		b = Integer.parseInt(s2);

		System.out.printf("%d와 %d의 합은 %d입니다", a, b, a+b);

		//스캐너 사용 후 종료 필수 Resource leak: 'scanner' is never closed
		scanner.close();
		
	}//main

}//Calender
