package util;

public class PairIdLivrPrec<A, B> {
	
	  public final A id;
	  public final B noeud;

	  /**
	   * Constructor
	   * @param id
	   * @param noeud
	   */
	  public PairIdLivrPrec(A id, B noeud) { 
		  this.id = id; 
		  this.noeud = noeud; 
	  }

	  /**
	   * 
	   * @param id
	   * @param noeud
	   * @return a pair of integer(id ) and Noeud
	   */
	  public static <A, B> PairIdLivrPrec<A, B> make(A id, B noeud) {
		  
		  return new PairIdLivrPrec<A, B>(id, noeud); 
	  }
	  
	  /** 
	   * @param object
	   * @return true if the current object(this class) is equal to the object given in parameter
	   */
	  public boolean equals(Object o) {
	    if (o == null || o.getClass() != this.getClass()) {
	    	return false; 
	    }
	    
	    PairIdLivrPrec that = (PairIdLivrPrec) o;
	    
	    return (noeud == null ? that.noeud == null : noeud.equals(that.noeud))
	        && (id == null ? that.id == null : id.equals(that.id));
	  }
}