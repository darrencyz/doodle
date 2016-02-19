package a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menubar extends JMenuBar {
	Model model;
	
	public Menubar(Model model) {
		this.model = model;
		JMenu mnuFile = new JMenu("File");
		JMenuItem mnuItemLoad = new JMenuItem("Load");
		JMenuItem mnuItemCreate = new JMenuItem("Create");
		JMenuItem mnuItemSave = new JMenuItem("Save");
		JMenuItem mnuItemQuit = new JMenuItem("Quit");
		
		JMenu mnuView = new JMenu("View");
		JMenuItem mnuItemFullSize = new JMenuItem("Full Size");
		JMenuItem mnuItemFit = new JMenuItem("Fit");
		
		mnuItemCreate.addActionListener(new CreateListener());
		mnuItemLoad.addActionListener(new LoadListener());
		mnuItemSave.addActionListener(new SaveListener());
		mnuItemQuit.addActionListener(new QuitListener());
		
		mnuFile.add(mnuItemCreate);
		mnuFile.add(mnuItemLoad);
		mnuFile.add(mnuItemSave);
		mnuFile.add(mnuItemQuit);
		
		mnuView.add(mnuItemFullSize);
		mnuView.add(mnuItemFit);
		
		add(mnuFile);
		add(mnuView);
	}
	
	class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model.getPath() == null) {
				JFileChooser chooser = new JFileChooser();
				int rVal = chooser.showSaveDialog(getParent());
				if (rVal == JFileChooser.APPROVE_OPTION) {
					model.setPath(chooser.getCurrentDirectory().toString() + "/" + chooser.getSelectedFile().getName());
					save();
				}
			}
			System.exit(0);
		}
	}
	
	class CreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Doodle.loadGUI(new Model());
		}
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (model.getPath() == null) {
				JFileChooser chooser = new JFileChooser();
				int rVal = chooser.showSaveDialog(getParent());
				if (rVal == JFileChooser.APPROVE_OPTION) {
					model.setPath(chooser.getCurrentDirectory().toString() + "/" + chooser.getSelectedFile().getName());
					save();
				}
			}
			else {
				save();
			}
		}
	}
	
	class LoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Model m = null;
			String path = null;
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser, .txt", "ser");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(getParent());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		           path = chooser.getSelectedFile().getAbsolutePath();
		    }
			try {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				m = (Model) in.readObject();
				in.close();
				fileIn.close();
				Doodle.loadGUI(m);
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return;
			}
		}
	}
	
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(model.getPath());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(model);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
