package exception;
//
public class BillerManageWebException extends RuntimeException {
	public BillerManageWebException() {
		super();
	}

	public BillerManageWebException(String message) {
		super(message);
	}

	public BillerManageWebException(String message, Throwable cause) {
		super(message, cause);
	}

	public BillerManageWebException(Throwable cause) {
		super(cause);
	}
}
