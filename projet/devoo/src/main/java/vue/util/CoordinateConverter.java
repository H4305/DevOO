package vue.util;

import java.awt.Point;

/**
 * This interface should be passed as an argument of the views to
 * convert physical unit from the models to pixel on the view
 * @author vcaen
 *
 */
public interface CoordinateConverter {
	
	/**
	 * A simple converter that juste return the given coordinate into a {@link Point}.
	 */
	public static CoordinateConverter DEFAULT_CONVERTER = new CoordinateConverter() {
		
		@Override
		public Point convert(int x, int y) {
			return new Point(x,y);
		}
	};

	public Point convert(int x, int y);
}
