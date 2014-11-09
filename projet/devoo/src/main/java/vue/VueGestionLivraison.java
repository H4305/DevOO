package vue;

import java.io.File;
import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;


/*
 * swing import
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


import controller.Controller;
/*
 * model import
 */
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

/*
 * util import
 */
import util.Useful;

/*
 * vue import
 */
import vue.widget.VuePlan;

/**
 * 
 */
public class VueGestionLivraison {
	
	private PlanManager mPlanManager;
	private LivraisonManager mLivraisonManager;
	
	private Controller mController;
	
	private JFrame mainFrame;
	private JPanel mainPanel;

    /**
     * 
     */
    public VueGestionLivraison(PlanManager planManager, LivraisonManager livraisonManager, Controller controller) {
    	mPlanManager = planManager;
    	mLivraisonManager = livraisonManager;
    	mController = controller;
    	
    	mainFrame = new JFrame();
    	mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	
    	mainPanel = new JPanel();
    	mainFrame.setLayout(new BorderLayout(2, 2));
    }
    
    /**
     * Affiche la fenetre principale du programme
     */
    public void afficherFenetrePrincipale() {
    	
    	JPanel mainPanel = new JPanel();
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    	
    	//mController.getDemandeLivraisons();
    }

    /**
     * @param circuit
     */
    public void afficherItineraire(List<Troncon> circuit) {
        // TODO implement here
    }
    
    public void afficherPlan(Collection<Troncon> troncons) {
    	
    	VuePlan vuePlan = new VuePlan(troncons);
    	mainPanel.add(vuePlan);
    	mainFrame.pack();
    }

	public File getFichierXMLDemandeLivraison() {
		// TODO Auto-generated method stub
		return Useful.ouvrirFichier('o');
	}

	public void afficherExceptionOuvertureXML(String message) {
    	/* ONLY FOR TEST */
		JPanel mainPanel = new JPanel();
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    	
    	JLabel label = new JLabel(message);
    	mainPanel.add(label);
	}

	public void afficherDemandeLivraisons() {
		/* Demander à livraison manager une demande de livraison
		* Colorier chaque point en fonction de :
		* - Sa plage horaire
		* - S'il est un entrepot
		*/
	}

}