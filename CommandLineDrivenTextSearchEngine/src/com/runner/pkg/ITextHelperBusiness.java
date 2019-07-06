package com.runner.pkg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ITextHelperBusiness implements ITextHelper {

	private ExecutorService executorService = Executors.newFixedThreadPool(50);
	
	@Override
	public List<TextSearchResponse> calculateTextPercent(File file) throws IllegalArgumentException{
		if(!file.exists()) new IllegalArgumentException("File -> "+ file.getName()
		   +"["+file.getAbsolutePath()+"] not exists..");
		File [] files = null;
		List<TextSearchResponse> textSearchResponses = new ArrayList<>();
		if(file.isFile()) {
			files = new File[] {file};
		} else if(file.isDirectory()) {
			files = file.listFiles();
		}
		if(Objects.isNull(files))
			throw new IllegalArgumentException("file is not in the valid format");
		
		for(File f : files)
		executorService.submit(new Runnable() {	
			@Override
			public void run() {
				TextSearchResponse response = new TextSearchResponse();
				response.setTextFileName(f.getName());
				response.setPercentage(TextSearcher.getInstance().prepareBuffer(f).getPersentageWord());
				textSearchResponses.add(response);
			}
		});
		return textSearchResponses;
	}
	
	
	
	
}
