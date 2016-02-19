package a2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class PaletteView extends JPanel implements IView {
	Model model;
	JPanel colorPalette = new JPanel();
	JPanel strokePalette = new JPanel();
	JButton picker = new JButton();
	Color[] colors = new Color[] {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.CYAN};
	
	public PaletteView(Model model) {
		this.model = model;
		
		colorPalette.setLayout(new GridLayout(4, 2, 3, 3));
		colorPalette.setPreferredSize(new Dimension(75, 150));
		for (int i = 0; i < 8; i++) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(15, 15));
			button.setOpaque(true);
			button.setBorderPainted(false);
			button.setBackground(colors[i]);
			button.addActionListener(new ColorListener(button));
			colorPalette.add(button);
		}
		
		picker.setPreferredSize(new Dimension(75, 75));
		picker.setOpaque(true);
		picker.setBorderPainted(false);
		picker.setBackground(Color.WHITE);
	    picker.addActionListener(new ChooserListener());
	    
	    strokePalette.setPreferredSize(new Dimension(75, 100));
	    for (int i = 1; i <= 5; i++) {
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(75, 5 + i*3));
			button.setOpaque(true);
			button.setBorderPainted(false);
			button.setBackground(Color.BLACK);
			button.addActionListener(new StrokeListener(i));
			strokePalette.add(button);
		}
	    
	    this.add(colorPalette);
		this.add(picker);
		this.add(strokePalette);
		this.setBorder(BorderFactory.createTitledBorder("Palette"));
		this.setPreferredSize(new Dimension(100, 400));
	}
	
	public class ColorListener implements ActionListener {
		JButton button;
		
		ColorListener(JButton button) {
			this.button = button;
		}
		public void actionPerformed(ActionEvent e) {
			model.setColor(button.getBackground());
		}
	}
	
	public class StrokeListener implements ActionListener {
		float thickness;
		
		public StrokeListener(float thickness) {
			this.thickness = thickness;
		}
		public void actionPerformed(ActionEvent e) {
			model.setStroke(thickness*2);
		}
	}
	
	public class ChooserListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
        Color initialBackground = picker.getBackground();
        Color background = JColorChooser.showDialog(null, "Change Button Background",
            initialBackground);
        if (background != null) {
          picker.setBackground(background);
          model.setPicker(picker.getBackground());
          model.setColor(picker.getBackground());
        }
      }
    };

	// IView interface 
	public void updateView() {
		picker.setBackground(model.getPicker());
	}
}
