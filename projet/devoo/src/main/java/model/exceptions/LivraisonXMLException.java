package model.exceptions;

@SuppressWarnings("serial")
public class LivraisonXMLException extends Exception {
	
	public LivraisonXMLException(String message) {  
		super(message); 
	}
	
	public LivraisonXMLException(String message, Throwable cause) {  
		super(message, cause); 
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}