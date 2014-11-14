 package vue;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import ch.qos.logback.core.joran.conditional.ThenAction;
import model.data.DemandeLivraisons;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Noeud;
import model.data.Troncon;
import model.manager.PlanManager;
import util.Useful;
import vue.widget.MainPanel;
import vue.widget.PlanPanel;
import vue.widget.PlanPanel.PointClickedListener;
import controller.Controller;
import devoo.LivraisonManagerTest;

/**
 *	Gestionnaire de vue qui g�re les actions d�clench�e par les diff�rente vues
 *	et g�re l'affichage de celle ci.
 *
 */
public class VueGestionLivraison {
	
	private PlanManager mPlanManager;
	private LivraisonManagerTest mLivraisonManager;
	private Controller mController;
	
	private JFrame mainFrame;
	private MainPanel mainPanel;
	private PlanPanel vuePlan;
	
	PlanPanel.PointClickedListener pointClickedListener;

   /**
    * @param planManager Manager du plan dans le mod�le
    * @param livraisonManager Manager des livraisons dans le mod�le
    * @param controller Controlleur du programme
    */
    public VueGestionLivraison(PlanManager planManager, LivraisonManagerTest livraisonManager, Controller controller) {
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
     * dans le fichier de livraison charg�.
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
    //    mLivraisonManager.getItineraire().getChemins();
        vuePlan.setItineraire(mLivraisonManager.getItineraire());
        
        vuePlan.afficherItineraire();
    }
    

	/**
	 * Display the map corresponding to the XML Loaded previously.
	 * @see VueGestionLivraison#chargerPlan()
	 */
	public void afficherPlan() {
    	vuePlan = new PlanPanel(mPlanManager.getPlan());
    	pointClickedListener = new PointClickedListener() {
			
			@Override
			public void pointClicked(Noeud point) {
				if(!point.isLivraison() && vuePlan.hasItienraire()){
					askForSecondPoint(point);
				} else {
					mController.afficherLivraison(point);
				}
			}
		};
    	vuePlan.setPointClickedListener(pointClickedListener);
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
	
    public void askForSecondPoint(Noeud point){ 
    	//JOptionPane.showMessageDialog(null, null, "Ajouter une livraison", JOptionPane.PLAIN_MESSAGE);
    	
    	JTextField id_client = new JTextField();
    	final JComponent[] inputs = new JComponent[] {
    			new JLabel("Id Client"),
    			id_client
    	};
    	JOptionPane.showMessageDialog(mainFrame, inputs, "Client Id :", JOptionPane.WARNING_MESSAGE);
    	
    	if (!id_client.getText().equals("")) {
    		int idClient = Integer.parseInt(id_client.getText());
    		mainPanel.setInformationMessage("Selectionner apr�s quelle livraison inserer la nouvelle.");
    		waitForSecondPoint(point, idClient);
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"Problem with the ID", null, JOptionPane.WARNING_MESSAGE);
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
	    int n = JOptionPane.showOptionDialog(frame, " Vous voulez supprimer la livraison � l'adresse: " + p.toString() + "?", 
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
	
	public void pointClicked(Noeud noeud) {
		
	}
	
	public void waitForSecondPoint(final Noeud first,final int idClient) {
		vuePlan.setPointClickedListener(new PointClickedListener() {
			
			@Override
			public void pointClicked(Noeud point) {
				if(point.isLivraison()) {
					enableAddLivraisonButton(first, point, idClient);
				} else {
					mainPanel.disablebtnAjouter();
				}
			}
		});
	}
	
	public void exporterFeuilleDeRoute(){
		mLivraisonManager.exporterFeuilleRoute();
	}
	
	public void ajouterLivraison(Noeud nouveau, Noeud precedent, int idClient) {
		mLivraisonManager.addNouvelleLivraison(nouveau, precedent, idClient);
		JOptionPane.showMessageDialog(null, 
				"Une nouvelle livraison pour le client " + idClient +  " a �t� ajout�e.",
				"Livraison ajout�e", JOptionPane.PLAIN_MESSAGE);
		mainPanel.disablebtnAjouter();
	}
	
	public void enableAddLivraisonButton(final Noeud nouveau, final Noeud precedent, final int idClient) {
		mainPanel.getBtnAjouter().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouterLivraison(nouveau, precedent, idClient);
				vuePlan.setPointClickedListener(pointClickedListener);
			}
		});
		mainPanel.getBtnAjouter().setEnabled(true);;
	}
	
	public void afficherLivraisonImpossible(List<Livraison> livraisons) {
		if(livraisons.size() < 1) return;
		
		StringBuilder builder = new StringBuilder();
		builder
		.append(livraisons.size() > 1 ? "Les" : "La")
		.append(" livraison")
		.append(livraisons.size() > 1 ? "s " : " ");
		
		for (Livraison livraison : livraisons) {
			builder.append(livraison.getId()).append(", ");
		}
		if(livraisons.size() > 1) {
			builder.append("ne peuvent pas �tre livr�es");
		} else {
			builder.append("ne peut pas �tre livr�e");
		}
		builder.delete(builder.lastIndexOf(","), builder.lastIndexOf(",") +1).append(".");
		JOptionPane.showMessageDialog(mainFrame, builder.toString());
	}

}
