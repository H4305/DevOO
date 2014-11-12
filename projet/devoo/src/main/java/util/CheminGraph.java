/**
 * 
 */
package util;


import model.data.Chemin;
import model.data.Point;

/**
 * @author mozilla
 *
 */
public class CheminGraph implements Graph {
	
	private TwoKeyMap<Point, Point, Chemin> chemins;

	private int maxArcCost = 0;
	private int minArcCost = 0;
	
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
		return chemins.size();
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
