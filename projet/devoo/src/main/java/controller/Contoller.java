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

/**
 * 
 */
public class Contoller {
	
	private LivraisonManager livraisonManager;
	private PlanManager planManager;
	
    /**
     * 
     */
    public Contoller() { 	
    	livraisonManager = new LivraisonManager();
    	planManager = new PlanManager();
    }

    /**
     * 
     */
    public void calculItineraire() {
        // TODO implement here
    }
    
    public void start() {
    	new VueGestionLivraison().afficherFenetrePrincipale();
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
    	livraisonManager.loadDemandeLivraisonsXML(nomFichier);
    }

}