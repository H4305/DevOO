package vue.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class ComplexDrawing {
	private static final int ARR_SIZE = 5;
	
	public static void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
		drawArrow(g1, x1, y1, x2, y2, -1, g1.getColor());
	}

    public static void drawArrow(Graphics g1, int x1, int y1, int x2, int y2, int strokeWidth, Color color) {
        Graphics2D g = (Graphics2D) g1.create();
        
        if(strokeWidth > 0) {
        	g.setStroke(new BasicStroke(strokeWidth));
        }
        
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

}
