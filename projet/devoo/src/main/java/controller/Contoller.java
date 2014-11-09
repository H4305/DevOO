package controller;

import java.util.*;

/*
 * vue import
 */
import vue.VueGestionLivraison;

/*
 * model import
 */
import model.data.Troncon;

import model.manager.*;

import model.manager.LivraisonManager;
import model.manager.PlanManager;

/**
 * 
 */
public class Contoller {
	
	private LivraisonManager mLivraisonManager;
	private PlanManager mPlanManager;

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
     * Initialisation de l'applicatione et affichage de l'�cran d'accueil.
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
    
    /**
     * @param nomFichier : nom du fichier XML à charger
     */
    public void getDemandeLivraisonsFromXML(String nomFichier) {
    	mLivraisonManager.loadDemandeLivraisonsXML(nomFichier);
    }

}
