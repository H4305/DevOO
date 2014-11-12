/**
 * 
 */
package util;


import java.util.List;

import model.data.Chemin;
import model.data.Noeud;

public class CheminGraph implements Graph {

	private List<Chemin> mChemins;
	private float maxArcCost = Float.NEGATIVE_INFINITY;
	private float minArcCost = Float.POSITIVE_INFINITY;
	
	public CheminGraph(List<Chemin> chemins) {
		mChemins = chemins;
		for(Chemin chemin: mChemins) {
			if(chemin.getTempsParcours() > maxArcCost) {
				maxArcCost = chemin.getTempsParcours();
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see util.Graph#getMaxArcCost()
	 */
	@Override
	public int getMaxArcCost() {
		return (int) maxArcCost;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getMinArcCost()
	 */
	@Override
	public int getMinArcCost() {
		return (int) minArcCost;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getNbVertices()
	 */
	@Override
	public int getNbVertices() {
		return mChemins.size();
	}

	/* (non-Javadoc)
	 * @see util.Graph#getCost()
	 */
	@Override
	public int[][] getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getSucc(int)
	 */
	@Override
	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getNbSucc(int)
	 */
	@Override
	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return 0;
	}

}
