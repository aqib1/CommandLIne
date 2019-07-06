package com.runner.pkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextSearcher {

	// Method chaining pattern

	private static TextSearcher textSearcher = null;
	private StringBuffer data = new StringBuffer();
	private double persentageWord = 0;
	private TextSearcher() {

	}

	private int totalWordsInAText(String text) {
		String[] words = text.split("\\s+");
		return words.length;
	}
	
	public double getPersentageWord() {
		return persentageWord;
	}

	public TextSearcher prepareBuffer(File f) {
		if (f.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
				String line;
				while((line=reader.readLine())!= null) {
					data.append(line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return textSearcher;
	}

	public TextSearcher searchCriteria(String searchCriteria) {
		int totalWords = totalWordsInAText(data.toString());
		int totalWordsOfSearchCriteria = totalWordsInAText(searchCriteria);
		int searchCount = getCountBySearchCriteria(data.toString(), searchCriteria);
		if (totalWordsOfSearchCriteria > 1) {
			totalWords -= totalWordsOfSearchCriteria;
		}
		persentageWord = totalWords * (searchCount * 0.01);
		return textSearcher;
	}

	private int getCountBySearchCriteria(String data, String searchCriteria) {
		int fromIndex = 0;
		int count = 0;
		while ((fromIndex = data.indexOf(searchCriteria, fromIndex)) != -1) {
			count++;
			fromIndex++;

		}
		return count;
	}

	public static TextSearcher getInstance() {
		textSearcher = new TextSearcher();
		return textSearcher;
	}

}
