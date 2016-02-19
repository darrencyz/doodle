package a2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter {
	Model model;

	Controller(Model model) {
		this.model = model;
	}
	
	@Override
    public void mousePressed(MouseEvent arg0) {
        model.addStroke();
        model.incrementSlider();
    }
	
	@Override
    public void mouseReleased(MouseEvent arg0) {
		if (model.getCurrentStroke().getPoints() == null) {
	        model.deleteLastStroke();
	        model.decrementSlider();
		}
    }
	
	@Override
    public void mouseDragged(MouseEvent arg0) {
        model.addPoint(arg0.getX(), arg0.getY());  
    }
}
