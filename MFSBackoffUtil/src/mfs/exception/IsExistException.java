package mfs.exception;

public class IsExistException extends Exception {
	public IsExistException() { super(); }
	public IsExistException(String message) { super(message); }
	public IsExistException(String message, Throwable cause) { super(message, cause); }
	public IsExistException(Throwable cause) { super(cause); }
}
