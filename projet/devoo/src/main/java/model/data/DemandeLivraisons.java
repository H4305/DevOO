package model.data;

import java.util.*;

/**
 * 
 */
public class DemandeLivraisons {

	/**
	 * 
	 */
	private List<PlageHoraire> mPlagesHoraires;
	private Noeud mEntrepot;
	
    /**
     * 
     */
    public DemandeLivraisons() {
    	mPlagesHoraires = new ArrayList<PlageHoraire>();
    }

    /**
     * @param entrepot
     * @param plagesHoraires
     */
    public DemandeLivraisons(Noeud entrepot, List<PlageHoraire> plagesHoraires) {
    	this.mPlagesHoraires = plagesHoraires;
    	this.mEntrepot = entrepot;
    }

    /**
     * 
     */
    public List<PlageHoraire> getPlagesHoraire() {
        return mPlagesHoraires;
    }

    /**
     * @param dateDebut
     */
    public PlageHoraire getPlageHoraire(Date dateDebut) {
    	
    	for (PlageHoraire pH : mPlagesHoraires) {
    		if(pH.getDateDebut().equals(dateDebut)) {
    			return pH;
    		}
    	}
    	return null;
    }

    /**
     * Returne l'entrepot de la demande de livraison
     * @return l'entrepot de la demande de livraison
     */
    public Noeud getEntrepot() {
    	return mEntrepot;
    }
    
    /**
     * @param dateDebut
     */
    public PlageHoraire getPlageHoraireEquals(PlageHoraire plageHoraire) {
    	
    	for (PlageHoraire pH : mPlagesHoraires) {
    		if(pH.equals(plageHoraire)) {
    			return pH;
    		}
    	}
    	return null;
    }
    
    
}