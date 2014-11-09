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
    	livraisons = new ArrayList<Livraison>();
    }

    public PlageHoraire(String dateDebut, String dateFin, List<Livraison> livraisons) {
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.livraisons = livraisons;
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
     * Livraisons dans cette plage horaire
     */
    private List<Livraison> livraisons;
    
    /**
     * Getter pour la date de début
     * @return La date de debut de la plage horaire
     */
    public String getDateDebut() {
		return dateDebut;
	}

    /**
     * @return Liste des livraisons de la plage horaire
     */
    public List<Livraison> getLivraisons() {
        return livraisons;
    }

}