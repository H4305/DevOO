package util;


public class GenerationIDint {
	static int uniqueId;
	public GenerationIDint(){
		uniqueId = 1000;
	}
	public int getUniqueId()
	{
	    return uniqueId++;
	}

}
