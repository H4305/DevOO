package model.data;

import java.util.*;

/**
 * 
 */
public class Livraison {

    /**
     * Constructeur
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
     * L'heure prévue de la livraison
     */
    private String heureLivraison;

    /**
     * L'id du client
     */
    private int id_client;

    /**
     * L'adresse de livraison
     */
    private Point adresse;

    /**
     * @param La nouvelle heure l'heure de la livraison
     */
    public void setHeureLivraison(String heure) {
        heureLivraison = heure;
    }

    /**
     * return L'adresse de livraison
     */
    public Point getAdresse() {
        return adresse;
    }

}