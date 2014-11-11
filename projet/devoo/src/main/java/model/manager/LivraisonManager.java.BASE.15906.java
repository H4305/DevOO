package model.manager;

import java.io.File;
import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * controller import
 */
import controller.Controller;

/*
 * model import
 */
import model.data.DemandeLivraisons;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.manager.PlanManager;
import model.data.Point;
import model.exceptions.LivraisonXMLException;

/*
 * util import
 */
import util.XMLLoader;
import util.generationIDint;
//import util.generationIDint;

/**
 * 
 */
public class LivraisonManager {
	
	private Controller mController;
	private PlanManager mPlanManager;
	private DemandeLivraisons mDemandeLivraisons;
	private generationIDint uniqueIDgenerator = new generationIDint(); 

    
    public LivraisonManager(PlanManager planManager, Controller controller) {
    	this.mPlanManager = planManager;
    	this.mController = controller;
    }

    public DemandeLivraisons getDemandeLivraisons() {
		return mDemandeLivraisons;
	}

    /**
     * @return
     */
    public Itineraire calculItineraire() {
        // TODO implement here
        return null;
    }
    
	/**
	 * 
	 * @param fileXML
	 */
    public void loadDemandeLivraisonsXML(File fileXML) {
    	if (fileXML != null) {             
			// Get the livraison
			try {
				HashMap<Integer, Point> planPoints = this.mPlanManager.getHashMapPlan();
				this.mDemandeLivraisons = XMLLoader.getLivraisonXML(fileXML, planPoints);
				mController.afficherDemandeLivraisons();
				
			} catch (LivraisonXMLException e) {
				// On affichera ca dans la vue
				mController.exceptionOpenFileXML(e.getMessage());
			}
       }     	
    }
    
    public List<PlageHoraire> getPlagesHoraire(){
    	
    	List<PlageHoraire> plagesHoraires = new ArrayList<PlageHoraire>();
    	plagesHoraires.addAll(this.mDemandeLivraisons.getPlagesHoraire());
    	return plagesHoraires;
    }
    
    public List<Livraison> getLivraisons(){
    	List<Livraison> lesLivraisons = new ArrayList<Livraison>();
    	
    	List<PlageHoraire> plagesHoraires = this.getPlagesHoraire();
    	for(PlageHoraire plage: plagesHoraires){
    		lesLivraisons.addAll(plage.getLivraisons());
    	}
    	return lesLivraisons;
    }
    
    public Livraison leLivraison(Point point){
    	List<Livraison> lesLivraisons = getLivraisons();
    	for(Livraison livraison: lesLivraisons){
			if(livraison.getAdresse().equals(point)){
				return livraison;
			}
		}
    	return null;
    }
    
    public void add(Point point){
    	//On cree et on affiche un bo�te de dialogue pour inserer les infos sur la nouvelle livraison 
    	JTextField id_client = new JTextField();
    	JTextField heureDebutInput = new JTextField();
    	JTextField heureFinInput = new JTextField();
    	int id = uniqueIDgenerator.getUniqueId();
    	final JComponent[] inputs = new JComponent[] {
    			new JLabel("Id Client"),
    			id_client,
    			new JLabel("Heure d�but"),
    			heureDebutInput,
    			new JLabel("Heure fin"),
    			heureFinInput
    	};
    	
    	JOptionPane.showMessageDialog(null,inputs, "Ajouter une livraison", JOptionPane.PLAIN_MESSAGE);
    	//on recupere les infos et on cr�e une nouvelle instance de la classe livraison 
    	Point adresse = point; //la nouvelle livraison est rajout�e dans le point o� on a cliqu� 
    	int idClient = Integer.parseInt(id_client.getText());
    	Livraison newLivraison = new Livraison(id, idClient, adresse);
    	//Je annonce que j'ai cree une nouvelle livraison
    	JOptionPane.showMessageDialog(null, "Vous avez introduit une livraison pour le client: " + idClient + " dans la plage horaire " + heureDebutInput.getText() + "-" + heureFinInput.getText() , null, JOptionPane.INFORMATION_MESSAGE);
    	
    	//on recupere les donn�es et on cr�e une nouvelle plage horaire 
    	String heureDebut = heureDebutInput.getText();
    	String heureFin = heureFinInput.getText();
    	List<Livraison> newLivraisonList = new ArrayList<Livraison>();
    	newLivraisonList.add(newLivraison);
    	PlageHoraire plage = new PlageHoraire(heureDebut, heureFin, newLivraisonList);
    	this.addLivraison(newLivraison,plage);
    	
    	//TODO Afficher les plages horaire et choisir une et ne pas cr�er une nouvelle plage horaire � chaque fois
    }
    public void addLivraison(Livraison livraison, PlageHoraire plage){
    	//TODO add la livraison dans l'itineraire (je comprends pas ce qu'il y a en fait dans un itinerarire et comment je peux l'introduire)
    }
    public void remove(Livraison l){
    	JFrame frame = new JFrame("Supprimer une livraison");
    	JOptionPane removeLivraisonPanel = new JOptionPane();
	    int n = JOptionPane.showOptionDialog(frame, " Vous voulez supprimer la livraison � l'adresse: " + l.getAdresse().toString() + " prevu�e � l'heure: " + l.getHeureLivraison(), null, 0, 0, null, null, removeLivraisonPanel);
    	if(n==0) {
    		this.removeLivraison(l);		
    		
    	}
    }
    public void removeLivraison(Livraison l){
    	//TODO Je fais quoi?? je supprime dans l'itineraire la livraison et je recalcule l'itineraire??
    }
}
