package mfs.biller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateTimeUtil {
	
	public static Date parseToDate(String strDate, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
		Date date = null;
		try{
			date = formatter.parse(strDate);
		}catch(Exception e){
		
		}
		return date;
	}
	
	public static String parseToString(Date date, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
		try{
			return formatter.format(date);
		}catch(Exception e){
			return "";
		}
	}
	final private static Logger _log = Logger.getLogger(DateTimeUtil.class);
	public final static Locale LOCALE_TH = new Locale("th", "TH");

	private static boolean isValidDateTime(String inDate, String format) {
		if (inDate == null) {
			return false;
		}

		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}

		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (Exception pe) {
			return false;
		}
		return true;
	}

	public static Date getDateTimeFromISO8583(String datetime) throws Exception {
		SimpleDateFormat sdfYear = new SimpleDateFormat("yy", Locale.US);
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyMMddHHmmss",
				Locale.US);

		String year = sdfYear.format(new Date());
		if (!isValidDateTime(year + datetime, "yyMMddHHmmss")) {
			throw new Exception("Invalid DataTime");
		}
		return sdfDateTime.parse(year + datetime);
	}

	public static String getDateTime(Date date, String format) throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat(format, Locale.US);
		return sdfDateTime.format(date);
	}
	
	public static Date getDateTime(String date, String format) throws Exception {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat(format, Locale.US);
		return sdfDateTime.parse(date);
	}

	public static String getDateStringForTag120Resp(Date date) {

		return new SimpleDateFormat("yyyyMMdd", Locale.US).format(date);

	}

	/**
	 * Check DueDate with allowed payableDays. Raise exception if today is not
	 * acceptable.
	 * 
	 * @param dueDate
	 * @param payableDays
	 * @param dateFormat
	 * @param locale
	 * @throws ValidateException
	 */
	
	public static Date covertBillerDueDate(String dueDate, String format) {
		
		String[] arrs = format.split(",");
		try {
			// If Today is after dueDate + payableDays = Invalid Payment.

		
			if(null!= arrs && 3 == arrs.length){
			
				String dateFormat = arrs[0];
				String year = arrs[1];
								
				Locale lc =null ;
				
				if( "BE".equals(year)){
					lc = DateTimeUtil.LOCALE_TH;
				}else{
					lc = Locale.US;
				}
				
				final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat,lc);
				_log.debug(String.format("Duedate[%s] dateformat[%s]", dueDate,
						dateFormat));
				
				return formatter.parse(dueDate);
			}
		} catch (Exception e) {

		

//			throw new ValidateException("Check DueDate: Invalid DueDate["
//					+ dueDate + "] format [" + dateFormat + "]",
//					GWErrorRespCode.INVALID_DUE_DATE);

		}
		return null;
	}

	public static Date getCurrent() {
		Calendar c = Calendar.getInstance(Locale.US);
		return c.getTime();
	}

	public static Date minusDate(Date date, int day) {
		Calendar c = Calendar.getInstance(Locale.US);
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
		return c.getTime();
	}
}
