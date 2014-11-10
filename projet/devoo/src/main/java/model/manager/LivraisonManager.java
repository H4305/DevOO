package model.manager;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;






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
import model.exceptions.PlanXMLException;

/*
 * util import
 */
import util.XMLLoader;
import util.generationIDint;

/**
 * 
 */
public class LivraisonManager {
	
	private Controller mController;
	private PlanManager mPlanManager;
	private DemandeLivraisons mDemandeLivraisons;
	private generationIDint uniqueIDgenerator = new generationIDint(); 

    /**
     * 
     */
    public LivraisonManager() {
    }
    
    public LivraisonManager(PlanManager planManager, Controller controller) {
    	this.mPlanManager = planManager;
    	this.mController = controller;
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
     */
    public void loadDemandeLivraisonsXML(File fileXML) {
    	
    	if (fileXML != null) {
            try {
               // Creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // Lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fileXML);
               Element racine = document.getDocumentElement();
               
               // Get the livraison
               if (racine.getNodeName().equals("JourneeType")) {
               	try {
               		
               		HashMap<Integer, Point> planPoints = this.mPlanManager.getHashMapPlan();
               		
               		this.mDemandeLivraisons = XMLLoader.getLivraisonXML(fileXML, racine, planPoints);
               		
               		mController.afficherDemandeLivraisons();
               		
					} catch (LivraisonXMLException e) {
						// On affichera ca dans la vue
						mController.exceptionOpenFileXML(e.getMessage());
					}
               }
               else {
            	   mController.exceptionOpenFileXML("Structure de fichier inconnue");
               }
              
           } catch (ParserConfigurationException pce) {
        	   mController.exceptionOpenFileXML("Erreur de configuration du parseur DOM");
        	   mController.exceptionOpenFileXML("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
        	   mController.exceptionOpenFileXML("Erreur lors du parsing du document");
        	   mController.exceptionOpenFileXML("lors de l'appel a construteur.parse(xml)");
           } catch (IOException ioe) {
        	   mController.exceptionOpenFileXML("Erreur d'entree/sortie");
        	   mController.exceptionOpenFileXML("lors de l'appel a construteur.parse(xml)");
           }
       }     	
    }
    
    public List<PlageHoraire> getPlagesHoraire(){
    	List<PlageHoraire> plagesHoraires = new ArrayList<PlageHoraire>();
    	Date d = new Date();
    	plagesHoraires.add(this.mDemandeLivraisons.getPlageHoraire(d));
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
    public void add(Point p){
    	int id = uniqueIDgenerator.getUniqueId();
    	Point adresse = p;
    	JOptionPane addLivraisonPanel = new JOptionPane();
    	String id_clientString = addLivraisonPanel.showInputDialog(null, "Id client:", null, JOptionPane.QUESTION_MESSAGE);
    	addLivraisonPanel.showMessageDialog(null, "Vous avez introduit une livraison pour le client: " + id_clientString, null, JOptionPane.INFORMATION_MESSAGE);
    	  
    	int id_client = Integer.parseInt(id_clientString);
    	Livraison newLivraison = new Livraison(id,id_client,adresse);
    	//TODO ADD la livraison dans l'itineraire
    	//TODO Afficher les plages horaire et choisir une 
    }
    
    public void remove(Livraison l){
    	//TODO afficher infos livraison
    	JOptionPane removeLivraisonPanel = new JOptionPane();
	    int n = JOptionPane.showOptionDialog(null, "Vous voulez supprimer cette livraison?", null, 0, 0, null, null, removeLivraisonPanel);
    	if(n==0) {
    		//supprimer   		
    		
    	}else{
    		//rien?
    	}
    }
}