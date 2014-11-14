package model.manager;

import java.util.*;

import util.Dijkstra;
import util.TwoKeyMap;
import util.Vertex;

import java.io.File;

import controller.Controller;
import util.CheminGraph;
import util.Dijkstra;
import util.PairKey;
import util.SolutionState;
import util.TSP;
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
	private List<Vertex> listVertexs;

	/**
    * Class constructor specifying a Controller
    * 	    
    * @param controller is the Controller, to advise the system when operations are concluded.
    */
    public PlanManager(Controller controller) {
    	this.mController = controller;
    	this.troncons = new HashSet<Troncon>();
    	this.listVertexs = new ArrayList<Vertex>();
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
				this.setPlan(tronconsVertexs.troncons);
				this.setVertexs(tronconsVertexs.vertexs);	
				for(Troncon t : troncons) {
					Noeud n = t.getArrivee();
					for(Vertex v : this.listVertexs) {
						if(v.getPoint().equals(n)) {
							t.setTarget(v);
						}
					}
				}
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
    	float tempsParcours = 0;
    	Dijkstra dijkstra = new Dijkstra();

		for(Vertex v: this.listVertexs) {
			v.setPrecedent(null);
			v.setMinTemps(Double.POSITIVE_INFINITY);
			if(v.getPoint().equals(source)) {
    			vSource = v;
    			//System.out.println("La source :" + v.getPoint().toString());
    		}
    		
    		if(v.getPoint().equals(cible)) {
    			vCible = v;
    			//System.out.println("La cible :" + v.getPoint().toString());
    		}
    	}
    	
    	ArrayList<Noeud> pointsDuCourtChemin = new ArrayList<Noeud>();
    	ArrayList<Vertex> vertexCourtChemin = new ArrayList<Vertex>();

    	dijkstra.computePaths(vSource);
    	
    	//System.out.println("Apres computePaths : ");
    	vertexCourtChemin = dijkstra.getShortestPathTo(vCible);  //on recupere la liste des vertex du plus court chemin
    	
    	//System.out.println("MERDE");
    	//for(Vertex v : vertexCourtChemin)
    	//	System.out.println(v.getPoint().getId());
    	
    	//System.out.println("Apres Dijkstra : ");

    	for(Vertex v : vertexCourtChemin)    //on recupere la liste des points correspondants aux vertex
    	{
    		//System.out.println(v.getPoint().toString());
    		pointsDuCourtChemin.add(v.getPoint());     		
    	}    	
        Chemin chemin = new Chemin(cible, source);  
        
        for(int i = 0; i < pointsDuCourtChemin.size()-1; i++)   //on parcourt la liste des points du plus court chemin
        {
        	for(Troncon t : pointsDuCourtChemin.get(i).getTronconsSortants())  //on parcourt la liste de troncons sortants des points du plus court chemin
        	{
        		if(t.getArrivee().equals(pointsDuCourtChemin.get(i+1)))  //on verifie si on est sur le troncon partant du point i et arrivant au point i+1
        		{
        			chemin.addTronconChemin(t);   //on ajoute le troncon au chemin
        			tempsParcours += t.getDuree();
        			break;
        		}
        	}
        } 
        
        chemin.setTempsParcours(tempsParcours);

        return chemin;
    }

    /**
     * Fait le calcul du chemin optimal pour la livraison
     * @param Points Une liste ordonnée de Sets des points de chaque plage horaire
     * @return une liste des chemins à suivre pour la livraison
     */
    public List<Chemin> getChemins(List<Set<Noeud>> plages) {
    	List<Chemin> chemins = new ArrayList<Chemin>();
    	
    	Set<Noeud> plagePrecedente = null;
    	//Pour chaque plage
    	for(Set<Noeud> plage : plages) {
    		//On calcule le meilleur chemin entre tous les points de cette plage
    		for(Noeud orig : plage) {
    			for(Noeud dest : plage) {
            		if(!orig.equals(dest)) {
            			chemins.add(calculerPlusCourtChemin(orig, dest));
            		}
            	}
    		}
    		//On calcule le meilleur chemin de tous les points de la plage précedente a la plage actuelle
    		if(plagePrecedente != null) {
    			for(Noeud orig : plagePrecedente) {
    				for(Noeud dest : plage) {
    					chemins.add(calculerPlusCourtChemin(orig, dest));
    				}
    			}
    		}
    		plagePrecedente = plage;
    	}
    	System.out.println("MERDE!");
    	for(Chemin c : chemins) {
    		System.out.println(c);
    	}
    	CheminGraph graph = new CheminGraph(chemins);
    	
    	System.out.println(graph);
    	TSP tsp = new TSP(graph);
    	
    	int bound = graph.getNbVertices()*graph.getMaxArcCost() + 1;
		int[] next = null;
		
		for (int t = 10; (tsp.getSolutionState() != SolutionState.OPTIMAL_SOLUTION_FOUND) && 
						 (tsp.getSolutionState() != SolutionState.INCONSISTENT); t*=2){
			System.out.println("--> Search of a tour strictly lower than "+bound+" within a time limit of "+t+"s.");
			tsp.solve(t*1000,bound-1);
			if (tsp.getSolutionState() == SolutionState.OPTIMAL_SOLUTION_FOUND) {
				next = tsp.getNext();
				break;
			} else{ // etat = SOLUTION_FOUND
				next = tsp.getNext();
			}
		}	
		List<Chemin> cheminsItineraire = new ArrayList<Chemin>();
		for(int i : next) {
			cheminsItineraire.add(chemins.get(i));
		}
		
        
        return cheminsItineraire;
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
	
	public List<Vertex> getVertexes() {
		System.out.println(this.listVertexs.size());
		return this.listVertexs; 
	}
	
	public void setVertexs(List<Vertex> argVertexs) {
		this.listVertexs = argVertexs;
	}
}
