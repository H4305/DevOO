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
public class Contoller {
	
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
    public Contoller() {
		super();
		this.mLivraisonManager = new LivraisonManager();
		this.mPlanManager = new PlanManager();
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
    
    /**
     * @param nomFichier : nom du fichier XML à charger
     */
    public void getDemandeLivraisons() {
    	File fichierXML = this.getFichierXMLDemandeLivraison();
    	mLivraisonManager.loadDemandeLivraisonsXML(fichierXML);
    }
    
    public File getFichierXMLDemandeLivraison() {
    	return this.mVueGestionLivraison.getFichierXMLDemandeLivraison();   	
    }
}
