package vue;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import vue.util.AppColors;
import vue.util.CoordinateConverter;
import model.data.Point;

public class VuePoint {
	
	private static final int POINT_RADIUS = 10;
	
	Point pointModel;
	java.awt.Point pointVue;
	
	public VuePoint(Point pointModel) {
		super();
		this.pointModel = pointModel;
	}
	
	public void draw(Graphics g, CoordinateConverter converter) {
		this.pointVue = converter.convert(pointModel.x, pointModel.y);
			g.setColor(AppColors.normalPoint);
			g.fillOval(pointVue.x - POINT_RADIUS / 2, pointVue.y - POINT_RADIUS / 2,
					POINT_RADIUS, POINT_RADIUS);
	}

	public boolean isClicked(java.awt.Point pointClicked) {
		return pointVue.distance(pointClicked) < POINT_RADIUS;
	}
	
	/**
	 * @return the data point represented by this view
	 */
	public Point getPoint() { return pointModel;}
	
	/**
	 * @return theCoordinate where the point is displayed.
	 */
	public java.awt.Point getGemometricPoint() { return pointVue; }
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == this) return true;
		if(!(arg0 instanceof VuePoint)) return false;
		VuePoint vuePoint = (VuePoint) arg0;
		return pointModel.equals(vuePoint.pointModel);
	}
	
	@Override
	public int hashCode() {
		return pointModel.hashCode() * 89;
	}


	
	

}
