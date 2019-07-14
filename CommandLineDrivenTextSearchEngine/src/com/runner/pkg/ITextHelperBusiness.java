package com.runner.pkg;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class ITextHelperBusiness implements ITextHelper {

	private ReentrantLock lock = new ReentrantLock();
	private Thread t1 = null;
	private volatile static ITextHelperBusiness iTextHelperBusiness = null;

	private ITextHelperBusiness() {
	}

	@Override
	public TextSearchResponse calculateTextPercent(File file, String searchCriteria)
			throws IllegalArgumentException, InterruptedException {
		if (!file.exists())
			new IllegalArgumentException("File -> " + file.getName() + "[" + file.getAbsolutePath() + "] not exists..");
		File[] files = null;
		TextSearchResponse response = new TextSearchResponse();
		if (file.isFile()) {
			files = new File[] { file };
		} else if (file.isDirectory()) {
			files = file.listFiles();
		}
		if (Objects.isNull(files))
			throw new IllegalArgumentException("file is not in the valid format");

		for (File f : files) {
			t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					response.setTextFileName(f.getName());
					response.setPercentage(TextSearcher.getInstance().prepareBuffer(f).searchCriteria(searchCriteria)
							.getPersentageWord());
					lock.unlock();
				}
			});
		t1.start();
		t1.join();
	   }
		return response;
	}

	public static ITextHelperBusiness getInstance() {
		if (iTextHelperBusiness == null) {
			synchronized (ITextHelperBusiness.class) {
				if (iTextHelperBusiness == null) {
					iTextHelperBusiness = new ITextHelperBusiness();
				}
			}
		}
		return iTextHelperBusiness;
	}

}
