package mfs.biller.util;

import java.math.BigDecimal;
import java.util.Date;

public class JpaResultUtil {

	public static String getString(Object[] item, int index){
		try{
			return item[index]==null ? "" : item[index].toString();
		}catch(Exception e){
			return "";
		}
	}
	
	public static Integer getInteger(Object[] item, int index){
		Integer i = null;
		try{
			i = new Integer(item[index].toString());
		}catch(Exception e){
			
		}
		return i;
	}
	
	public static BigDecimal getBigDecimal(Object[] item, int index){
		BigDecimal b = null;
		try{
			b = new BigDecimal(item[index].toString());
		}catch(Exception e){
			
		}
		return b;
	}
	
	public static Date getDate(Object[] item, int index, String dateFormat){
		Date date = null;
		try{
			date = DateTimeUtil.parseToDate(getString(item, index), dateFormat);
		}catch(Exception e){
			
		}
		return date;
	}
	
	
}
