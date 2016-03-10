package mfs.biller.util;


public class ValidateUtil {

	public static boolean isEmpty(String param){
		if ((param == null) || param.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isEmpty(Integer param){
		if (param == null){
			return true;
		}else{
			return false;
		}
	}
}
