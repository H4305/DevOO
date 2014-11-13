package vue;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ch.qos.logback.core.joran.conditional.ThenAction;
import model.data.DemandeLivraisons;
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
 *	Gestionnaire de vue qui gère les actions déclenchée par les différente vues
 *	et gère l'affichage de celle ci.
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
    * @param planManager Manager du plan dans le modèle
    * @param livraisonManager Manager des livraisons dans le modèle
    * @param controller Controlleur du programme
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

    /**
     * Affiche sur le {@link PlanPanel} les points de livraison contenus
     * dans le fichier de livraison chargé.
     * @see VueGestionLivraison#chargerLivraison()
     */
	public void afficherDemandeLivraisons() {
		mainPanel.getVueLivraison().resetInfoLivraison();
		mainPanel.setCalculItineraire(true);
		vuePlan.setDemandeLivraisons(mLivraisonManager.getDemandeLivraisons());
		vuePlan.afficherDemandeLivraison();
	}
    
    public void afficherExceptionOuvertureXML(String message) {
		mainPanel.setErrorMessage(message);
	}

    /**
     * Affiche la fenetre principale du programme.
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
    public void afficherItineraire() {
        mLivraisonManager.getItineraire().getChemins();
        
        vuePlan.afficherItineraire();
    }
    

	/**
	 * Display the map corresponding to the XML Loaded previously.
	 * @see VueGestionLivraison#chargerPlan()
	 */
	public void afficherPlan() {
    	vuePlan = new PlanPanel(mPlanManager.getPlan());
    	vuePlan.setPointClickedListener(new PointClickedListener() {
			
			@Override
			public void pointClicked(Noeud point) {
				if(!point.isLivraison()){
					add(point);
				} else {
					mController.afficherLivraison(point);
				}
			}
		});
    	vuePlan.repaint();
    	mainPanel.setPlan(vuePlan);
    	mainFrame.pack();
    }
	
	/**
	 * Open a dialog to select an XML {@link File}.
	 * @return The selected XML File.
	 */
	public File getFichierXML() {
		return Useful.ouvrirFichier('o');
	}

	/**
	 * Set the listener called when any {@link Noeud} of the {@link PlanPanel} is clicked.
	 * @param pointClickedListener The listener that will be called.
	 */
	public void setPointClickedListener(
			PlanPanel.PointClickedListener pointClickedListener) {
		this.pointClickedListener = pointClickedListener;
	}
	
	/**
	 * Display the information about the selected {@link Livraison}.
	 * @param plageHoraire The {@link PlageHoraire} which contains the selected {@link Livraison}.
	 * @param livraison The selected {@link Livraison} in the {@link PlanPanel}.
	 * 
	 * @see PlanPanel
	 * @see PlageHoraire
	 * @see Livraison
	 */
	public void afficherLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		mainPanel.setLivraisonSelected(plageHoraire, livraison);
	}
	
	/**
	 * Remove the information about the last {@link Livraison} selected.
	 */
	public void masquerLivraison() {
		mainPanel.resetInfoLivraison();
	}
	
	/**
	 * Ask the controller to load the Map from an XML file.
	 */
	public void chargerPlan() {
		mController.loadPlanXML();
	}
	
	
	/**
	 * Ask the controller to load the {@link DemandeLivraisons} from an XML file
	 */
	public void chargerLivraison() {
		mController.loadDemandeLivrasonsXML();
	}
	
    public void add(Noeud point){ 
    	//JOptionPane.showMessageDialog(null, null, "Ajouter une livraison", JOptionPane.PLAIN_MESSAGE);
    	
    	JTextField id_client = new JTextField();
    	final JComponent[] inputs = new JComponent[] {
    			new JLabel("Id Client"),
    			id_client
    	};
    	JOptionPane.showMessageDialog(null, inputs, "Ajouter une livraison", JOptionPane.PLAIN_MESSAGE);
    	if (!id_client.getText().equals("")) {
    		int idClient = Integer.parseInt(id_client.getText());
    		JOptionPane.showMessageDialog(null, "Vous avez introduit une livraison pour le client: " + idClient, null, JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"Problem with the ID", null, JOptionPane.INFORMATION_MESSAGE);
    	}	
    }
    
    /**
     * Display a popup asking a confirmation to delete the {@link Livraison} at the given {@link Noeud}
     * @param p The address of the {@link Livraison} to delete.
     * 
     * @see Livraison
     * @see Noeud
     */
    public void afficherDialogConfirmationSuppressionLivraison(Noeud p) {
    	JFrame frame = new JFrame("Supprimer une livraison");
    	JOptionPane removeLivraisonPanel = new JOptionPane();
	    int n = JOptionPane.showOptionDialog(frame, " Vous voulez supprimer la livraison à l'adresse: " + p.toString() + "?", 
	    		"Suppression", 
	    		JOptionPane.YES_NO_OPTION, 
	    		JOptionPane.QUESTION_MESSAGE,
	    		null, 
	    		null, 
	    		removeLivraisonPanel);
    	if(n==JOptionPane.YES_OPTION) {
    		//this.removeLivraison(l);		
    		//on supprime la livraison
    	}
    }

    /**
     * Remove the {@link Livraison} selected in the PlanPanel (if there is one selected).
     */
	public void removeSelectedLivraison() {
		Livraison livraison = mainPanel.getVueLivraison().getLivraison();
		if(livraison != null) {
			afficherDialogConfirmationSuppressionLivraison(livraison.getAdresse());
		}
		
	}

	public void chargerTournee() {
		mLivraisonManager.calculItineraire();	
	}

}
