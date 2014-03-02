package listener;

import gui.MainGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resources.Strings;
import structures.Definitions;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTrayExitItem implements ActionListener {
	private Definitions defs;
	private MainGUI gui;
	
	public SysTrayExitItem(Definitions d, MainGUI g) {
		defs = d;
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //Exit field selected on tray icon menu
		if (gui.thread.isAlive()) gui.thread.interrupt();
		if (defs.verbose) System.out.println(Strings.verbExit);
		System.exit(0);
	}

}
