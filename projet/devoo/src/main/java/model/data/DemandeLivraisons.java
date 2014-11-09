package model.data;

import java.util.*;

/**
 * 
 */
public class DemandeLivraisons {

	/**
	 * 
	 */
	private List<PlageHoraire> plagesHoraires;
	
    /**
     * 
     */
    public DemandeLivraisons() {
    	plagesHoraires = new ArrayList<PlageHoraire>();
    }

    /**
     * 
     * @param plagesHoraires
     */
    public DemandeLivraisons(List<PlageHoraire> plagesHoraires) {
    	this.plagesHoraires = plagesHoraires;
    }

    /**
     * 
     */
    public List<PlageHoraire> getPlagesHoraire() {
        return plagesHoraires;
    }

    /**
     * @param dateDebut
     */
    public PlageHoraire getPlageHoraire(Date dateDebut) {
    	for (PlageHoraire pH : plagesHoraires) {
    		if(pH.getDateDebut().equals(dateDebut)) {
    			return pH;
    		}
    	}
    	return null;
    }

}