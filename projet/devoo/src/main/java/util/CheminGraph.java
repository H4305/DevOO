/**
 * 
 */
package util;


import model.data.Chemin;
import model.data.Noeud;

public class CheminGraph implements Graph {

	private TwoKeyMap<Noeud, Noeud, Chemin> chemins;

	private TreeMap<Pair<Point,Point>, Chemin> chemin;
	private int maxArcCost = 0;
	private int minArcCost = 0;
	
	public CheminGraph(TwoKeyMap<Point, Point, Chemin> chemins) {
		mChemins = chemins;
	}
	
	/* (non-Javadoc)
	 * @see util.Graph#getMaxArcCost()
	 */
	@Override
	public int getMaxArcCost() {
		return maxArcCost;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getMinArcCost()
	 */
	@Override
	public int getMinArcCost() {
		return minArcCost;
	}

	/* (non-Javadoc)
	 * @see util.Graph#getNbVertices()
	 */
	@Override
	public int getNbVertices() {
		return mChemins.getSize();
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
