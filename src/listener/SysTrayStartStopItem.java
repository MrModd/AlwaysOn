package listener;

import exceptions.OptionException;
import gui.MainGUI;
import gui.SysTray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import resources.Strings;
import structures.Options;
import threads.Simulator;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTrayStartStopItem implements ActionListener {
	MainGUI gui;
	SysTray tray;
	
	public SysTrayStartStopItem(MainGUI g, SysTray t) {
		gui = g;
		tray = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) { //Start or Stop field selected on tray icon menu
		if (gui.startStop.getText().equals(Strings.startButton) || gui.startStop.getText().equals(Strings.startHideButton)) {
			try {
				Options opts = new Options(gui.alt.isSelected(), gui.ctrl.isSelected(), gui.shift.isSelected(), gui.letter.getSelectedIndex(), (int)(Integer.parseInt(gui.times.getValue().toString())));
				gui.run = new Simulator(gui.defs, opts);
				gui.thread = new Thread(gui.run);
				gui.thread.start();
				gui.alt.setEnabled(false);
				gui.ctrl.setEnabled(false);
				gui.shift.setEnabled(false);
				gui.letter.setEnabled(false);
				gui.times.setEnabled(false);
				gui.startStop.setText(Strings.stopButton);
				tray.startStop.setLabel(Strings.trayStop);
			} catch (OptionException e) {
				if(gui.defs.verbose) {
					e.printStackTrace();
					System.out.println(Strings.verbOptErrMessage);
				}
				JOptionPane.showMessageDialog(null, Strings.optionErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			gui.thread.interrupt();
			gui.alt.setEnabled(true);
			gui.ctrl.setEnabled(true);
			gui.shift.setEnabled(true);
			gui.letter.setEnabled(true);
			gui.times.setEnabled(true);
			gui.startStop.setText(Strings.startHideButton);
			tray.startStop.setLabel(Strings.trayStart);
		}
	}

}
