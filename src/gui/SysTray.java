package gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import listener.SysTrayExitItem;
import listener.SysTrayHelpItem;
import listener.SysTrayIcon;
import listener.SysTraySaveExitItem;
import listener.SysTrayShowHideItem;
import listener.SysTrayStartStopItem;
import resources.Strings;
import structures.Definitions;
import structures.Options;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class SysTray extends JFrame {
	private static final long serialVersionUID = 1L;
	public MenuItem showHide;
	public MenuItem startStop;
	private MenuItem help;
	private MenuItem saveExit;
	private MenuItem exit;
	
	public SysTray(Definitions defs, Options opts) {
		if (!defs.graphic) {
			try {
				javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName()); //Use system graphics
			} catch(Exception e) {
				if (defs.verbose) {
					e.printStackTrace();
					System.out.println(Strings.verbLook);
				}
			}
		}
		
		if (SystemTray.isSupported()) { //Create the tray icon if supported by the system
			//Declare variables
			SystemTray tray = SystemTray.getSystemTray(); //Global container for the tray icon 
			TrayIcon trayIcon; //Goes inside tray
			PopupMenu popup = new PopupMenu(); //Goes inside trayIcon
			Toolkit toolkit = Toolkit.getDefaultToolkit(); //Uses to import icon image as resources instead of external image (useful for jar packaging)
			Image icon = toolkit.getImage(this.getClass().getResource("icon64.png"));
			
			if (defs.verbose) System.out.println(Strings.verbCreatingGUI);
			
			//Creating GUI
			MainGUI gui = new MainGUI(defs, opts, this);
			
			// ---- //
			
			//Generate menu
			
			//Set Show Hide field
			showHide = new MenuItem(Strings.trayHide);
			showHide.addActionListener(new SysTrayShowHideItem(gui, this));
			
			//Set Start Stop field
			startStop = new MenuItem(Strings.trayStart);
			startStop.addActionListener(new SysTrayStartStopItem(gui, this));
			
			//Set Help field
			help = new MenuItem(Strings.trayHelp);
			help.addActionListener(new SysTrayHelpItem());
			
			//Set Save Exit field
			saveExit = new MenuItem(Strings.traySaveExit);
			saveExit.addActionListener(new SysTraySaveExitItem(gui));
			
			//Set Exit field
			exit = new MenuItem(Strings.trayExit);
			exit.addActionListener(new SysTrayExitItem(defs, gui));
			
			//Adding field to menu array
			popup.add(showHide);
			popup.add(startStop);
			popup.addSeparator();
			popup.add(help);
			popup.add(saveExit);
			popup.add(exit);
			
			//Create TrayIcon
			trayIcon = new TrayIcon(icon, Strings.title, popup);
			trayIcon.setImageAutoSize(true); //Auto resize system tray icon
			trayIcon.addActionListener(new SysTrayIcon(gui, this));
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				if (defs.verbose) System.out.println(Strings.verbIconError);
			}
			
			// ---- //
		
		} else {
			if (defs.verbose) {
				System.out.println(Strings.verbIconUnsupported);
				System.out.println(Strings.verbNoIcon);
			}
			defs.disableTray = true;
			JOptionPane.showMessageDialog(null, Strings.IconError, Strings.error, JOptionPane.ERROR_MESSAGE);
			if (defs.verbose) System.out.println(Strings.verbCreatingGUI);
			@SuppressWarnings("unused")
			MainGUI gui = new MainGUI(defs, opts, null);
		}
	}
}
