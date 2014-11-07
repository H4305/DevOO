package model.data;

import java.util.*;

/**
 * 
 */
public class Livraison {

    /**
     * 
     */
    public Livraison() {
    }

    /**
     * 
     * @param id
     * @param heureLivraison
     * @param id_client
     */
    public Livraison(int id, String heureLivraison, int id_client) {
    	this.id = id;
    	this.heureLivraison = heureLivraison;
    	this.id_client = id_client;
    }
    
    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String heureLivraison;

    /**
     * 
     */
    private int id_client;



    /**
     * @param heure
     */
    public void setHeureLivraison(Date heure) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getAdresse() {
        // TODO implement here
    }

}