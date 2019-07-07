package com.runner.pkg;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
//		if (args.length == 0) {
//			throw new IllegalArgumentException("No directory given to index.");
//		}
		
		
		final File indexableDirectory = new File("C:\\Users\\AQIB JAVED\\Desktop\\show");
		try (Scanner keyboard = new Scanner(System.in)) {
			while (true) {
				System.out.print("search> ");
				final String line = keyboard.nextLine();
				List<TextSearchResponse> response = ITextHelperBusiness.getInstance().calculateTextPercent(indexableDirectory, line);
				System.out.println(response);
				if(line.equalsIgnoreCase(":quit"))
					break;
			}
		} catch (IllegalArgumentException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("$ - End program");
	}

}
