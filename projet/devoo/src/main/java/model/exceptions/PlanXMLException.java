package model.exceptions;

@SuppressWarnings("serial")
public class PlanXMLException extends Exception {
	public PlanXMLException(String message, Throwable cause) {  
		super(message, cause); 
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}