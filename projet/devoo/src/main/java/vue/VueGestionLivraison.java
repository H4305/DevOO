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
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    	File f = this.getFichierXMLDemandeLivraison();
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

}