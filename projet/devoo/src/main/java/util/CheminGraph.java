/**
 * 
 */
package util;


import java.util.ArrayList;
import model.data.*;
import java.util.*;

import model.data.Chemin;

public class CheminGraph implements Graph {

	private List<Chemin> mChemins;
	private float maxArcCost = Float.NEGATIVE_INFINITY;
	private float minArcCost = Float.POSITIVE_INFINITY;
	Set<Noeud> noeudSet = new HashSet<Noeud>();
	
	HashMap<Integer, Noeud> noeudsMap = new HashMap<Integer, Noeud>();
	HashMap<Noeud, Integer> noeudsKeys = new HashMap<Noeud, Integer>();
	
	/**
	 * Constructor
	 * @param chemins Liste des chemins 
	 */
	public CheminGraph(List<Chemin> chemins) { 
		mChemins = chemins;
		for(Chemin chemin: mChemins) {
			noeudSet.add(chemin.getArrivee());
			noeudSet.add(chemin.getDepart());
			if(chemin.getTempsParcours() > maxArcCost) {
				maxArcCost = chemin.getTempsParcours();
			}
			if(chemin.getTempsParcours() < minArcCost) {
				minArcCost = chemin.getTempsParcours();
			}
		}
		Integer key = 0;
		for(Noeud n : noeudSet) {
			noeudsMap.put(key, n);
			noeudsKeys.put(n, key);
			key++;
		}
	}
	
	/**
	 * @return the maximal cost of an arc of <code>this</code>
	 */
	@Override
	public int getMaxArcCost() {
		return Math.round(maxArcCost);
	}

	/**
	 * @return the minimal cost of an arc of <code>this</code>
	 */
	@Override
	public int getMinArcCost() {
		return Math.round(minArcCost);
	}

	/**
	 * @return the number of vertices of <code>this</code>
	 */
	@Override
	public int getNbVertices() {
		return noeudSet.size();
	}

	/**
	 * @return the <code>cost</code> matrix of <code>this</code>: for all vertices <code>i</code> and <code>j</code>,
	 * if <code>(i,j)</code> is an arc of <code>this</code>, then <code>cost[i][j]</code> = cost of <code>(i,j)</code>, 
	 * otherwise <code>cost[i][j] = this.getMaxArcCost()+1</code>
	 */
	@Override
	public int[][] getCost() {
		int[][] costs = new int[noeudSet.size()][noeudSet.size()];
		for(int i = 0; i < noeudSet.size();i++) {
			for(int j = 0; j < noeudSet.size(); j++) {
				costs[i][j] = this.getMaxArcCost()+1;
			}
		}
		for(Chemin chemin : mChemins) {
			costs[noeudsKeys.get(chemin.getDepart())][noeudsKeys.get(chemin.getArrivee())] = Math.round(chemin.getTempsParcours());
		}
		return costs;
	}

	/**
	 * @param i a vertex such that <code>0 <= i < this.getNbVertices()</code>
	 * @return an array containing all successor vertices of <code>i</code> in <code>this</code>
	 * @throws ArrayIndexOutOfBoundsException If <code>i<0</code> or <code>i>=this.getNbVertices()</code>
	 */
	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		if(i < 0 ||
		   i >= this.getNbVertices()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		List<Integer> successors = new ArrayList<Integer>();
		
		for(Chemin chemin : mChemins) {
			if(noeudsKeys.get(chemin.getDepart()) == i) {
				successors.add(noeudsKeys.get(chemin.getArrivee()));
			}
		}
		int[] r = new int[successors.size()];
		//System.out.println("Taille " + successors.size());
		int cpt = 0;
		for(Object o : successors.toArray()) {
			r[cpt] = (int) o;
			cpt++;
		}
		return r;
	}

	/**
	 * @param i a vertex such that <code>0 <= i < this.getNbVertices()</code>
	 * @return the number of successor vertices of <code>i</code> in <code>this</code>
	 * @throws ArrayIndexOutOfBoundsException If <code>i<0</code> or <code>i>=this.getNbVertices()</code>
	 */
	@Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if(i < 0 ||
		   i >= this.getNbVertices()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int successors = 0;
		for(Chemin chemin : mChemins) {
			if(noeudsKeys.get(chemin.getDepart()) == i) {
				successors++;
			}
		}
		return successors;
	}
	
	public Noeud getNoeudFromIndex(int id) {
		return noeudsMap.get(id);
	}

	public String toString() {
		String s = "Graph:\n";
		for(Chemin c : mChemins) {
			s += c.toString() + "\n";
		}
		return s;
	}
}
