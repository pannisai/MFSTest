package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import mfs.biller.util.AppUtil;

import org.apache.log4j.Logger;


public class BillerInquiryConfig {
	private static Logger log = Logger.getLogger(AppConfig.class);

	private static String FILE_PATH ;
	/* ### For Reload Config File ### */
	private static long lastModified = 0;
	private static long latestLastModified = 0;
	static{
//		FILE_PATH=org.zkoss.lang.Library.getProperty("app.billerinquiryConfig.location");
//		FILE_PATH= "D:\\JoseWork\\Dtac\\config\\billerInquiryWeb\\billerinquiryConfig.properties";
		FILE_PATH=AppConstant.USER_HOME+org.zkoss.lang.Library.getProperty("app.billerinquiryConfig.location");
	}
	private static Properties prop = new Properties();
	
	public final static String MEA_COLUMN_KEY="0001_COLUMN";
	public final static String PEA_COLUMN_KEY="PEA_COLUMN";
	public final static String MWA_COLUMN_KEY="MWA_COLUMN";
	public final static String PWA_COLUMN_KEY="PWA_COLUMN";
	public final static String SSO39_COLUMN_KEY="SSO39_COLUMN";
	public final static String SSO40_COLUMN_KEY="SSO40_COLUMN";
	public final static String GSB_COLUMN_KEY="GSB_COLUMN";
	public final static String TOT_TOT3G_COLUMN_KEY="TOT_TOT3G_COLUMN";
	public final static String DTAC_DTN_COLUMN_KEY="DTAC_DTN_COLUMN";
	
	public final static String MEA_VALUE_KEY="0001_VALUE";
	public final static String PEA_VALUE_KEY="PEA_VALUE";
	public final static String MWA_VALUE_KEY="MWA_VALUE";
	public final static String PWA_VALUE_KEY="PWA_VALUE";
	public final static String SSO39_VALUE_KEY="SSO39_VALUE";
	public final static String SSO40_VALUE_KEY="SSO40_VALUE";
	public final static String GSB_VALUE_KEY="GSB_VALUE";
	public final static String TOT_TOT3G_VALUE_KEY="TOT_TOT3G_VALUE";
	public final static String DTAC_DTN_VALUE_KEY="DTAC_DTN_VALUE";
	
	private static void checkFileForUpdate() {
		try {
			
			File singlePropsFile = new File(FILE_PATH);
			latestLastModified = singlePropsFile.lastModified();
			if (latestLastModified != lastModified) {
				loadConfig();
				lastModified = latestLastModified;
			}
		} catch (Exception e) {
		}
	}


	public static String getValue(String key) {
		checkFileForUpdate();
		if (prop.isEmpty()) {
			loadConfig();
		}
		return prop.getProperty(key);
	}
	public static String searchValue(String key){
		checkFileForUpdate();
		if (prop.isEmpty()) {
			loadConfig();
		}
		String keyStr=null;
		String value=null;

		for(Object oKey:prop.keySet()){
			 keyStr=oKey.toString();
			
			 log.debug(AppUtil.trim(key).indexOf(AppUtil.trim(keyStr)));
			 if(AppUtil.trim(key).indexOf(AppUtil.trim(keyStr))!=-1){
				 value=prop.getProperty(keyStr);
				 break;
			 }
		}
		return value;
	}
	public static void loadConfig() {

		try {
			log.info("Load configuration file::"+FILE_PATH);
			prop.load(new FileInputStream(FILE_PATH));
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}

	}

	
}
