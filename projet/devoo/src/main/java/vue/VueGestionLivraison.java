package vue;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Noeud;
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;
import util.Useful;
import vue.widget.MainPanel;
import vue.widget.PlanPanel;
import vue.widget.PlanPanel.PointClickedListener;
import controller.Controller;

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
    	mainFrame.setLayout(new BorderLayout());
    	
    	mainPanel = new MainPanel(this);
    	mainFrame.setLayout(new BorderLayout(2, 2));
    }

	public void afficherDemandeLivraisons() {
		vuePlan.setDemandeLivraisons(mLivraisonManager.getDemandeLivraisons());
		vuePlan.afficherDemandeLivraison();
	}
    
    public void afficherExceptionOuvertureXML(String message) {
		mainPanel.setErrorMessage(message);
	}

    /**
     * Affiche la fenetre principale du programme
     */
    public void afficherFenetrePrincipale() {
    	mainFrame.add(mainPanel, BorderLayout.CENTER);
    	afficherPlan();
    	mainFrame.pack();
    	mainFrame.setVisible(true);   	
    }
    
    /**
     * @param circuit
     */
    public void afficherItineraire(List<Troncon> circuit) {
        // TODO implement here
    }
    

	public void afficherPlan() {
    	vuePlan = new PlanPanel(mPlanManager.getPlan());
    	vuePlan.setPointClickedListener(new PointClickedListener() {
			
			@Override
			public void pointClicked(Noeud point) {
				mLivraisonManager.add(point);
				
			}
		});
    	vuePlan.repaint();
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
	
	public void afficherLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		mainPanel.setLivraisonSelected(plageHoraire, livraison);
	}
	
	public void masquerLivraison() {
		mainPanel.removeLivraison();
	}
	
	public void chargerPlan() {
		mController.loadPlanXML();
	}
	
	public void chargerLivraison() {
		mController.loadDemandeLivrasonsXML();
	}
	


}
