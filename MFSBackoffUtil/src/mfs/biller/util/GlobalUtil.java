package mfs.biller.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GlobalUtil {
	
	public static String getData(List<String> item, int index){
		try{
			return item.get(index)==null ? "" : item.get(index).toString();
		}catch(Exception e){
			return "";
		}
	}
	
	public static String getStringByMax(String value, int max){
		try{
			if (value.length() > max){
				return value.substring(0, max);
			}else{
				return value;
			}
		}catch(Exception e){
			return "";
		}
	}
	
	private java.sql.Date parseToSqlDate(String strDate) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		java.util.Date d = null;
		java.sql.Date dsql = null;
		try {
			d = (Date) formatter.parse(strDate);
			Calendar cc = Calendar.getInstance(Locale.US);
			cc.setTime(d);
			dsql = new java.sql.Date(cc.getTimeInMillis());
		} catch (ParseException e) {
			
		}
		return dsql;
	}
	
	
}
