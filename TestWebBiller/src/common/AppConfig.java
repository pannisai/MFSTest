package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import mfs.biller.util.AppUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import exception.BillerManageWebException;

public class AppConfig {
	private static Logger log = Logger.getLogger(AppConfig.class);

	private static String FILE_PATH;
	/* ### For Reload Config File ### */
	private static long lastModified = 0;
	private static long latestLastModified = 0;
	static {
//		FILE_PATH=org.zkoss.lang.Library.getProperty("app.billerInquiryWebConfig.location");
		FILE_PATH = "C:/Users/csimember/conf/billerInquiryWeb/config/billerInquiryWebConfig.properties";
	   //FILE_PATH=AppConstant.USER_HOME+org.zkoss.lang.Library.getProperty("app.billerInquiryWebConfig.location");
	}

	private static Properties prop = new Properties();

	public final static String INITIAL_CONTEXT_FACTORY_KEY = "INITIAL_CONTEXT_FACTORY";
	public final static String PROVIDER_URL_KEY = "PROVIDER_URL_"+AppConstant.WEBLOGIC_NAME;
	public final static String SECURITY_PRINCIPAL_KEY = "SECURITY_PRINCIPAL_"+AppConstant.WEBLOGIC_NAME;
	public final static String SECURITY_CREDENTIALS_KEY = "SECURITY_CREDENTIALS_"+AppConstant.WEBLOGIC_NAME;
	public final static String BILLER_INQUIRY_COLUMN_KEY = "BILLER_INQUIRY_COLUMN";
	public final static String PAGE_SIZE_KEY = "PAGE_SIZE";
	public final static String CHK_SESS_TIMEOUT_URL = "CHK_SESS_TIMEOUT_URL_"+AppConstant.WEBLOGIC_NAME;
	public final static String CHK_SESS_TIMEOUT_ERR_CODE_KEY = "CHK_SESS_TIMEOUT_ERR_CODE_KEY";
	public final static String CHK_SESS_TIMEOUT_ERR_DESC_KEY = "CHK_SESS_TIMEOUT_ERR_DESC_KEY";
	public final static String CHK_SESS_TIMEOUT_SUCCESS_CODE = "CHK_SESS_TIMEOUT_SUCCESS_CODE";
	public final static String SESS_TIMEOUT_ERR_PAGE_URL = "SESS_TIMEOUT_ERR_PAGE_URL_"+AppConstant.WEBLOGIC_NAME;
	public final static String LOG_PRINT_STACK_TRACE = "LOG_PRINT_STACK_TRACE";
	public final static String SERV_DENOMINATE_PAGE_SIZE = "SERV_DENOMINATE_PAGE_SIZE";
	public final static String GET_PERMISSION_URL = "GET_PERMISSION_URL_"+AppConstant.WEBLOGIC_NAME;
	public final static String GET_PERMISSION_ERR_CODE_KEY = "GET_PERMISSION_ERR_CODE_KEY";
	public final static String GET_PERMISSION_ERR_DESC_KEY = "GET_PERMISSION_ERR_DESC_KEY";
	public final static String SECURITY_DECRYPTED_ENDPOINT = "SECURITY_DECRYPTED_ENDPOINT_"+AppConstant.WEBLOGIC_NAME;

	/* ### Redirect page path### */
		
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
		return AppUtil.trim(prop.getProperty(key));
	}

	public static void loadConfig() {
		String log4jConfPath = "D:/DTAC/Workspace/Biller/TestWebBiller/WebContent/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		try {
			log.info("Load configuration file::" + FILE_PATH);
			prop.load(new FileInputStream(FILE_PATH));
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			throw new BillerManageWebException("File config not found", e);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new BillerManageWebException("Can't load file config", e);
		}

	}
	public static void main(String[] args){
		AppConfig ac=new AppConfig();
	}
}
