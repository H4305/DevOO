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
    	// TODO : Je ne sais pas si quelqu'un l'utilise, moi j'en sers plus
    	this.id = id;
    	this.heureLivraison = heureLivraison;
    	this.idClient = id_client;
    }
    
    /**
     * 
     * @param id
     * @param heureLivraison
     * @param id_client
     */
    public Livraison(int id, int id_client, Point adresse) {
    	this.id = id;
    	this.heureLivraison = "";
    	this.idClient = id_client;
    	this.adresse = adresse;
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
    private int idClient;

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
    public String getHeureLivraison() {
    	if(heureLivraison==""){
    		return " pas encore prevue";
    	}
    	return heureLivraison;       
    }
    
    public int getId() {
		return id;
	}
    
    public int getIdClient() {
		return idClient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + id;
		result = prime * result + idClient;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Livraison))
			return false;
		Livraison other = (Livraison) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (id != other.id)
			return false;
		if (idClient != other.idClient)
			return false;
		return true;
	}
    


}