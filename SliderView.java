package a2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderView extends JPanel implements IView {
	Model model;
	JSlider slider = new JSlider();
	JButton play = new JButton("Play");
	JButton start = new JButton("Start");
	JButton end = new JButton("End");
	JPanel rightSubPanel = new JPanel();
	JPanel leftSubPanel = new JPanel();
	
	public SliderView(Model model) {
		this.setBorder(BorderFactory.createTitledBorder("Timeline"));
		rightSubPanel.add(start);
		rightSubPanel.add(end);
		leftSubPanel.add(play);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		play.setPreferredSize(new Dimension(75, 75));
		start.setPreferredSize(new Dimension(75, 75));
		end.setPreferredSize(new Dimension(75, 75));
		this.model = model;
		this.setLayout(new BorderLayout());
		this.add(leftSubPanel, BorderLayout.LINE_START);
		this.add(slider, BorderLayout.CENTER);
		this.add(rightSubPanel, BorderLayout.LINE_END);
		
		// What to do when the slider is moved
		slider.addChangeListener(new SliderListener());
		start.addActionListener(new StartListener());
		end.addActionListener(new EndListener());
	}
	
	public class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider s = (JSlider)e.getSource();
			model.setSliderPos(s.getValue());
		}
	}

	public class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.setSliderPos(0);
		}
	}
	
	public class EndListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.setSliderPos(model.getStrokes().size());
		}
	}
	
	// IView interface 
	public void updateView() {
		slider.setMaximum(model.getStrokes().size());
		slider.setValue(model.getSliderPos());
	}
}
