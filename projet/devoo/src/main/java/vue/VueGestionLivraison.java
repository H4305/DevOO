package vue;

import java.io.File;
import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;
import util.Useful;
import vue.widget.MainPanel;
import vue.widget.PlanPanel;

/**
 * 
 */
public class VueGestionLivraison {
	
	private PlanManager mPlanManager;
	private LivraisonManager mLivraisonManager;
	
	private Controller mController;
	
	private JFrame mainFrame;
	private MainPanel mainPanel;
	private PlanPanel vuePlan;
	
	PlanPanel.PointClickedListener pointClickedListener;

    /**
     * 
     */
    public VueGestionLivraison(PlanManager planManager, LivraisonManager livraisonManager, Controller controller) {
    	mPlanManager = planManager;
    	mLivraisonManager = livraisonManager;
    	mController = controller;
    	
    	mainFrame = new JFrame();
    	mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	
    	mainPanel = new MainPanel(this);
    	mainFrame.setLayout(new BorderLayout(2, 2));
    }

	public void afficherDemandeLivraisons() {
		/* Demander à livraison manager une demande de livraison
		* Colorier chaque point en fonction de :
		* - Sa plage horaire
		* - S'il est un entrepot
		*/
	}
    
    public void afficherExceptionOuvertureXML(String message) {
    	/* TODO ONLY FOR TEST */
		JPanel mainPanel = new JPanel();
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    	
    	JLabel label = new JLabel(message);
    	mainPanel.add(label);
	}

    /**
     * Affiche la fenetre principale du programme
     */
    public void afficherFenetrePrincipale() {
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    	afficherPlan();
    }
    
    /**
     * @param circuit
     */
    public void afficherItineraire(List<Troncon> circuit) {
        // TODO implement here
    }
    

	public void afficherPlan() {
    	vuePlan = new PlanPanel(mPlanManager.getPlan());
    	vuePlan.setPointClickedListener(pointClickedListener);
    	mainPanel.setPlan(vuePlan);
    	mainFrame.pack();
    }

	public File getFichierXML() {
		return Useful.ouvrirFichier('o');
	}

	public void setPointClickedListener(
			PlanPanel.PointClickedListener pointClickedListener) {
		this.pointClickedListener = pointClickedListener;
	}
	
	public void chargerPlan() {
		//TODO mController.
	}
	


}
