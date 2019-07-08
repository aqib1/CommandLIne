package com.runner.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.runner.pkg.ITextHelperBusiness;
import com.runner.pkg.TextSearchResponse;

public class TextSearchTest {

	@Test
	public void testTextPercent() throws IllegalArgumentException, InterruptedException {
		File file = new File("textfile/test");
		TextSearchResponse response = ITextHelperBusiness.getInstance().calculateTextPercent(file, "aqib");
		Assert.assertEquals(response.getPercentage(), 18.181818181818183, 0.0f);
		Assert.assertEquals(response.getTextFileName(), "test");
	}
	
	@Test
	public void testTextPercent100() throws IllegalArgumentException, InterruptedException {
		File file = new File("textfile/textfull");
		TextSearchResponse response = ITextHelperBusiness.getInstance().calculateTextPercent(file, "aqib");
		Assert.assertEquals(response.getPercentage(), 100, 0.0f);
		Assert.assertEquals(response.getTextFileName(), "textfull");
	}
	
	@Test
	public void testTextPercentZero() throws IllegalArgumentException, InterruptedException {
		File file = new File("textfile/test");
		TextSearchResponse response = ITextHelperBusiness.getInstance().calculateTextPercent(file, "ahmed");
		Assert.assertEquals(response.getPercentage(), 0.0, 0.0f);
		Assert.assertEquals(response.getTextFileName(), "test");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fileNotFound() throws IllegalArgumentException, InterruptedException {
		File file = new File("textfile/abc");
		ITextHelperBusiness.getInstance().calculateTextPercent(file, "aqib");
	}
}
