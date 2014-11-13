/**
 * 
 */
package util;


import java.util.ArrayList;
import java.util.List;

import model.data.Chemin;

public class CheminGraph implements Graph {

	private List<Chemin> mChemins;
	private float maxArcCost = Float.NEGATIVE_INFINITY;
	private float minArcCost = Float.POSITIVE_INFINITY;
	
	/**
	 * Constructor
	 * @param chemins Liste des chemins 
	 */
	public CheminGraph(List<Chemin> chemins) {
		mChemins = chemins;
		for(Chemin chemin: mChemins) {
			if(chemin.getTempsParcours() > maxArcCost) {
				maxArcCost = chemin.getTempsParcours();
			}
			if(chemin.getTempsParcours() < minArcCost) {
				minArcCost = chemin.getTempsParcours();
			}
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
		return mChemins.size();
	}

	/**
	 * @return the <code>cost</code> matrix of <code>this</code>: for all vertices <code>i</code> and <code>j</code>,
	 * if <code>(i,j)</code> is an arc of <code>this</code>, then <code>cost[i][j]</code> = cost of <code>(i,j)</code>, 
	 * otherwise <code>cost[i][j] = this.getMaxArcCost()+1</code>
	 */
	@Override
	public int[][] getCost() {
		int[][] costs = new int[mChemins.size()][mChemins.size()];
		for(int i = 0; i<mChemins.size();i++) {
			for(int j = 0; j < mChemins.size(); j++) {
				costs[i][j] = this.getMaxArcCost()+1;
			}
		}
		for(Chemin chemin : mChemins) {
			costs[chemin.getDepart().getId()][chemin.getArrivee().getId()] = Math.round(chemin.getTempsParcours());
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
		ArrayList<Integer> successors = new ArrayList<Integer>();
		for(Chemin chemin : mChemins) {
			if(chemin.getDepart().getId() == i) {
				successors.add(chemin.getArrivee().getId());
			}
		}
		int[] r = new int[successors.size()];
		int cpt = 0;
		for(Object o : successors.toArray()) {
			r[cpt] = (int) o;
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
			if(chemin.getDepart().getId() == i) {
				successors++;
			}
		}
		return successors;
	}

}
