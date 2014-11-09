package model.exceptions;

import controller.Controller;

@SuppressWarnings("serial")
public class PlanXMLException extends Exception {
	public PlanXMLException(String message, Throwable cause) {  
		super(message, cause); 
		new Controller().exceptionOpenFileXML(message + "Cause:" + cause.getMessage());
	}
	
	public PlanXMLException(String message) {
		super(message);
		new Controller().exceptionOpenFileXML(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}