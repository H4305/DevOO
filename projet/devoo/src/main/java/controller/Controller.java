package controller;

import java.io.File;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private static final Logger LOG = Logger.getLogger(Controller.class
			.getName());
	
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
		this.mLivraisonManager = new LivraisonManager(this.mPlanManager, this);
		this.mVueGestionLivraison = new VueGestionLivraison(mPlanManager, mLivraisonManager, this);
		
		mVueGestionLivraison.setPointClickedListener(new PointClickedListener() {
			
			@Override
			public void pointClicked(Point point) {
				LOG.log(Level.INFO, "Point clicked : " + point.x + " " + point.y);
			}
		});
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
    	System.out.println("Controller :: getDemandeLivraison :: BEGIN");
    	
    	File fichierXML = this.getFichierXMLDemandeLivraison();
    	mLivraisonManager.loadDemandeLivraisonsXML(fichierXML);
    	
    	System.out.println("Controller :: getDemandeLivraison :: END");
    }

	public void exceptionOpenFileXML(String message) {
		this.mVueGestionLivraison.afficherExceptionOuvertureXML(message);
		
	}

	public void afficherDemandeLivraisons() {
		mVueGestionLivraison.afficherDemandeLivraisons();
	}
}
