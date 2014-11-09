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

    /**
     * 
     */
    private Date dateDebut;

    /**
     * 
     */
    private Date dateFin;

    /**
     * Livraisons dans cette plage horaire
     */
    private List<Livraison> livraisons;
    
    /**
     * Getter pour la date de début
     * @return La date de debut de la plage horaire
     */
    public Date getDateDebut() {
		return dateDebut;
	}

    /**
     * @return Liste des livraisons de la plage horaire
     */
    public List<Livraison> getLivraisons() {
        return livraisons;
    }

}