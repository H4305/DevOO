package model.exceptions;

@SuppressWarnings("serial")
public class PlanXMLException extends Exception {
	public PlanXMLException(String message, Throwable cause) {  
		super(message, cause); 
	}
	
	public PlanXMLException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}