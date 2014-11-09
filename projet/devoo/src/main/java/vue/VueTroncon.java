package vue;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import vue.util.AppColors;
import vue.util.CoordinateConverter;
import model.data.Troncon;

public class VueTroncon {

	private static final float LINE_WIDTH = 5;

	Troncon troncon;
	private Point pointA;
	private Point pointB;

	/**
	 * Construct a new representation of troncon at the given coordinates
	 * 
	 * @param pointA
	 *            The first extremity of the troncon
	 * @param pointB
	 *            The second extremity of the troncon
	 * @param troncon
	 *            The troncon to represent.
	 */
	public VueTroncon(Troncon troncon) {
		super();
		this.troncon = troncon;
	}

	public Troncon getTroncon() {
		return troncon;
	}

	public void draw(Graphics g, CoordinateConverter converter) {
		Point pointA = converter.convert(troncon.getDepart().x,
				troncon.getDepart().y);
		Point pointB = converter.convert(troncon.getArrivee().x,
				troncon.getArrivee().y);
		drawLine(pointA, pointB, g);
	}

	private void drawLine(java.awt.Point a, java.awt.Point b, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(LINE_WIDTH));
		g2.setColor(AppColors.troncon);
		g2.drawLine(a.x, a.y, b.x, b.y);
	}
}
