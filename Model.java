package a2;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

interface IView {
	public void updateView();
}

@SuppressWarnings("serial")
public class Model implements Serializable {
	private ArrayList<Polyline> strokes = new ArrayList<Polyline>();
	private ArrayList<IView> views = new ArrayList<IView>();
	private int sliderPos = 0;
	private Color selectedColor = Color.BLACK;
	private Color pickerColor = Color.WHITE;
	private float selectedStroke = 2.0f;
	private String filepath;
	
	// set the view observer
	public void addView(IView view) {
		views.add(view);
		view.updateView();
	}
	
	// notify the IView observer
	private void notifyObservers() {
		for (IView view : this.views) {
			view.updateView();
		}
	}
	
	public String getPath() {
		return filepath;
	}
	
	public void setPath(String path) {
		filepath = path;
	}
	
	public Color getColor() {
		return selectedColor;
	}
	
	public void setColor(Color color) {
		selectedColor = color;
		notifyObservers();
	}
	
	public Color getPicker() {
		return pickerColor;
	}
	
	public void setPicker(Color color) {
		pickerColor = color;
		notifyObservers();
	}
	
	public float getSelectedStroke() {
		return selectedStroke;
	}
	
	public void setStroke(float thickness) {
		selectedStroke = thickness;
		notifyObservers();
	}
	
	public int getSliderPos() {
		return sliderPos;
	}
	
	public void setSliderPos(int value) {
		sliderPos = value;
		notifyObservers();
	}
	
	public void incrementSlider() {
		sliderPos++;
		notifyObservers();
	}
	
	public void decrementSlider() {
		sliderPos--;
		notifyObservers();
	}
	
	public ArrayList<Polyline> getStrokes() {
		return strokes;
	}
	
	public Polyline getCurrentStroke() {
		return strokes.get(strokes.size() - 1);
	}
	
	public void addPoint(double x, double y) {
    	getCurrentStroke().addPoint(x, y);
    	notifyObservers();
    }
	
	public void deleteLastStroke() {
		strokes.remove(strokes.size() - 1);
	}
	
	public void addStroke() {
		if (strokes.size() != sliderPos) {
			ArrayList<Polyline> updateStrokes = new ArrayList<Polyline>();
			for (int i = 0; i < sliderPos; i++) {
				updateStrokes.add(strokes.get(i));
			}
			strokes = updateStrokes;
		}
		
		Polyline stroke = new Polyline();
		stroke.setColour(selectedColor);
		stroke.setStrokeThickness(selectedStroke);
		strokes.add(stroke);
		notifyObservers();
	}
}
