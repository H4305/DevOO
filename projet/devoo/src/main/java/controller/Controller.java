package controller;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Point;
/*
 * model import
 */
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;
/*
 * vue import
 */
import vue.VueGestionLivraison;
import vue.widget.PlanPanel.PointClickedListener;

/**
 * 
 */
public class Controller {
	
	//model attributes
	private LivraisonManager mLivraisonManager;
	private PlanManager mPlanManager;
	
	private static final Logger LOG = Logger.getLogger(Controller.class
			.getName());
	
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
		this.mPlanManager = new PlanManager(this);
		this.mLivraisonManager = new LivraisonManager(this.mPlanManager, this);
		this.mVueGestionLivraison = new VueGestionLivraison(mPlanManager, mLivraisonManager, this);
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
    	mVueGestionLivraison.setPointClickedListener(new PointClickedListener() {
			
			@Override
			public void pointClicked(Point point) {
				LOG.log(Level.INFO, "Point Clicked");
				
				onePointSelected(point);
			}
		});
    }
    
    public void onePointSelected(Point point) {
    	afficherLivraison(point);
    }
    
    public void afficherLivraison(Point point) {
    	Livraison livraison = mLivraisonManager.findLivraisonByAddress(point);
    	if(livraison != null) {
    		PlageHoraire plageHoraire = mLivraisonManager.findPlageHoraireByLivraison(livraison);
    		if(plageHoraire!=null) {
    			mVueGestionLivraison.afficherLivraison(plageHoraire, livraison);
    		} else {
    			masquerLivraison();
    		}
    	} else {
    		masquerLivraison();
    	}
    }
    
    public void masquerLivraison() {
    	mVueGestionLivraison.masquerLivraison();
    }

    /**
     * @param circuit
     */
    public void afficherItineraire(Set<Troncon> circuit) {
        // TODO implement here
    }
    
    /**
     * 
     */
    public void loadDemandeLivrasonsXML() {
    	System.out.println("Controller :: loadDemandeLivrasonsXML :: BEGIN");

    	mLivraisonManager.loadDemandeLivraisonsXML(this.mVueGestionLivraison.getFichierXML());
    	
    	System.out.println("Controller :: loadDemandeLivrasonsXML :: END");
    }
    
    /**
     * 
     */
    public void loadPlanXML() {
    	System.out.println("Controller :: loadPlanXML :: BEGIN");

    	mPlanManager.loadPlanXML(this.mVueGestionLivraison.getFichierXML());
    	
    	System.out.println("Controller :: loadPlanXML :: END");
    }

	public void exceptionOpenFileXML(String message) {
		this.mVueGestionLivraison.afficherExceptionOuvertureXML(message);
		
	}

	public void afficherDemandeLivraisons() {
		mVueGestionLivraison.afficherDemandeLivraisons();
	}

	public void afficherPlan() {
		mVueGestionLivraison.afficherPlan();
	}
	public void pointClicked(Point p){
		Livraison livraison = mLivraisonManager.leLivraison(p);
    	if(livraison!=null)  {
    		mLivraisonManager.remove(livraison);
    	} else {
    		mLivraisonManager.add(p);
    	}
    	
    }
}
