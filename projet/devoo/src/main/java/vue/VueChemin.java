package vue;

import java.awt.Color;
import java.awt.Graphics;
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
	
	private static final int LINE_WIDTH = 2;
	private static final int PARALLELE_LINE_DISTANCE = 5;
	private static final Logger LOGGER = Logger.getLogger(VueChemin.class.getName());

	class TronconCounter {
		Troncon troncon;
		int number = 0;
		int displayed = 0;
	}
	
	Color[] colorTroncon = {
			AppColors.itineraire1,
			AppColors.itineraire4,
			AppColors.itineraire3,
			AppColors.itineraire2
	};

	TwoKeyMap<model.data.Point, model.data.Point, TronconCounter> displayedTroncon = 
			new TwoKeyMap<model.data.Point, model.data.Point, TronconCounter>();
	Chemin mChemin;

	public VueChemin(Chemin chemin) {
		mChemin = chemin;

		for (Troncon troncon : chemin.getTroncons()) {
			if (displayedTroncon.containsKeys(troncon.getDepart(),
					troncon.getDepart())) {
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
		drawChemin(g, converter);
	}


	private void drawChemin(Graphics g, CoordinateConverter converter) {
		// Affiche l'itineraire

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
				int offset = Math.round((counter.number / 2f + 0.5f)
						* LINE_WIDTH + counter.displayed
						* PARALLELE_LINE_DISTANCE);
				java.awt.Point point1 = converter.convert(
						troncon.getDepart().x, troncon.getDepart().y);
				java.awt.Point point2 = converter.convert(
						troncon.getArrivee().x, troncon.getArrivee().y);

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
				if(counter.displayed == counter.number) {
					counter.displayed = 0;
				}
			} else {
				LOGGER.log(Level.WARNING,
						"Troncon de l'tineraire introuvable dans le plan");
			}
		}
	}

}
