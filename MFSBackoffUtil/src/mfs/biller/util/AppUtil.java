package mfs.biller.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AppUtil {
	public static <T> boolean isEmpty(T v) {
		if (v == null) {
			return true;
		}
		if (v instanceof String) {
			String s = (String) v;
			if (s != null && s.trim().length() > 0) {
				return false;
			} else {
				return true;
			}
		} else if (v instanceof Number) {
			Number b = (Number) v;
			if (b != null) {
				double d = b.doubleValue();
				if (!Double.isNaN(d) && !Double.isInfinite(d) && d != 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} else if (v instanceof Collection) {
			Collection c = (Collection) v;
			if (c != null && c.size() > 0) {
				return false;
			} else {
				return true;
			}
		} else if (v instanceof Map) {
			Map m = (Map) v;
			if (m != null && m.size() > 0) {
				return false;
			} else {
				return true;
			}
		} else if (v instanceof List) {
			List l = (List) v;
			if (l != null && l.size() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			if (v != null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static String trim(String str) {
		if (str != null) {
			return str.trim();
		} else {
			return str;
		}
	}

	public static String toString(Date datetime) {
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		String dateTimeStr = "";
		try {
			dateTimeStr = sdfDateTime.format(datetime);
		} catch (Exception ex) {
			throw new RuntimeException("Format fail:" + ex.getMessage());

		}
		return dateTimeStr;
	}

	public static String toString(BigDecimal o) {
		return toString(o, "");
	}
	
	public static String toString(BigDecimal o, String pattern) {
		String str = "";
		pattern = pattern.equals("") ? "#,##0.00" : pattern;
		try {
			if (o != null) {
				DecimalFormat df = new DecimalFormat(pattern);
				str = df.format(o);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Format BigDecimal fail:[" + pattern + "]" + ex.getMessage());

		}
		return str;
	}

	public static String toString(Integer i) {
		if (i == null) {
			return "";
		} else {
			return i.toString();
		}
	}

	public static <T> boolean isNotNull(T v) {
		if (v instanceof String) {
			String s = (String) v;
			if (s != null) {
				if (s.trim().length() > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (v instanceof Number) {
			Number b = (Number) v;
			if (b != null) {
				double d = b.doubleValue();
				if (Double.isNaN(d) || Double.isInfinite(d)) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			if (v != null) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static <T> boolean isNull(T v) {
		return !isNotNull(v);
	}

	

	public static void main(String[] args) throws Exception {
	
	}

	public static String addPadding(String message, String added, int padding) {
		for (int i = 0; i < padding; i++) {
			if (message.length() < padding) {
				message = added + message;
			}
		}
		return message;
	}

}
