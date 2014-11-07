package controller;

import java.util.*;

import vue.VueGestionLivraison;
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

/**
 * 
 */
public class Contoller {
	
	LivraisonManager mLivraisonManager;
	PlanManager mPlanManager;

	/**
	 * Classe controller principale
	 * 
	 * @param mLivraisonManager
	 * @param mPlanManager
	 */
    public Contoller() {
		super();
		this.mLivraisonManager = new LivraisonManager();
		this.mPlanManager = new PlanManager();
	}




    /**
     * 
     */
    public void calculItineraire() {
        // TODO implement here
    }
    
    /**
     * Initialisation de l'applicatione et affichage de l'écran d'accueil.
     */
    public void start() {
    	new VueGestionLivraison(mPlanManager, mLivraisonManager).afficherFenetrePrincipale();
    }

    /**
     * @param circuit
     */
    public void afficherItineraire(Set<Troncon> circuit) {
        // TODO implement here
    }

}