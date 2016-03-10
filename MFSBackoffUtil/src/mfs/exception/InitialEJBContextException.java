package mfs.exception;

public class InitialEJBContextException extends Exception {

	public InitialEJBContextException() {
		super();
		
	}

	public InitialEJBContextException(String message) {
		super(message);
	}

	public InitialEJBContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitialEJBContextException(Throwable cause) {
		super(cause);
	}
}
