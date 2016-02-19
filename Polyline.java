package a2;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Polyline implements Serializable {
    // line model
    ArrayList<Point2D> points;
    Color color;
	float strokeThickness;
	
	public ArrayList<Point2D> getPoints() {
		return points;
	}
	
    public Color getColour() {
		return color;
	}
	public void setColour(Color colour) {
		this.color = colour;
	}
    public float getStrokeThickness() {
		return strokeThickness;
	}
	public void setStrokeThickness(float strokeThickness) {
		this.strokeThickness = strokeThickness;
	}
    
    // add a point to end of shape
    public void addPoint(double x, double y) {
    	if (points == null)
    		points = new ArrayList<Point2D>();
    	points.add(new Point2D.Double(x,y));
    }
}