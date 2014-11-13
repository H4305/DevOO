package model.data;

import java.util.*;

/**
 * 
 */
public class Itineraire {

    /**
     * 
     */
    private ArrayList<Chemin> mChemins;
    
    /**
     * 
     */
    public Itineraire(ArrayList<Chemin> chemins) {
    	this.mChemins = chemins;
    }
    
    public Noeud getAdresseArriveByDepart(Noeud depart) {
    	
    	Noeud arrivee = null;
    	
    	for(Chemin chemin: mChemins) {
    		
    		if(chemin.getDepart().equals(depart)) {
    			arrivee = chemin.getArrivee();
    			return arrivee;
    		}
    		
    	}	
    	
    	return arrivee;
    }

	public Chemin getCheminByDepart(Noeud depart) {
		
		Chemin cheminRes = null;
		
    	for(Chemin chemin: mChemins) {
    		
    		if(chemin.getDepart().equals(depart)) {
    			
    			return chemin;
    		}
    		
    	}	
    	
    	return cheminRes;
	}
	
	public ArrayList<Chemin> getChemins() {
		return mChemins;
	}

	
}