package com.runner.pkg;

import java.io.File;

public interface ITextHelper {

	TextSearchResponse calculateTextPercent(File f, String searchCriteria)
			throws IllegalArgumentException, InterruptedException;
}
