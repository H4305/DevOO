package util;


public class GenerationIDint {
	static int uniqueId;
	
	/**
	 * Constructor
	 */
	public GenerationIDint(){
		uniqueId = 1000;
	}
	
	/**
	 * This method generates unique IDs
	 * @return unique id
	 */
	public int getUniqueId()
	{
	    return uniqueId++;
	}

}
