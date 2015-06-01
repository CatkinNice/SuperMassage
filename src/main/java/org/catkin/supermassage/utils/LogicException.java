package org.catkin.supermassage.utils;


public class LogicException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private ErrorType error;
	
	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(Throwable cause) {
		super(cause);
	}

	public LogicException(ErrorType error) {
		super(error.toString());
		this.error = error;
	}
	
	public LogicException(ErrorType error, Throwable cause) {
		super(error.toString(), cause);
		this.error = error;
	}

	public ErrorType getError() {
		return error;
	}
}
