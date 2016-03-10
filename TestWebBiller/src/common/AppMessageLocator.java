package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;

import org.apache.log4j.Logger;

public class AppMessageLocator implements LabelLocator2 {
	private Logger log = Logger.getLogger(AppMessageLocator.class);
	String filePath = "";

	public AppMessageLocator(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return "UTF-8";
	}

	@Override
	public InputStream locate(Locale arg0) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(this.filePath));
		} catch (FileNotFoundException e) {
			log.error("Application Message File path not found:"+this.filePath);
		}
		return fis;
	}

}
