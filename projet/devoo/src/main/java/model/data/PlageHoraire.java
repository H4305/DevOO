package model.data;

import java.util.*;
/**
 * 
 * PlageHoraire is the time slot
 * It contains a list of Deliveries to be delivered in this time slot 
 * 
 * @author      Vadim Caen
 * @author      Maria Etegan
 * @author      Anthony Faraut
 * @author      Ludmila Danilescu
 * @author      Marco Montalto
 * @author      Bernardo Rittmeyer
 * 
 */
public class PlageHoraire {
	
	/** Classe constructor  
     * 
     * sets the attribute list of deliveries a list with zero elements 
	 */
    public PlageHoraire() {
    	livraisons = new ArrayList<Livraison>();
    }
    /** Classe constructor  specifying its attributes 
     * 
     * @param dateDebut the start of the time slot
     * @param dateFin the end of the time slot
     * @param livraisons the list of deliveried to be done in the time slot
     */
    public PlageHoraire(String dateDebut, String dateFin, List<Livraison> livraisons) {
    	this.dateDebut = dateDebut;
    	this.dateFin = dateFin;
    	this.livraisons = livraisons;
    }
    
    private String dateDebut;

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
    
    public String getDateFin() {
		return dateFin;
	}

    /**
     * @return Liste des livraisons de la plage horaire
     */
    public List<Livraison> getLivraisons() {
        return livraisons;
    }

}