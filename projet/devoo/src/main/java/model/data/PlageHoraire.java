package model.data;

import java.util.*;

/**
 * 
 */
public class PlageHoraire {

    /**
     * 
     */
    public PlageHoraire() {
    }

    public PlageHoraire(String dateDebut, String dateFin) {
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    }
    
    /**
     * 
     */
    private String dateDebut;

    /**
     * 
     */
    private String dateFin;


    /**
     * @return
     */
    public Set<Livraison> getLivraisons() {
        // TODO implement here
        return null;
    }

}