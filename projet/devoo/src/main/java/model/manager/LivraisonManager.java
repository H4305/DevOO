package model.manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;




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
import model.manager.PlanManager;
import model.data.Noeud;
import model.exceptions.LivraisonXMLException;
import util.CalculesHoraires;
import util.PairIdLivrPrec;
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
    public void setItineraire(Itineraire it){
    	this.mItineraire = it;
    }
    public Itineraire getItineraire(){
    	
    	return this.mItineraire;
    }

    /**
     * Fait le calcul de l'itin√©raire a suivre
     * @return l'itineraire √† suivre
     */
    public void calculItineraire() {
    	List<Set<Noeud>> adresses = new ArrayList<Set<Noeud>>();
    	
        List<PlageHoraire> plagesHoraire = getPlagesHoraire();
        Set<Noeud> setEntrepot = new HashSet<Noeud>();
        setEntrepot.add(mDemandeLivraisons.getEntrepot());
        adresses.add(setEntrepot);
        for(PlageHoraire horaire : plagesHoraire) {
        	Set<Noeud> setPlage = new HashSet<Noeud>();
        	for(Livraison livraison : horaire.getLivraisons()) {
        		setPlage.add(livraison.getAdresse());
        	}
        	adresses.add(setPlage);
        }
        
        setEntrepot.add(mDemandeLivraisons.getEntrepot());
        List<Chemin> chemins = mPlanManager.getChemins(adresses);
        
        this.updateLivraisonsTime(plagesHoraire, chemins);
        
        mItineraire = new Itineraire(chemins);
        mController.afficherItineraire(); //pas de param√®tre
    }
    
    /**
     * Met a jour les horaires des livraisons
     * @param plagesHoraire liste ordonn√©e de plages horaires
     * @param chemins liste ordonn√©e des chemins
     */
    private void updateLivraisonsTime(List<PlageHoraire> plagesHoraire, List<Chemin> chemins) {
    	PlageHoraire plageActuelle = null;
    	String horaire = "00:00";
        for(Chemin chemin : chemins) {
        	//Si nous avons arriv√© √† l'entrepot nous pouvons sortir de la boucle
        	if(chemin.getArrivee().equals(mDemandeLivraisons.getEntrepot())) { 
        		break;
        	}
        	//Met a jour la plage horaire actuelle
        	if(plageActuelle != this.getPlageHoraireByAdress(chemin.getArrivee())) {
        		plageActuelle = this.getPlageHoraireByAdress(chemin.getArrivee());
        		//
        		if(CalculesHoraires.firstBeforeSecond(horaire, plageActuelle.getDateDebut())) {
        			horaire = plageActuelle.getDateDebut();
        		}
        	}
        	//Calcule l'horaire de la prochaine livraison
        	String horaireTmp = CalculesHoraires.sommeHeures(horaire, CalculesHoraires.transformeEnHeureMin(chemin.getTempsParcours()));
        	
        	//Si l'horaire sort de la plage horaire, on lui enleve du graphe
        	if(CalculesHoraires.firstBeforeSecond(plageActuelle.getDateFin(), horaireTmp)) {
        		//TODO PANIC!
        	} else {
        		chemin.getArrivee().getLivraison().setHeureLivraison(horaireTmp);
        	}
        	horaire = CalculesHoraires.sommeHeures(horaireTmp, "00:10");
        }
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
    
    /**
     * This method adds a new delivery.
     * 
     * @param adresseNouvelleLivraison is the Noeud where the new delivery will be performed 
     * @param adresseLivraisonPrecedente is the Noeud of the previous delivery
     * @param idClient is the client id for the new shipping
     */
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
    	
    	String heureLivraisonPrevue = CalculesHoraires.sommeHeures( CalculesHoraires.sommeHeures(livraisonPrecedente.getHeureLivraison(), "00:10"), CalculesHoraires.transformeEnHeureMin(plusCourtCheminPrecNouvelle.getTempsParcours()) ); 
    	
    	if(CalculesHoraires.firstBeforeSecond( heureLivraisonPrevue, plageHoraireLivPrecedente.getDateFin()) ) {
    		/*La livraison Prevue rentre dans la plage horaire -> ajout demandeLivraison,
    		* -> decaler tous
    		* -> set a Livrer
    		* -> supprimer le chemin de l'itineraire et ajouter les deux nouveaux chemins
    		*/
    		nouvelleLivraison.setALivrer();
    		
    		String decalage = CalculesHoraires.transformeEnHeureMin(plusCourtCheminPrecNouvelle.getTempsParcours() + plusCourCheminNouvelleSucc.getTempsParcours() + cheminPrecSucc.getTempsParcours());
    		
    		List <Livraison> livraisons = plageHoraireLivPrecedente.getLivraisons();
    		
    		for(Livraison liv: livraisons) {
    			
    			String heurePassage = liv.getHeureLivraison();
    			
    			if(CalculesHoraires.firstBeforeSecond( heureLivraisonPrevue, heurePassage) ) {
    				
    				String heure = CalculesHoraires.sommeHeures(heurePassage, decalage) ;
    				
    				liv.setHeureLivraison(heure);	
    			}			
    		}			
    		
    		mItineraire.getChemins().remove(cheminPrecSucc);
    		mItineraire.getChemins().add(plusCourtCheminPrecNouvelle);
    		mItineraire.getChemins().add(plusCourCheminNouvelleSucc);  				
    	}
    	
    	plageHoraireLivPrecedente.getLivraisons().add(nouvelleLivraison);
    	
    }

    /**
     * 
     */
    public void exporterFeuilleRoute()
    {
    	String format = "dd/MM/yy"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
    	File file = new File("feuilleDeRoute.txt");
        PrintWriter printWriter = null;
        List<Chemin> chemins = this.mItineraire.getChemins();

        try
        {
            printWriter = new PrintWriter(file);
            printWriter.println("------------------FEUILLE DE ROUTE------------------");
            printWriter.println("Cette feuille de route a pour but d'ennoncer le planning des livraisons des colis le " + formater.format( date )+".");
            printWriter.println("Aujourd'hui vous avez ‡ dÈlivrer " + chemins.size()+" clients.");
            printWriter.println("Suivez les instructions suivantes: ");
            printWriter.print("DÈpart du dÈpot : rue ");
            int i = 0;
            for(Chemin c : chemins){
            	
            	i++; 
            	if(i!=1){
            		printWriter.print("Prendre la rue \"");
        		}
        		            	
            	for(Troncon t : c.getTroncons()){
            		printWriter.println(t.getNomRue() + "\" et continuer " + t.getLongueur() + " metrËs tout droit. ");
            		
            		if(t.getVitesse() < 4){
            			printWriter.println("Attention, ne roulez pas trop vite ! La vitesse maximale autorisÈe est de : " +t.getVitesse()*3.6 +"km/h !");
            		}
            		
            		if(!c.getTroncons().get(c.getTroncons().size()-1).equals(t)){
            			printWriter.print("Prendre la rue \"");
            		}
            		else{
            			if(chemins.get(chemins.size()-1).equals(c) && c.getTroncons().get(c.getTroncons().size()-1).equals(t)){
            				printWriter.println("ArrivÈe au dÈpot. Vous avez fini les livraisons pour aujourd'hui! ");
            				printWriter.println("Bonne fin journÈe !");
            				printWriter.println("---------------------------*****L'Èquipe LIVRAMAP*****---------------------------");
            			}
            			else{           			
            				printWriter.println("");
                			printWriter.println("ArrivÈe au point de livraison numÈro " + i +  "!");
                			printWriter.println("Vous avez 10 min pour remmetre le colis au client ! N'oubliez allumer les clignotants! ");
            			}
            			
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
    /**
     * This method return the object plageHoraire that contains the delivery at the address noeud
     * 
     * @param noeud
     **/
    public PlageHoraire getPlageHoraireByAdress(Noeud noeud){
    	PlageHoraire plageHoraireFound = null; 
    	for(PlageHoraire plageHoraire : this.mDemandeLivraisons.getPlagesHoraire()) {
    		
    		for(Livraison livraison : plageHoraire.getLivraisons()) {
    			
    			if(livraison.getAdresse().equals(noeud)) { 
    				return plageHoraire;    				
    			}
    		}
    	}
    	return plageHoraireFound;
    }
    
    /**
     * This method remove a delivery.
     * 
     * @param adresseLivraison is the address of the delivery that we want to remove 
     */
    public PairIdLivrPrec<Integer, Noeud> supprimerLivraison(Noeud adresseLivraison) {	
    	
    	Livraison livraisonASupprimer = adresseLivraison.getLivraison();
    	
    	int id_client = livraisonASupprimer.getIdClient();
    	
    	PlageHoraire laPlageHoraire = getPlageHoraireByAdress(adresseLivraison);
    	
    	Chemin cheminAvant = mItineraire.getCheminByArrivee(adresseLivraison);
    	Chemin cheminApres = mItineraire.getCheminByDepart(adresseLivraison);

    	Noeud noeudAvant = cheminAvant.getDepart();
    	Noeud noeudApres = cheminApres.getArrivee();
    	
    	Chemin cheminToAdd = mPlanManager.calculerPlusCourtChemin(noeudAvant, noeudApres);
    	
    	
    	mItineraire.getChemins().remove(cheminAvant);
    	mItineraire.getChemins().remove(cheminApres);
		mItineraire.getChemins().add(cheminToAdd);
		
		float tempsAvant = cheminAvant.getTempsParcours();
		float tempsApres = cheminApres.getTempsParcours();
		float tempsToAdd = cheminToAdd.getTempsParcours();
		
		float decalageFloat = tempsToAdd - tempsAvant - tempsApres - 10*60;
		String decalageString = CalculesHoraires.transformeEnHeureMin(decalageFloat);
		
		String tempsAvantString = CalculesHoraires.transformeEnHeureMin(tempsAvant);
		
		//on decalle toutes les livraisons prevues apres la livraison a supprimer dans laPlageHoraire de la livr ÔøΩ supprimer 
		List <Livraison> livraisons = laPlageHoraire.getLivraisons();
		
		for(Livraison liv: livraisons) {
			
			String heurePassage = liv.getHeureLivraison();
			
			if(CalculesHoraires.firstBeforeSecond( tempsAvantString, heurePassage) ) {
				
				String heure = CalculesHoraires.sommeHeures(heurePassage, decalageString) ;
				
				liv.setHeureLivraison(heure);	
			}			
		}			
		
		
		laPlageHoraire.getLivraisons().remove(livraisonASupprimer);
		
		adresseLivraison.setIsLivraison(false);
    
    	return new PairIdLivrPrec<Integer, Noeud>(id_client, noeudAvant);
    } 
    
}
