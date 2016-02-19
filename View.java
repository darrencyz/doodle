package a2;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel implements IView {
	// the model that this view is showing
	private Model model;
	
	View(Model model, Controller controller) {
		this.setBorder(BorderFactory.createTitledBorder("Canvas"));
		this.setPreferredSize(new Dimension(400, 400));
		this.model = model;
		addMouseListener(controller);
		addMouseMotionListener(controller);
	} 
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        ArrayList<Polyline> strokes = model.getStrokes();
        for (int j = 0; j < model.getSliderPos(); j++) {
	        Polyline stroke = strokes.get(j);
        	ArrayList<Point2D> points = stroke.getPoints();
        	if (points != null) {
                int[] x_points = new int[points.size()];
                int[] y_points = new int[points.size()];
                
                for (int i=0; i < points.size(); i++) {
                    x_points[i] = (int)points.get(i).getX();
                    y_points[i] = (int)points.get(i).getY();
                }

	            if (x_points != null) {
	            	g2.setColor(stroke.getColour());
	            	g2.setStroke(new BasicStroke(stroke.getStrokeThickness())); 
	                g2.drawPolyline(x_points, y_points, points.size());
	            }
            }
        }
    }

	// IView interface 
	public void updateView() {
		repaint();
	}
}
