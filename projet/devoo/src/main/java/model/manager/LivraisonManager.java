package model.manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;



/*
 * controller import
 */
import controller.Controller;

/*
 * model import
 */
import model.data.Chemin;
import model.data.Troncon;
import model.data.DemandeLivraisons;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.ZoneGeographique;
import model.manager.PlanManager;
import model.data.Noeud;
import model.exceptions.LivraisonXMLException;

/*
 * util import
 */
import util.XMLLoader;
import util.GenerationIDint;

/**
 * 
 * LivraisonManager has the role to manage all the data related to the "livraisons". 
 * It stores most of the mathematics results and it's a bridge between the controller and the model.
 * 
 * @author      Vadim Caen
 * @author      Maria Etegan
 * @author      Anthony Faraut
 * @author      Ludmila Danilescu
 * @author      Marco Montalto
 * @author      Bernardo Rittmeyer
 * 
 */
public class LivraisonManager {
	
	private Controller mController;
	private PlanManager mPlanManager;
	private DemandeLivraisons mDemandeLivraisons;
	private GenerationIDint uniqueIDgenerator; 
	private Itineraire mItineraire;

   /**
    * Class constructor specifying a Controller and a PlanManager
    * 
    * @param planManager is the PlanManager, necessary as LivraisonManager needs the plan data.
    * @param controller is the Controller, to advise the system when operations are concluded.
    */
    public LivraisonManager(PlanManager planManager, Controller controller) {
    	this.mPlanManager = planManager;
    	this.mController = controller;
    	this.uniqueIDgenerator = new GenerationIDint();
    }
    
    /**
	 * This method loads a "demande de livraisons" from a xml file, passing through the XMLLoader class,
	 * and stores the result to the attributes mDemandeLivraisons. An exception is cached if there's a problem
	 * opening or reading the file.
	 * 
	 * @param fileXML is a xml File, which contains all the informations concerning a "demande de livraisons"
	 * @throws NullPointException() if file is empty
	 */
    public void loadDemandeLivraisonsXML(File fileXML) throws NullPointerException {
    	if (fileXML != null) {             
			// Get the livraison
			try {
				HashMap<Integer, Noeud> planPoints = this.mPlanManager.getHashMapPlan();
				this.mDemandeLivraisons = XMLLoader.getLivraisonXML(fileXML, planPoints);
				mController.afficherDemandeLivraisons();
				
			} catch (LivraisonXMLException e) {
				// On affichera ca dans la vue
				mController.exceptionOpenFileXML(e.getMessage());
			}
       }else {
    	   throw new NullPointerException();
       }
    }

    public DemandeLivraisons getDemandeLivraisons() {
		return mDemandeLivraisons;
	}
    
    public Itineraire getItineraire(){
    	
    	return this.mItineraire;
    }

    /**
     * Fait le calcul de l'itinéraire a suivre
     * @return l'itineraire à suivre
     */
    public Itineraire calculItineraire() {
    	List<Set<Noeud>> adresses = new ArrayList<Set<Noeud>>();
    	
        List<PlageHoraire> plagesHoraire = getPlagesHoraire();
        Set<Noeud> setEntrepot = new TreeSet<Noeud>();
        setEntrepot.add(mDemandeLivraisons.getEntrepot());
        adresses.add(setEntrepot);
        for(PlageHoraire horaire : plagesHoraire) {
        	Set<Noeud> setPlage = new TreeSet<Noeud>();
        	for(Livraison livraison : horaire.getLivraisons()) {
        		setPlage.add(livraison.getAdresse());
        	}
        	adresses.add(setPlage);
        }
        setEntrepot.add(mDemandeLivraisons.getEntrepot());
        Chemin chemin = mPlanManager.getChemin(adresses);
        //TODO
        //mItineraire = new Itineraire(ArrayList<Chemin>);
        mController.afficherItineraire(chemin); //pas de paramètre
        return null;
    }
	
    public List<PlageHoraire> getPlagesHoraire() {
    	
    	return this.mDemandeLivraisons.getPlagesHoraire();
    }
    
    public List<Livraison> getLivraisons(){
    	List<PlageHoraire> plagesHoraires = this.getPlagesHoraire();
    	if(plagesHoraires == null) return new ArrayList<>();
    	
    	List<Livraison> lesLivraisons = new ArrayList<Livraison>();
    	for(PlageHoraire plage: plagesHoraires){
    		lesLivraisons.addAll(plage.getLivraisons());
    	}
    	return lesLivraisons;
    }
       
    public void addLivraison(Livraison livraison, PlageHoraire plage){
    	//TODO add la livraison dans l'itineraire (je comprends pas ce qu'il y a en fait dans un itinerarire et comment je peux l'introduire)
    }
    
    public void removeLivraison(Livraison l){
    	//TODO Je fais quoi?? je supprime dans l'itineraire la livraison et je recalcule l'itineraire??
    }
    
    public Livraison findLivraisonByAddress(Noeud address) {
    	for(Livraison livraison : getLivraisons()) {
    		if(livraison.getAdresse().equals(address)) 
    			return livraison;
    	}
    	return null;
    }
    
    public PlageHoraire findPlageHoraireByLivraison(Livraison livraison) {
    	for(PlageHoraire horaire : getPlagesHoraire()) {
    		for(Livraison liv : horaire.getLivraisons()) {
    			if(livraison.equals(liv)) {
    				return horaire;
    			}
    		}
    	}
    	return null;
    }
    
    public void addNouvelleLivraison(Noeud adresseNouvelleLivraison, Noeud adresseLivraisonPrecedente, int idClient) {	
    	
    	Livraison nouvelleLivraison = new Livraison(uniqueIDgenerator.getUniqueId(), idClient, adresseNouvelleLivraison);
    	
    	Livraison livraisonPrecedente = null;
    	PlageHoraire plageHoraireLivPrecedente = null;
    	
    	for(PlageHoraire plageHoraire : this.mDemandeLivraisons.getPlagesHoraire()) {
    		
    		for(Livraison livraison : plageHoraire.getLivraisons()) {
    			
    			if(livraison.getAdresse().equals(adresseLivraisonPrecedente)) {
    				
    				livraisonPrecedente = livraison;
    				plageHoraireLivPrecedente = plageHoraire;
    				
    			}
    		}
    	}
    	
    	Chemin cheminPrecSucc = mItineraire.getCheminByDepart(adresseLivraisonPrecedente);
    	
    	Noeud adresseLivraisonSuccessive = mItineraire.getAdresseArriveByDepart(adresseLivraisonPrecedente);
    	
    	Chemin plusCourtCheminPrecNouvelle = mPlanManager.calculerPlusCourtChemin(adresseLivraisonPrecedente, adresseNouvelleLivraison);
    	
    	Chemin plusCourCheminNouvelleSucc = mPlanManager.calculerPlusCourtChemin(adresseNouvelleLivraison, adresseLivraisonSuccessive);
    	
    	String heureLivraisonPrevue = sommeHeures( sommeHeures(livraisonPrecedente.getHeureLivraison(), "00:10"), this.transformeEnHeureMin(plusCourtCheminPrecNouvelle.getTempsParcours()) ); 
    	
    	if(this.firstBeforeSecond( heureLivraisonPrevue, plageHoraireLivPrecedente.getDateFin()) ) {
    		/*La livraison Prevue rentre dans la plage horaire -> ajout demandeLivraison,
    		* -> decaler tous
    		* -> set a Livrer
    		* -> supprimer le chemin de l'itineraire et ajouter les deux nouveaux chemins
    		*/
    		nouvelleLivraison.setALivrer();
    		
    		String decalage = this.transformeEnHeureMin(plusCourtCheminPrecNouvelle.getTempsParcours() + plusCourCheminNouvelleSucc.getTempsParcours() + cheminPrecSucc.getTempsParcours());
    		
    		List <Livraison> livraisons = plageHoraireLivPrecedente.getLivraisons();
    		
    		for(Livraison liv: livraisons) {
    			
    			String heurePassage = liv.getHeureLivraison();
    			
    			if(this.firstBeforeSecond( heureLivraisonPrevue, heurePassage) ) {
    				
    				String heure = this.sommeHeures(heurePassage, decalage) ;
    				
    				liv.setHeureLivraison(heure);	
    			}			
    		}			
    		
    		mItineraire.getChemins().remove(cheminPrecSucc);
    		mItineraire.getChemins().add(plusCourtCheminPrecNouvelle);
    		mItineraire.getChemins().add(plusCourCheminNouvelleSucc);  				
    	}
    	
    	plageHoraireLivPrecedente.getLivraisons().add(nouvelleLivraison);
    	
    }
    
    //A tester
    public String sommeHeures(String heureA, String heureB) {
    	
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        
        Calendar calFirst = Calendar.getInstance();
        Calendar calSecond = Calendar.getInstance();
        
		try {
			calFirst.setTime(formatter.parse(heureA));
			calSecond.setTime(formatter.parse(heureA));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		calFirst.setTimeInMillis(calFirst.getTimeInMillis() + calSecond.getTimeInMillis());
		
		Date heure = calFirst.getTime();
		
		System.out.println(heure);
		
		return heure.toString();
    	
    }
    
    public Boolean firstBeforeSecond(String heureA, String heureB) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    	
    	Calendar calFirst = Calendar.getInstance();
        Calendar calSecond = Calendar.getInstance();
        
		try {
			calFirst.setTime(formatter.parse(heureA));
			calSecond.setTime(formatter.parse(heureB));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return calFirst.before(calSecond);	
    }
    
    public String transformeEnHeureMin(float tempsParcours) {
    	
    	int heure = (int) ((int) tempsParcours) / 3600;
    	int min = heure % 3600;
    	
    	return heure + ":" + min;
    	
    }
    
    public void exporterFeuilleRoute()
    {
    	String format = "dd/MM/yy"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
    	File file = new File("C:/Users/Liuda/Desktop/file.txt");
        PrintWriter printWriter = null;
        ArrayList<Chemin> chemins = this.mItineraire.getChemins();

        try
        {
            printWriter = new PrintWriter(file);
            printWriter.println("------------------FEUILLE DE ROUTE------------------");
            printWriter.println("Cette feuille de route a pour but d'ennoncer le planning des livraisons des colis le " + formater.format( date )+".");
            printWriter.println("Aujourd'hui vous avez � d�poser " + chemins.size()+" clients.");
            printWriter.println("Suivez les instructions suivantes: ");
            printWriter.print("D�part du d�pot : rue ");
            for(Chemin c : chemins){
            	
            	for(Troncon t : c.getTroncons())
            	{
            		printWriter.println(t.getNomRue());
            		if(!c.getTroncons().get(c.getTroncons().size()-1).equals(t))
            		{
            			printWriter.print("Prendre : rue ");
            		}
            		else
            		{
            			printWriter.print("Vous etes arriv� � la livraison ....");
            		}
            	}
            	
            	
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( printWriter != null ) 
            {
                printWriter.close();
            }
        }
    	
    }
    
}
