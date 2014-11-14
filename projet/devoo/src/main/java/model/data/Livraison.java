package model.data;

import java.util.*;

/**
 * 
 */
public class Livraison {
    
    /**
     * The id of the livraison
     */
    private int id;

    /**
     * L'heure pévue de la livraison
     */
    private String heureLivraison;

    /**
     * L'id du client
     */
    private int idClient;

    /**
     * L'adresse de livraison
     */
    private Noeud adresse;
	
    private Boolean aLivrer;
    
    /**
     * Constructor
     * @param id : The id of the livraison
     * @param heureLivraison : The hour of the livraison
     * @param id_client : The id of the client who want to be delivered
     */
    public Livraison(int id, int id_client, Noeud adresse) {
    	this.id = id;
    	this.heureLivraison = "";
    	this.idClient = id_client;
    	this.adresse = adresse;
    	this.aLivrer = false;
    }

    /**
     * This method allows to set the hour of the livraison
     * @param La nouvelle heure l'heure de la livraison
     */
    public void setHeureLivraison(String heure) {
        heureLivraison = heure;
    }

    /**
     * This method allows to get the Adresse of the Livraison
     */
    public Noeud getAdresse() {
        return adresse;
    }
    
    /**
     * This method allows to get the Hour of the Livraison
     */
    public String getHeureLivraison() {
    	if(heureLivraison==""){
    		return " pas encore prevue";
    	}
    	return heureLivraison;
    }
    
    /**
     * This method allows to get the id of the Livraison
     */
    public int getId() {
		return id;
	}
    
    /**
     * This method allows to get the id of the client for the Livraison
     */
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

	/**
	 * This method allows to set the boolean of the livraison reprensenting the state of the livraison
	 */
	public void setALivrer() {
		this.aLivrer = true;		
	}
}