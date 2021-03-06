package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import devoo.LivraisonManagerTest;
import model.data.Chemin;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.Noeud;
/*
 * model import
 */
import model.data.Livraison;
import model.manager.LivraisonManager;
import model.manager.PlanManager;
import model.data.PlageHoraire;
import model.data.Noeud;
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
	private ArrayList<Action> actions;
	private int actionsIndex = 0;

	/** 
	 * Class constructor.
	 */
	public Controller() {
		this.mPlanManager = new PlanManager(this);
		this.mLivraisonManager = new LivraisonManager(this.mPlanManager, this);
		this.mVueGestionLivraison = new VueGestionLivraison(mPlanManager, mLivraisonManager, this);
		this.actions = new ArrayList<Action>();
	}

	/**
	 * 
	 */
	public void generateRoadmap () {
		mPlanManager.getAllTroncons();

		//mLivraisonManager.g
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
	public void afficherItineraire() {
		mVueGestionLivraison.afficherItineraire();
	}


	public void afficherLivraison(Noeud point) {
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
		mLivraisonManager.calculItineraire();
	}


	public PlanManager getPlanManager()
	{
		return this.mPlanManager;
	}
    
    public LivraisonManager getLivraisonManager()
    {
    	return this.mLivraisonManager;
    }
	
	/**
	 * This method ask the livraison manager to add a new delivrery
	 * 
	 * @param adresseNouvelleLivraison is the Noeud where the new delivery will be performed 
	 * @param adresseLivraisonPrecedente is the Noeud of the previous delivery
	 * @param idClient is the client id for the new shipping
	 */
	public void addNouvelleLivraison(Noeud adresseNouvelleLivraison, Noeud adresseLivraisonPrecedente, int idClient) {

		mLivraisonManager.addNouvelleLivraison(adresseNouvelleLivraison, adresseLivraisonPrecedente, idClient);

		actions.add(new ActionAddLivraison());
		actionsIndex++;
	}

	/**
	 * 
	 */
	public void removeLivraison() {

		//mLivraisonManager.removeLivraison();

		actions.add(new ActionDeleteLivraison());
	}

	public void undo() {

		//actions.get(index);
		actions.remove(actions.size());

		actions.add(new ActionDeleteLivraison());
	}

	public void redo() {

	}

	public void afficherLivraisonImpossible(List<Livraison> livraisons) {
		mVueGestionLivraison.afficherLivraisonImpossible(livraisons);

	}
}
