package vue;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

/**
 * 
 */
public class VueGestionLivraison {
	
	private PlanManager mPlanManager;
	private LivraisonManager mLivraisonManager;
	
	private JFrame mainFrame;
	private JPanel mainPanel;

    /**
     * 
     */
    public VueGestionLivraison(PlanManager planManager, LivraisonManager livraisonManager) {
    	mPlanManager = planManager;
    	mLivraisonManager = livraisonManager;
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
    	JLabel helloWorldLabel = new JLabel();
    	helloWorldLabel.setText("A");
    	mainPanel.add(helloWorldLabel);
    	mainFrame.add(mainPanel);
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
    	Collection<Troncon> troncons = mPlanManager.getAllTroncons();
    	
    	
    	
    }

}