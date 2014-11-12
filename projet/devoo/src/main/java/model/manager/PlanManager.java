package model.manager;

import java.util.*;

import util.Dijkstra;
import util.TwoKeyMap;
import util.Vertex;

import java.io.File;

import controller.Controller;

import util.Dijkstra;
import util.PairKey;
import util.Vertex;
import util.XMLLoader;

import model.data.Chemin;
import model.data.Noeud;
import model.data.Troncon;
import model.exceptions.PlanXMLException;

/**
 * 
 * PlanManager has the role to manage all the data related to the plan. 
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
public class PlanManager {
	
	private Controller mController;
	private Set<Troncon> troncons;
	private ArrayList<Vertex> vertexs;

	/**
    * Class constructor specifying a Controller
    * 	    
    * @param controller is the Controller, to advise the system when operations are concluded.
    */
    public PlanManager(Controller controller) {
    	this.mController = controller;
    	this.troncons = new HashSet<Troncon>();
    	this.vertexs = new ArrayList<Vertex>();
    }
    
    /**
	 * This method loads a "plan" from a xml file, passing through the XMLLoader class,
	 * and stores the result to the attributes troncons and vertexs. An exception is cached if there's a problem
	 * opening or reading the file.
	 * 
	 * @param fileXML is a xml File, which contains all the informations concerning a plan.
	 * @throws NullPointException() if file is empty
	 */
    public void loadPlanXML(File fileXML) throws NullPointerException {
    	if (fileXML != null) {
			// Get the plan
			try {
				
				PairKey<Set<Troncon>, ArrayList<Vertex>> tronconsVertexs = XMLLoader.getPlanXML(fileXML);
				setPlan(tronconsVertexs.troncons);
				setVertexs(tronconsVertexs.vertexs);	
				mController.afficherPlan();
				
			} catch (PlanXMLException e) {
				// On affichera ca dans la vue
				mController.exceptionOpenFileXML(e.getMessage());
			}
       } else {
    	   throw new NullPointerException();
       }
    }
    
    /**
     * Cette methode calcule le plus court chemin entre 2 points avec la methode de Dijkstra
     * 
     * @param Noeud source 
     * @param Noeud target  
     * @param Vertex source
     * @param Vertex target 
     * @return Chemin 
     */
    public Chemin calculerPlusCourtChemin(Noeud source, Noeud cible) {
        
    	Vertex vSource = null;
    	Vertex vCible = null;
    	
    	for(Vertex v: vertexs) {
    		
    		if(v.getPoint().equals(source)) {
    			vSource = v;
    		}
    		
    		if(v.getPoint().equals(cible)) {
    			vCible = v;
    		}
    	}
    	
    	ArrayList<Noeud> pointsDuCourtChemin = new ArrayList<Noeud>();
    	ArrayList<Vertex> vertexCourtChemin = new ArrayList<Vertex>();
    	
    	Dijkstra.computePaths(vSource);
    	vertexCourtChemin = Dijkstra.getShortestPathTo(vCible);  //on recupere la liste des vertex du plus court chemin
    	
    	for(Vertex v : vertexCourtChemin)    //on recupere la liste des points correspondants aux vertex
    	{
    		pointsDuCourtChemin.add(v.getPoint()); 
    	}    	
    	
        Chemin chemin = new Chemin(cible, source);  
        //TODO faire set dureeTrajet tempsParcours
    	
        for(int i = 0; i < pointsDuCourtChemin.size()-1; i++)   //on parcourt la liste des points du plus court chemin
        {
        	for(Troncon t : pointsDuCourtChemin.get(i).getTronconsSortants())  //on parcourt la liste de troncons sortants des points du plus court chemin
        	{
        		if(t.getArrivee().equals(pointsDuCourtChemin.get(i+1)))  //on verifie si on est sur le troncon partant du point i et arrivant au point i+1
        		{
        			chemin.addTronconChemin(t);   //on ajoute le troncon au chemin
        			break;
        		}
        	}
        }      
    		
        return chemin;
    }

    /**
     * @param Points 
     * @return
     */
    public Chemin getChemin(List<Set<Noeud>> plages) {
    	TwoKeyMap<Noeud, Noeud, Chemin> courtsChemins = new TwoKeyMap<Noeud, Noeud, Chemin>();
        Set<Noeud> plagePrecedente = null;
    	//Pour chaque plage
    	for(Set<Noeud> plage : plages) {
    		//On calcule le meilleur chemin entre tous les points de cette plage
    		for(Noeud orig : plage) {
    			for(Noeud dest : plage) {
            		if(!orig.equals(dest)) {
            			courtsChemins.put(orig, dest, calculerPlusCourtChemin(orig, dest));
            		}
            	}
    		}
    		//On calcule le meilleur chemin de tous les points de la plage précedente a la plage actuelle
    		if(plagePrecedente != null) {
    			for(Noeud orig : plagePrecedente) {
    				for(Noeud dest : plage) {
    					courtsChemins.put(orig, dest, calculerPlusCourtChemin(orig, dest));
    				}
    			}
    		}
    		plagePrecedente = plage;
    	}
        
        return null;
    }

    /**
     * @param listTroncon 
     * @param circuit
     */
    public void getChemin(Set<Troncon> listTroncon, List<Troncon> circuit) {
        // TODO implement here
    }
    
    /**
     * Ajoute un point au plan
     * @param troncon
     */
    public void addTroncon(Troncon troncon) {
    	troncons.add(troncon);
    }
    
    public Collection<Troncon> getAllTroncons() {
    	return troncons;
    }
    
    public class TronconNotFoundExcepetion extends Exception {
    	
		private static final long serialVersionUID = 3754278865880411751L;

		@Override
    	public String getMessage() {
    		return "Le troncon demand� n'existe pas";
    	}
    }

    /**
	 * This method reads the list of "troncons" and create a HashMap<Integer, Point>
	 * 
	 * @return a HashMap, which key is the id of a point and value is the point itself
	 */
	public HashMap<Integer, Noeud> getHashMapPlan() {

		HashMap<Integer, Noeud> planPoints = new HashMap<Integer, Noeud>();
		
		for(Troncon troncon: troncons)
		{	
			Noeud point = troncon.getDepart();
			int id = point.getId();
			
			if( !planPoints.containsKey(id) ) {
				planPoints.put(id, point);
			}
		}
		
		return planPoints;
	}
	
	public Set<Troncon> getPlan() {
		return troncons;
	}
	
	public void setPlan(Set<Troncon> troncons) {
		this.troncons = troncons;
	}
	
	public void setVertexs(ArrayList<Vertex> vertexs) {
		this.vertexs = vertexs;
	}
}
