package exception;

import java.io.PrintWriter;

public class ReportServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE_JASPER = "001";
	private int errorCode;
    private boolean first;
    Throwable previousException;

    /**
     *
     */
    public ReportServiceException() {
        previousException = null;
        errorCode = 0;
        first = true;
    }

    /**
     * @param s
     */
    public ReportServiceException(String s) {
        super(s);
        previousException = null;
        errorCode = 0;
        first = true;
        fillInStackTrace();
    }

    public ReportServiceException(String s, int i, Throwable throwable) {
        super(s);
        previousException = null;
        errorCode = 0;
        first = true;
        errorCode = i;
        previousException = throwable;
        fillInStackTrace();
    }

    public Throwable getInitialException() {
        if (previousException == null) {
            return this;
        }

        if (previousException instanceof ReportServiceException) {
            return ((ReportServiceException) previousException).getInitialException();
        } else {
            return previousException;
        }
    }

    @SuppressWarnings("unused")
	private String getExceptionSource(Exception exception) {
        FastOutputStreamWriter fastoutputstreamwriter = new FastOutputStreamWriter(this);

        exception.printStackTrace(new PrintWriter(fastoutputstreamwriter));

        int i = fastoutputstreamwriter.size();
        String s = new String(fastoutputstreamwriter.getByteArray(), 0, fastoutputstreamwriter.size() - 1);
        char ac[] = s.toCharArray();
        int j = s.length();
        int l = 0;
        int k;

        for (k = 0; k < j; k++) {
            if (ac[k] == 0) {
                break;
            }

            if (Character.isWhitespace(ac[k])) {
                l = k;
            }

            if (Character.isISOControl(ac[k])) {
                ac[k] = ' ';
            }
        }

        String s1 = "[" + new String(ac, l, k - l) + "]";

        return s1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int i) {
        errorCode = i;
    }

    public String getMessage() {
        String s = "";
        String s1;

        try {
            s1 = super.getMessage().trim();
        } catch (Exception exception) {
            s1 = "";
        }

        if (previousException != null) {
            s = previousException.getMessage();
        }

        if (s1.length() > 0) {
            if (s.length() == 0) {
                s = s + s1;
            } else {
                s = s + "\n" + s1;
            }
        }

        return s;
    }

    public String toString() {
        String s1 = getClass().getName();
        int i = s1.lastIndexOf(46);
        String s2 = s1.substring(i + 1);

        if (first) {
            String s;

            if (previousException != null) {
                s = previousException.toString();
            } else {
                s = "";
            }

            String s3 = super.getMessage();

            if ((s3 == null) || (s3.length() == 0)) {
                s3 = "Default Message";
            }

            s = s + s2 + ": " + "<b>" + s3 + "</b>";
            first = false;
            s = s + getExceptionSource(this) + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            first = true;

            return s;
        } else {
            return "";
        }
    }

    public final boolean isChild(String s) {
        boolean flag = false;

        try {
            for (ReportServiceException omsexception = (ReportServiceException) previousException; omsexception != null;) {
                if (omsexception.getClass().getName().equals(s)) {
                    flag = true;

                    break;
                }

                try {
                    omsexception = (ReportServiceException) omsexception.previousException;
                } catch (ClassCastException classcastexception) {
                    Throwable throwable = omsexception.previousException;

                    return (throwable != null) && throwable.getClass().getName().equals(s);
                }
            }
        } catch (Exception exception) {
            return false;
        }

        return flag;
    }

}
	

	
