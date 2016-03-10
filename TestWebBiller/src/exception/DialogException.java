package exception;

public class DialogException extends RuntimeException {
	public DialogException() {
		super();
	}

	public DialogException(String message) {
		super(message);
	}

	public DialogException(String message, Throwable cause) {
		super(message, cause);
	}

	public DialogException(Throwable cause) {
		super(cause);
	}
}
