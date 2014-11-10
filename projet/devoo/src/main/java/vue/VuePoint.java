package vue;

import java.awt.Color;
import java.awt.Graphics;

import model.data.Point;
import vue.util.AppColors;
import vue.util.CoordinateConverter;
import vue.util.Vue;

public class VuePoint extends Vue{
	
	
	private static final int POINT_RADIUS = 10;
	
	Point pointModel;
	java.awt.Point pointVue;

	Color color = AppColors.normalPoint;
	public VuePoint(Point pointModel) {
		super();
		this.pointModel = pointModel;
	}
	
	
	@Override
	public void draw(Graphics g, CoordinateConverter converter) {
		this.pointVue = converter.convert(pointModel.x, pointModel.y);
			g.setColor(color);
			g.fillOval(pointVue.x - POINT_RADIUS / 2, pointVue.y - POINT_RADIUS / 2,
					POINT_RADIUS, POINT_RADIUS);
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == this) return true;
		if(!(arg0 instanceof VuePoint)) return false;
		VuePoint vuePoint = (VuePoint) arg0;
		return pointModel.equals(vuePoint.pointModel);
	}

	/**
	 * @return theCoordinate where the point is displayed.
	 */
	public java.awt.Point getGemometricPoint() { return pointVue; }
	
	/**
	 * @return the data point represented by this view
	 */
	public Point getPoint() { return pointModel;}
	
	@Override
	public int hashCode() {
		return pointModel.hashCode() * 89;
	}
	
	public boolean isClicked(java.awt.Point pointClicked) {
		return pointVue.distance(pointClicked) < POINT_RADIUS;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
