package controller;

import java.io.File;
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

/**
 * 
 */
public class Controller {
	
	//model attributes
	private LivraisonManager mLivraisonManager;
	private PlanManager mPlanManager;
	
	//vue attributes
	private VueGestionLivraison mVueGestionLivraison;

	/**
	 * Classe controller principale
	 * 
	 * @param mLivraisonManager
	 * @param mPlanManager
	 */
    public Controller() {
		super();
		this.mPlanManager = new PlanManager();
		this.mLivraisonManager = new LivraisonManager(this.mPlanManager);
		this.mVueGestionLivraison = new VueGestionLivraison(mPlanManager, mLivraisonManager);
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
    	this.mVueGestionLivraison.afficherFenetrePrincipale();
    }

    /**
     * @param circuit
     */
    public void afficherItineraire(Set<Troncon> circuit) {
        // TODO implement here
    }
       
    public File getFichierXMLDemandeLivraison() {
    	return this.mVueGestionLivraison.getFichierXMLDemandeLivraison();   	
    }
    
    /**
     * 
     */
    public void getDemandeLivraisons() {
    	File fichierXML = this.getFichierXMLDemandeLivraison();
    	mLivraisonManager.loadDemandeLivraisonsXML(fichierXML);
    }

	public void exceptionOpenFileXML(String message) {
		this.mVueGestionLivraison.afficherExceptionOuvertureXML(message);
		
	}
}
