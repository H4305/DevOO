package model.data;

import java.util.*;

/**
 * 
 */
public class Itineraire {

    private List<Chemin> mChemins;
    
    /**
     * Constructor of the Itineraire
     * @param chemins : The list of the "Chemin" who representing the Itineraire
     */
    public Itineraire(List<Chemin> chemins) {
    	this.mChemins = chemins;
    }
    
    /**
     * This method allows to get the Address of an Itineraire from the start node
     * @param depart : The start Node
     * @return The Node
     */
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

    /**
     * This method allows to get the Chemin from start Node
     * @param depart : The start Node
     * @return The "Chemin's" object
     */
	public Chemin getCheminByDepart(Noeud depart) {
		Chemin cheminRes = null;
    	for(Chemin chemin: mChemins) {
    		if(chemin.getDepart().equals(depart)) {
    			return chemin;
    		}
    	}	
    	return cheminRes;
	}
	
	/**
	 * This method allows to get the Chemin from end Node
	 * @param arrive : The end Node
	 * @return The "Chemin's" object
	 */
	public Chemin getCheminByArrivee(Noeud arrive) {
		Chemin cheminRes = null;
    	for(Chemin chemin: mChemins) {
    		if(chemin.getArrivee().equals(arrive)) {
    			return chemin;
    		}
    	}	
    	return cheminRes;
	}
	
	/**
	 * This method allows to get all the "Chemin's" object representing the Itineraire
	 * @return The "Chemin's" list
	 */
	public List<Chemin> getChemins() {
		return mChemins;
	}
	
}