package exception;

public class BillerManageWebValidationException extends RuntimeException {
	public BillerManageWebValidationException() {
		super();
	}

	public BillerManageWebValidationException(String message) {
		super(message);
	}

	public BillerManageWebValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BillerManageWebValidationException(Throwable cause) {
		super(cause);
	}
}
