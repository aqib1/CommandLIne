package com.runner.pkg;

public class TextSearchResponse {

	private String textFileName;
	private double percentage;

	public String getTextFileName() {
		return textFileName;
	}

	public void setTextFileName(String textFileName) {
		this.textFileName = textFileName;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "TextSearchResponse [textFileName=" + textFileName + ", percentage=" + percentage + "]";
	}

}
