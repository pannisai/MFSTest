package common;

import java.util.Locale;

public class AppDateFormatInfo implements DateFormatInfo{

	@Override
	public String getDateFormat(int arg0, Locale arg1) {
		
		return "yyyy-MM-dd";
	}

	@Override
	public String getDateTimeFormat(int arg0, int arg1, Locale arg2) {
		// TODO Auto-generated method stub
		return "yyyy-MM-dd HH:mm:ss";
	}

	@Override
	public String getTimeFormat(int arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return "HH:mm:ss";
	}

}
