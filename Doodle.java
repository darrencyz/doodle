package a2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Doodle {
	static JFrame f = new JFrame("Doodle");
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	public JFrame getFrame() {
		return f;
	}

	public static void createAndShowGUI() {
		// main mvc
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(model, controller);
		model.addView(view);

		// slider
		SliderView sliderView = new SliderView(model);
		model.addView(sliderView);

		// palette
		PaletteView paletteView = new PaletteView(model);
		model.addView(paletteView);

		// add views to layout
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(sliderView, BorderLayout.SOUTH);
		p.add(view, BorderLayout.CENTER);
		p.add(paletteView, BorderLayout.WEST);
		f.getContentPane().add(p);

		// Menubar
		Menubar mb = new Menubar(model);
		f.setJMenuBar(mb);

		// settings
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(1000, 750));
		f.setMinimumSize(new Dimension(350, 520));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void loadGUI(Model model) {
		f.getContentPane().removeAll();
		Controller controller = new Controller(model);
		View view = new View(model, controller);
		model.addView(view);

		// slider
		SliderView sliderView = new SliderView(model);
		model.addView(sliderView);

		// palette
		PaletteView paletteView = new PaletteView(model);
		model.addView(paletteView);

		// add views to layout
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(sliderView, BorderLayout.SOUTH);
		p.add(view, BorderLayout.CENTER);
		p.add(paletteView, BorderLayout.WEST);
		f.getContentPane().add(p);
		
		// Menubar
		Menubar mb = new Menubar(model);
		f.setJMenuBar(mb);
		
		f.getContentPane().revalidate();
	}
}
