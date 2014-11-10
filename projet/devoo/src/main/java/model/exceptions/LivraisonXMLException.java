package model.exceptions;

import controller.Controller;

@SuppressWarnings("serial")
public class LivraisonXMLException extends Exception {
	
	public LivraisonXMLException(String message) {  
		super(message); 
		//new Controller().exceptionOpenFileXML(message);
	}
	
	public LivraisonXMLException(String message, Throwable cause) {  
		super(message, cause); 
		//new Controller().exceptionOpenFileXML(message + "Cause:" + cause.getMessage());
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}