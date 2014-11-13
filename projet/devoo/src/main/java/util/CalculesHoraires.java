package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author marcomontalto
 *
 */
public class CalculesHoraires {
	
    /**
     * This method adds two times
     * 
     * @param heureA is the first time
     * @param heureB is the second time
     * @return a string which contains a time, in format HH:MM
     */
    public static String sommeHeures(String heureA, String heureB) { 	
    	
    	int heureAHH = Integer.parseInt(heureA.split("\\:")[0]);
    	int heureAMM = Integer.parseInt(heureA.substring(heureA.indexOf(":")+1, heureA.length()));
    	//System.out.println("heureAMM: " + heureAMM);
    	
    	int heureBHH = Integer.parseInt(heureB.split("\\:")[0]);
    	int heureBMM = Integer.parseInt(heureB.substring(heureB.indexOf(":")+1, heureB.length()));
    	//System.out.println("heureBMM: " + heureBMM);
    	
    	int minutesTemps = heureAMM + heureBMM;
    	//System.out.println("Minutes: " + minutesTemps);
    	
    	int heuresAAjouter = (int) (minutesTemps/60);
    	//System.out.println("Heures à ajouter: " + heuresAAjouter);
    	
    	int resMinutes = minutesTemps - 60*heuresAAjouter;
    	
    	String minutesString = resMinutes + "";
    	
    	if(resMinutes<10) {
    		
    		minutesString = "0" + minutesString;
    		
    	}
    	
    	int resHeures = heureAHH + heureBHH + heuresAAjouter;
    	
    	String heuresString = resHeures + "";
    	
    	if(resHeures<0) {
    		
    		heuresString = "0" + heuresString;
    		
    	}
    	
    	return heuresString + ":" + minutesString;
    	   	
    }
    
    /**
     * This method compares two times and returns a boolean
     * 
     * @param heureA
     * @param heureB
     * @return true if the first time is before the second, false if it is not
     */
    public static Boolean firstBeforeSecond(String heureA, String heureB) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    	
    	Calendar calFirst = Calendar.getInstance();
        Calendar calSecond = Calendar.getInstance();
        
		try {
			calFirst.setTime(formatter.parse(heureA));
			calSecond.setTime(formatter.parse(heureB));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return calFirst.before(calSecond);	
    }
    
    /**
     * This method transforms a a float quantity of time, in seconds, in HH:MM time format
     * 
     * @param tempsParcours is a quantity of time in seconds
     * @return a string containing the fime in HH:MM format
     */
    public static String transformeEnHeureMin(float tempsParcours) {
    	
    	int heureI = (int) ((int) tempsParcours) / 3600;
    	int minI = (int) ( (tempsParcours / 60) % 60);
    	
    	String heureS = "";
    	String minS = "";
    	
    	if(heureI<10)
    	{
    		heureS = "0"+heureI;
    	}else{
    		heureS = "" + heureI;
    	}
    	if(minI<10)
    	{
    		minS = "0"+minI;
    	}else{
    		minS = ""+minI;
    	}
    	
    	return heureS + ":" + minS;    	
    }
}
