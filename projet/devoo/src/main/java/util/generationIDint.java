package util;


public class generationIDint {
	static int uniqueId;
	public generationIDint(){
		uniqueId = 1000;
	}
	public int getUniqueId()
	{
	    return uniqueId++;
	}

}
