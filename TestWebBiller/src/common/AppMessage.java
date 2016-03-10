package common;
import java.io.File;

import exception.BillerManageWebException;

public class AppMessage {

	/* ### For Reload Config File ### */
	private static long lastModified = 0;
	private static long latestLastModified = 0;

	public final static String SELECTBOX_ALL = "SELECTBOX_ALL";
	public final static String SELECTBOX_SELECT = "SELECTBOX_SELECT";
	public final static String SELECTBOX_CREATE_NEW = "SELECTBOX_CREATE_NEW";

	public final static String MSG_SAVE_CONFIRM = "SAVE_CONFIRM";
	public final static String MSG_RESET_CONFIRM = "RESET_CONFIRM";
	public final static String MSG_SAVE_SUCCESS = "SAVE_SUCCESS";
	public final static String MSG_SAVE_FAIL = "SAVE_FAIL";
	public final static String MSG_CORRECT = "MSG_CORRECT";
	public final static String MSG_SAVE_BILLER_SERVICE_BEFORE = "SAVE_BILLER_SERVICE_BEFORE";
	public final static String MSG_CREATE_INPUT_FORM_BEFORE = "CREATE_INPUT_FORM_BEFORE";

	/* ### Title ### */
	
	public static final String BILLER_INQUIRY_SEARCH_TITLE = "BILLER_INQUIRY_SEARCH_TITLE";
	public static final String FILEDOWNLOAD_INQUIRY_TITLE = "FILEDOWNLOAD_INQUIRY_TITLE";
	
	/* ## Validation Message ## */
	public static final String MSG_STARTDATE_MUST_BEFORE_ENDDATE = "STARTDATE_MUST_BEFORE_ENDDATE";
	public static final String MSG_ENDDATE_MUST_AFTER_STARTDATE = "ENDDATE_MUST_AFTER_STARTDATE";
	public static final String MSG_DATA_DUPLICATE = "DATA_DUPLICATE";
	public static final String MSG_DATA_EMPTY = "DATA_EMPTY";
	public static final String MSG_NUMBER_ONLY = "NUMBER_ONLY";
	public static final String MSG_MUST_SELECT = "MUST_SELECT";
	public static final String MSG_TAX_ID_FORMAT_ONLY = "TAX_ID_FORMAT_ONLY";
	public static final String MSG_INVALID_XML_FORMAT = "INVALID_XML_FORMAT";
	public static final String MSG_TODATE_MUST_AFTER_FROMDATE = "TODATE_MUST_AFTER_FROMDATE";
	public static final String MSG_NOT_OVER = "NOT_OVER";
	public static final String MSG_NOT_LESS_THAN = "NOT_LESS_THAN";
	public static final String MSG_FIX_LENGTH = "FIX_LENGTH";
	public static final String MSG_INVALID_DATE_FORMAT = "INVALID_DATE_FORMAT";
	public static final String MSG_ILLEGAL_INPUT = "ILLEGAL_INPUT";

	private static void checkFileForUpdate() {
		try {
			String filePath = AppConstant.USER_HOME+org.zkoss.lang.Library.getProperty("app.message.location");
			File singlePropsFile = new File(filePath);
			latestLastModified = singlePropsFile.lastModified();
			if (latestLastModified != lastModified) {
				Labels.reset();
				lastModified = latestLastModified;
			}
		} catch (Exception e) {
		}
	}

	public static String getMessage(String key) {
		checkFileForUpdate();

		String message = "";
		try {
			message = Labels.getLabel(key);
		} catch (Exception ex) {
			throw new BillerManageWebException("Get Message From i18n fail", ex);
		}
		return message;
	}

	public static String getMessage(String key, String def) {
		checkFileForUpdate();

		String message = null;
		try {
			message = Labels.getLabel(key);
		} catch (Exception ex) {
			throw new BillerManageWebException("Get Message From i18n fail", ex);
		}
		return (message == null) ? def : message;
	}

	public static String getMessage(String key, String... params) {
		checkFileForUpdate();

		String message = "";
		try {
			message = Labels.getLabel(key, params);
		} catch (Exception ex) {
			throw new BillerManageWebException("Get Message From i18n fail", ex);
		}
		return message;
	}
}
