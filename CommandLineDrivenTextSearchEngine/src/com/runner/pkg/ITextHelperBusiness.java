package com.runner.pkg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ITextHelperBusiness implements ITextHelper {

	private ReentrantLock lock = new ReentrantLock();
	private ExecutorService executorService = Executors.newFixedThreadPool(50);
	private volatile static ITextHelperBusiness iTextHelperBusiness = null;
	
	private ITextHelperBusiness() {
	} 
	@Override
	public List<TextSearchResponse> calculateTextPercent(File file, String searchCriteria) throws IllegalArgumentException, InterruptedException{
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
				lock.lock();
				TextSearchResponse response = new TextSearchResponse();
				response.setTextFileName(f.getName());
				response.setPercentage(TextSearcher.getInstance().prepareBuffer(f).
						searchCriteria(searchCriteria)
						.getPersentageWord());
				textSearchResponses.add(response);
				lock.unlock();
			}
		});
		executorService.shutdownNow();
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		return textSearchResponses;
	}
	
	public static ITextHelperBusiness getInstance() {
		if(iTextHelperBusiness == null) {
			synchronized (ITextHelperBusiness.class) {
				if(iTextHelperBusiness == null) {
					iTextHelperBusiness = new ITextHelperBusiness();
				}
			}
		}
		return iTextHelperBusiness;
	}
	
	
}
