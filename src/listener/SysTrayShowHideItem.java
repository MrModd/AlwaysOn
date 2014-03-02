package listener;

import gui.MainGUI;
import gui.SysTray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resources.Strings;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTrayShowHideItem implements ActionListener {
	private MainGUI gui;
	private SysTray tray;
	
	public SysTrayShowHideItem(MainGUI g, SysTray t) {
		gui = g;
		tray = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) { //Show or Hide field selected on tray icon menu
		gui.setVisible(!gui.isVisible());
		if (tray.showHide.getLabel().equals(Strings.trayHide)) tray.showHide.setLabel(Strings.trayShow);
		else tray.showHide.setLabel(Strings.trayHide);
	}
}
