package listener;

import exceptions.OptionException;
import gui.MainGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import resources.Strings;
import structures.Options;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTraySaveExitItem implements ActionListener {
	MainGUI gui;
	
	public SysTraySaveExitItem(MainGUI g) {
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) { //Save and Exit field selected on tray icon menu
		try {
			if (gui.defs.verbose) System.out.println(Strings.verbSaving);
			FileOutputStream f = new FileOutputStream(gui.defs.output);
			ObjectOutputStream o = new ObjectOutputStream(f);
			Options opts = new Options(gui.alt.isSelected(), gui.ctrl.isSelected(), gui.shift.isSelected(), gui.letter.getSelectedIndex(), (int)(Integer.parseInt(gui.times.getValue().toString())));
			o.writeObject(opts);
			o.close();
		} catch (IOException e) {
			if (gui.defs.verbose) {
				e.printStackTrace();
				System.out.println(Strings.verbOutFileError);
			}
			JOptionPane.showMessageDialog(null, Strings.IOErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
		} catch (OptionException e) {
			if (gui.defs.verbose) {
				e.printStackTrace();
				System.out.println(Strings.verbOptErrMessage);
			}
			JOptionPane.showMessageDialog(null, Strings.optionErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
		}
		finally {
			//DIE POTATO!!!
			if (gui.thread.isAlive()) gui.thread.interrupt();
			if (gui.defs.verbose) System.out.println(Strings.verbExit);
			System.exit(0);
		}
	}

}
