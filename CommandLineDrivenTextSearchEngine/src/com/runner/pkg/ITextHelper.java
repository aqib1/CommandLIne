package com.runner.pkg;

import java.io.File;
import java.util.List;

public interface ITextHelper {

	List<TextSearchResponse> calculateTextPercent(File f) throws IllegalArgumentException;
}
