package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.data.Chemin;
import model.data.Troncon;
import util.TwoKeyMap;
import vue.util.AppColors;
import vue.util.ComplexDrawing;
import vue.util.CoordinateConverter;
import vue.util.Vue;


public class VueChemin extends Vue {
	
	private static final int REDUCTION_LENGTH = 5;
	private static final int LINE_WIDTH = 2;
	private static final int PARALLELE_LINE_DISTANCE = 2;
	private static final Logger LOGGER = Logger.getLogger(VueChemin.class.getName());

	class TronconCounter {
		Troncon troncon;
		int number = 0;
		int displayed = 0;
	}
	
	Color[] colorTroncon = AppColors.itineraire;
	
	TwoKeyMap<model.data.Noeud, model.data.Noeud, TronconCounter> displayedTroncon = 
			new TwoKeyMap<model.data.Noeud, model.data.Noeud, TronconCounter>();
	Chemin mChemin;

	public VueChemin(Chemin chemin) {
		mChemin = chemin;

		for (Troncon troncon : chemin.getTroncons()) {
			if (displayedTroncon.containsKeys(troncon.getDepart(),
					troncon.getArrivee())) {
				displayedTroncon.get(troncon.getDepart(), troncon.getArrivee()).number++;
			} else if (displayedTroncon.containsKeys(troncon.getArrivee(),
					troncon.getDepart())) {
				displayedTroncon.get(troncon.getArrivee(), troncon.getDepart()).number++;
			} else {
				TronconCounter tronconCounter = new TronconCounter();
				tronconCounter.number++;
				tronconCounter.troncon = troncon;
				displayedTroncon.put(troncon.getDepart(), troncon.getArrivee(),
						tronconCounter);
			}
		}

	}

	@Override
	public void draw(Graphics g, CoordinateConverter converter) {
		for (Troncon troncon : mChemin.getTroncons()) {
			TronconCounter counter = null;
			if (displayedTroncon.containsKeys(troncon.getDepart(),
					troncon.getArrivee())) {
				counter = displayedTroncon.get(troncon.getDepart(),
						troncon.getArrivee());
			} else if (displayedTroncon.containsKeys(troncon.getArrivee(),
					troncon.getDepart())) {
				counter = displayedTroncon.get(troncon.getArrivee(),
						troncon.getDepart());
			}
			if (counter != null) {
				
				if(counter.displayed >= counter.number) {
					counter.displayed = 0;
				}
				
				int offset = Math.round((counter.number / 2f + 0.5f)
						* LINE_WIDTH + counter.displayed
						* PARALLELE_LINE_DISTANCE);
				Point point1 = converter.convert(
						troncon.getDepart().getX(), troncon.getDepart().getY());
				Point point2 = converter.convert(
						troncon.getArrivee().getX(), troncon.getArrivee().getY());
				
				
				point1 = movePointTowards(point1, point2, REDUCTION_LENGTH * counter.displayed);
				point2 = movePointTowards(point2, point1, REDUCTION_LENGTH * counter.displayed);
		
				// Calcul d�calage
				double L = Math.sqrt((point1.x - point2.x)
						* (point1.x - point2.x) + (point1.y - point2.y)
						* (point1.y - point2.y));
				
				int nx1 = new Double(point1.x + offset * (point2.y - point1.y)
						/ L).intValue();
				int nx2 = new Double(point2.x + offset * (point2.y - point1.y)
						/ L).intValue();
				int ny1 = new Double(point1.y + offset * (point1.x - point2.x)
						/ L).intValue();
				int ny2 = new Double(point2.y + offset * (point1.x - point2.x)
						/ L).intValue();
				
				
		
				g.setColor(colorTroncon[counter.displayed % colorTroncon.length]);
				ComplexDrawing.drawArrow(g, nx1, ny1, nx2, ny2,
						LINE_WIDTH, g.getColor());
				counter.displayed++;
				
			} else {
				LOGGER.log(Level.WARNING,
						"Troncon de l'tineraire introuvable dans le plan");
			}
		}
	}
	
	private Point movePointTowards(Point a, Point b, float distance)
	{
	    Point vector = new Point(b.x - a.x, b.y - a.y);
	    float length = (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
	    
	    float unitX = vector.x / length;
	    float unitY = vector.y / length;
	    
	    int nx = Math.round(a.x + unitX * distance);
	    int ny = Math.round(a.y + unitY * distance);
	    return new Point(nx, ny);
	}

}
