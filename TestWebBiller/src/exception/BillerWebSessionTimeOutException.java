package exception;

public class BillerWebSessionTimeOutException extends RuntimeException {
	public BillerWebSessionTimeOutException() {
		super();
	}

	public BillerWebSessionTimeOutException(String message) {
		super(message);
	}

	public BillerWebSessionTimeOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public BillerWebSessionTimeOutException(Throwable cause) {
		super(cause);
	}
}
