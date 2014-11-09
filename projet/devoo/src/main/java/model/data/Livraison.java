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
     * L'identifiant de la livraison
     */
    private int id;

    /**
     * L'heure prévue de la livraison
     */
    private Date heureLivraison;

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
    public void setHeureLivraison(Date heure) {
        heureLivraison = heure;
    }

    /**
     * return L'adresse de livraison
     */
    public Point getAdresse() {
        return adresse;
    }

}