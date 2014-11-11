package util;

public class PairKey<A, B> {
	
	  public final A troncons;
	  public final B vertexs;

	  public PairKey(A troncons, B vertexs) { 
		  this.troncons = troncons; 
		  this.vertexs = vertexs; 
	  }

	  public static <A, B> PairKey<A, B> make(A troncons, B vertexs) { 
		  return new PairKey<A, B>(troncons, vertexs); 
	  }

	  public boolean equals(Object o) {
	    if (o == null || o.getClass() != this.getClass()) {
	    	return false; 
	    }
	    
	    PairKey that = (PairKey) o;
	    
	    return (troncons == null ? that.troncons == null : troncons.equals(that.troncons))
	        && (vertexs == null ? that.vertexs == null : vertexs.equals(that.vertexs));
	  }
}
