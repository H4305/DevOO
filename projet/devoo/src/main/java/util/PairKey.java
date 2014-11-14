package util;

public class PairKey<A, B> {
	
	  public final A troncons;
	  public final B vertexs;
	  
	  /**
	   * Constructor
	   * @param troncons
	   * @param vertexs
	   */
	  public PairKey(A troncons, B vertexs) { 
		  this.troncons = troncons; 
		  this.vertexs = vertexs; 
	  }
	  
	  /**
	   * 
	   * @param list of troncons
	   * @param list of vertexs
	   * @return the pair -> list of troncons, list of vertexs
	   */
	  public static <A, B> PairKey<A, B> make(A troncons, B vertexs) { 
		  return new PairKey<A, B>(troncons, vertexs); 
	  }

	  /**
	   * @param object
	   * @return true if the current object(this class) is equal to the object given in parameter
	   */
	  public boolean equals(Object o) {
	    if (o == null || o.getClass() != this.getClass()) {
	    	return false; 
	    }
	    
	    PairKey that = (PairKey) o;
	    
	    return (troncons == null ? that.troncons == null : troncons.equals(that.troncons))
	        && (vertexs == null ? that.vertexs == null : vertexs.equals(that.vertexs));
	  }
}
