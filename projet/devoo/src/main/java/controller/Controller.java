package controller;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.data.Itineraire;
import model.data.Livraison;
import model.data.Point;
/*
 * model import
 */
import model.data.Livraison;
import model.manager.LivraisonManager;
import model.manager.PlanManager;
import model.data.PlageHoraire;
import model.data.Point;
import model.data.Troncon;

/*
 * vue import
 */
import vue.VueGestionLivraison;
import vue.widget.PlanPanel.PointClickedListener;

/**
 * 
 * Controller has the role to act as an intermediary between views and model.
 * It receives all the requests from the view and ask the model to do some operations.
 * At the end, the controller ask the view to update itself.
 * 
 * @author      Vadim Caen
 * @author      Maria Etegan
 * @author      Anthony Faraut
 * @author      Ludmila Danilescu
 * @author      Marco Montalto
 * @author      Bernardo Rittmeyer
 * 
 */
public class Controller {
	
	//model attributes
	private LivraisonManager mLivraisonManager;
	private PlanManager mPlanManager;
	
	//vue attributes
	private VueGestionLivraison mVueGestionLivraison;
	
	private static final Logger LOG = Logger.getLogger(Controller.class.getName());

   /** 
    * Class constructor.
    */
    public Controller() {
		super();
		this.mPlanManager = new PlanManager(this);
		this.mLivraisonManager = new LivraisonManager(this.mPlanManager, this);
		this.mVueGestionLivraison = new VueGestionLivraison(mPlanManager, mLivraisonManager, this);
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

    /**
     * @param circuit
     */
    public void afficherItineraire(Itineraire circuit) {
        // TODO implement here
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
	 * This method asks the views to select a file and send it to the LivraisonManager class, to analyse it.
	 * The file should contain a "Plan" in a xml format
	 * 
	 */
    public void loadPlanXML() {
    	LOG.log(Level.INFO, "loadPlanXML :: BEGIN");

    	mPlanManager.loadPlanXML(this.mVueGestionLivraison.getFichierXML());
    	
    	LOG.log(Level.INFO, "loadPlanXML :: END");
    }
    
	/**
	 * This method indicates the view to update, showing the "plan".
	 * 
	 */
	public void afficherPlan() {
		mVueGestionLivraison.afficherPlan();
	}
	
	/**
	 * This method asks the views to select a file and send it to the LivraisonManager class, to analyse it.
	 * The file should contain the "Demande de livraison" in a xml format.
	 * 
	 */
    public void loadDemandeLivrasonsXML() {
    	LOG.log(Level.INFO, "loadDemandeLivrasonsXML :: BEGIN");

    	mLivraisonManager.loadDemandeLivraisonsXML(this.mVueGestionLivraison.getFichierXML());
    	
    	LOG.log(Level.INFO, "loadDemandeLivrasonsXML :: END");
    }
    
	/**
	 * This method indicates the view to update, showing the "demande the livraisons".
	 * 
	 */
	public void afficherDemandeLivraisons() {
		mVueGestionLivraison.afficherDemandeLivraisons();
	}
	
	/**
	 * This method sends a message to the view. It contains
	 * the description of an exception occurred while trying to open xml file.
	 * 
	 */
	public void exceptionOpenFileXML(String message) {
		this.mVueGestionLivraison.afficherExceptionOuvertureXML(message);
		
	}

    /**
     * 
     */
    public void calculItineraire() {
        // TODO implement here
    }

    /**
     * @param circuit
     */
    public void afficherItineraire(Set<Troncon> circuit) {
        // TODO implement here
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
