package leedokchidok.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMove {

	public static void main(String[] args) throws IOException {

		
		//파일의 현재 위치
		String currentDirectory	= null;
		//예) String currentDirectory = "C:/Users/folderName/";
		//파일이 이동하는 위치
		String moveToDirectory	= "C:/Users/Lee/Desktop/movetotest11/";

		//파일 목록
		String[] fileNameList = {"text.txt", "text - 복사본.txt", "Alphabet123.txt", "특수문자!@#$.txt"};

		//파일 초기화
		File currentFile= null;
		File moveToFile	= null;


		for (String fileName : fileNameList) {

			currentFile	= new File(currentDirectory	+ fileName);
			moveToFile	= new File(moveToDirectory	+ fileName);
			
			//IOException
			Path result = Files.copy(currentFile.toPath(), moveToFile.toPath());

			System.out.println("Path : "+ result);
		}
	}

}
