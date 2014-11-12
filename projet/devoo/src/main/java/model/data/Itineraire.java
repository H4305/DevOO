package model.data;

import java.util.*;

/**
 * 
 */
public class Itineraire {

    /**
     * 
     */
    private List<Chemin> mChemins;
    
    /**
     * 
     */
    public Itineraire(List<Chemin> chemins) {
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
	
	public List<Chemin> getChemins() {
		return mChemins;
	}

}