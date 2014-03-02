package listener;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import resources.Strings;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTrayHelpItem implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) { //Help field selected on tray icon menu
		Toolkit toolkit = Toolkit.getDefaultToolkit(); //Uses to import icon image as resources instead of external image (useful for jar packaging)
		Image icon = toolkit.getImage(this.getClass().getResource("/gui/icon64.png"));
		JOptionPane.showMessageDialog(null, Strings.credits, Strings.trayHelp, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(icon));
	}
}
