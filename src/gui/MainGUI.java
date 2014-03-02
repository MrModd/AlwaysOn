package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import resources.Constants;
import resources.Strings;
import structures.Definitions;
import structures.Options;
import threads.Simulator;
import exceptions.OptionException;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class MainGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private SysTray tray;
	public Runnable run;
	public Thread thread = new Thread(); //Dummy instantiation to avoid null pointer exception
	public Definitions defs;
	public JCheckBox alt;
	public JCheckBox ctrl;
	public JCheckBox shift;
	public JComboBox letter;
	public JSpinner times;
	public JButton startStop;
	private JButton saveExit;
	private JButton exit;
	
	public MainGUI(Definitions d, Options options, SysTray t) {
		defs = d;
		tray = t;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		//Set window attributes
		this.setTitle(Strings.title);
		List<Image> icons = new ArrayList<Image>();
		icons.add(toolkit.getImage(this.getClass().getResource("icon256.png")));
		icons.add(toolkit.getImage(this.getClass().getResource("icon128.png")));
		icons.add(toolkit.getImage(this.getClass().getResource("icon64.png")));
		icons.add(toolkit.getImage(this.getClass().getResource("icon48.png")));
		icons.add(toolkit.getImage(this.getClass().getResource("icon32.png")));
		icons.add(toolkit.getImage(this.getClass().getResource("icon24.png")));
		this.setIconImages(icons); //Set window icon. System should choice the best resolution from the array, but I'm not sure it does his job...
		/*Image icon = toolkit.getImage(this.getClass().getResource("icon256.png"));
		this.setIconImage(icon); //Set window icon*/
		this.setResizable(false);
		if (!defs.graphic) {
			try {
				javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName()); //Use system graphics
				//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); //Use GTK graphics (works only on KDE)
			} catch(Exception e) {
				if (defs.verbose) {
					e.printStackTrace();
					System.out.println(Strings.verbLook);
				}
			}
		}
		if (!defs.disableTray) { //Choice what to do on exit
			this.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent arg0) {}

				@Override
				public void windowClosed(WindowEvent arg0) {}

				@Override
				public void windowClosing(WindowEvent arg0) {
					int input = JOptionPane.showConfirmDialog(null, Strings.ConfirmExit, Strings.ConfirmExitTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					switch (input) {
					case 0 : {
						setVisible(false);
						if (!defs.disableTray) {
							tray.showHide.setLabel(Strings.trayShow);
						}
						break;
					}
					case 1 : {
						if (thread.isAlive()) thread.interrupt();
						if (defs.verbose) System.out.println(Strings.verbExit);
						System.exit(0);
					}
					case 2 : {
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					}
					}
				}
				@Override
				public void windowDeactivated(WindowEvent arg0) {}
				@Override
				public void windowDeiconified(WindowEvent arg0) {}
				@Override
				public void windowIconified(WindowEvent arg0) {}
				@Override
				public void windowOpened(WindowEvent arg0) {}
				
			});
		}
		else this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//---// Making GUI
		
		//Create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout());
		
		//Create title
		JPanel northPanel = new JPanel();
		JPanel north1 = new JPanel();
		JPanel north2 = new JPanel();
		JPanel north3 = new JPanel();
		JPanel north4 = new JPanel();
		JLabel label1 = new JLabel(Strings.label1);
		JLabel label2 = new JLabel(Strings.label2);
		JLabel label3 = new JLabel(Strings.label3);
		JLabel label4 = new JLabel(Strings.separator);
		northPanel.setLayout(new GridLayout(4,1));
		north1.setLayout(new FlowLayout(FlowLayout.CENTER));
		north2.setLayout(new FlowLayout(FlowLayout.CENTER));
		north3.setLayout(new FlowLayout(FlowLayout.CENTER));
		north4.setLayout(new FlowLayout(FlowLayout.CENTER));
		north1.add(label1);
		north2.add(label2);
		north3.add(label3);
		north4.add(label4);
		northPanel.add(north1);
		northPanel.add(north2);
		northPanel.add(north3);
		northPanel.add(north4);
		
		//Create choice section
		JPanel centerPanel = new JPanel();
		JPanel center1 = new JPanel();
		JPanel center2 = new JPanel();
		JPanel choices = new JPanel();
		JPanel centerTitle1 = new JPanel();
		JPanel centerTitle2 = new JPanel();
		JLabel instructions1 = new JLabel(Strings.instructions1);
		JLabel instructions2 = new JLabel(Strings.instructions2);
		JLabel instructions3 = new JLabel(Strings.instructions3);
		JPanel flowTitle3 = new JPanel();
		
		centerPanel.setLayout(new BorderLayout());
		center1.setLayout(new BorderLayout());
		center2.setLayout(new BorderLayout());
		choices.setLayout(new FlowLayout());
		flowTitle3.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerTitle1.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerTitle2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		centerTitle1.add(instructions1);
		centerTitle2.add(instructions2);
		
		alt = new JCheckBox(Strings.alt);
		ctrl = new JCheckBox(Strings.ctrl);
		shift = new JCheckBox(Strings.shift);
		letter = new JComboBox(Constants.letters);
		alt.setSelected(options.getAlt());
		ctrl.setSelected(options.getCtrl());
		shift.setSelected(options.getShift());
		letter.setSelectedIndex(options.getLetterIndex());
		choices.add(alt);
		choices.add(ctrl);
		choices.add(shift);
		choices.add(letter);
		
		center1.add(centerTitle1, BorderLayout.NORTH);
		center1.add(choices, BorderLayout.CENTER);
		
		times = new JSpinner(new SpinnerNumberModel(options.getMinutes(), Constants.minMinutes, Constants.maxMinutes, Constants.stepMinutes));
		flowTitle3.add(times);
		flowTitle3.add(instructions3);
		
		center2.add(centerTitle2, BorderLayout.NORTH);
		center2.add(flowTitle3, BorderLayout.CENTER);
		
		centerPanel.add(center1, BorderLayout.CENTER);
		centerPanel.add(center2, BorderLayout.SOUTH);
		
		//Create buttons selection
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(3,1));
		
		if (!defs.disableTray) startStop = new JButton(Strings.startHideButton);
		else startStop = new JButton(Strings.startButton);
		saveExit = new JButton(Strings.saveAndExitButton);
		exit = new JButton(Strings.exitButton);
		
		southPanel.add(startStop);
		southPanel.add(saveExit);
		southPanel.add(exit);
		
		//Construct the frame on the main panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		
		//Set dimension and show window
		this.validate();
		this.pack(); //Set dimension of the window related on dimension of panels
		Dimension scrSize = toolkit.getScreenSize();
		int scrWidth = scrSize.width;
		int scrHeight = scrSize.height;
		this.setLocation((scrWidth/2)-(this.getWidth()/2), (scrHeight/2)-(this.getHeight()/2));
		this.setVisible(true);
		
		//---// GUI made!
		
		//Events management
		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (startStop.getText().equals(Strings.startButton) || startStop.getText().equals(Strings.startHideButton)) {
					try {
						Options opts = new Options(alt.isSelected(), ctrl.isSelected(), shift.isSelected(), letter.getSelectedIndex(), (int)(Integer.parseInt(times.getValue().toString())));
						run = new Simulator(defs, opts);
						thread = new Thread(run);
						thread.start();
						if (!defs.disableTray) setVisible(false);
						alt.setEnabled(false);
						ctrl.setEnabled(false);
						shift.setEnabled(false);
						letter.setEnabled(false);
						times.setEnabled(false);
						startStop.setText(Strings.stopButton);
						if (!defs.disableTray) {
							tray.showHide.setLabel(Strings.trayShow);
							tray.startStop.setLabel(Strings.trayStop);
						}
					} catch (OptionException e) {
						if(defs.verbose) {
							e.printStackTrace();
							System.out.println(Strings.verbOptErrMessage);
						}
						JOptionPane.showMessageDialog(null, Strings.optionErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					thread.interrupt();
					alt.setEnabled(true);
					ctrl.setEnabled(true);
					shift.setEnabled(true);
					letter.setEnabled(true);
					times.setEnabled(true);
					if (!defs.disableTray) {
						startStop.setText(Strings.startHideButton);
						tray.startStop.setLabel(Strings.trayStart);
					}
					else startStop.setText(Strings.startButton);
				}
			}
		});
		
		saveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try {
					if (defs.verbose) System.out.println(Strings.verbSaving);
					FileOutputStream f = new FileOutputStream(defs.output);
					ObjectOutputStream o = new ObjectOutputStream(f);
					Options opts = new Options(alt.isSelected(), ctrl.isSelected(), shift.isSelected(), letter.getSelectedIndex(), (int)(Integer.parseInt(times.getValue().toString())));
					o.writeObject(opts);
					o.close();
				} catch (IOException e) {
					if (defs.verbose) {
						e.printStackTrace();
						System.out.println(Strings.verbOutFileError);
					}
					JOptionPane.showMessageDialog(null, Strings.IOErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
				} catch (OptionException e) {
					if (defs.verbose) {
						e.printStackTrace();
						System.out.println(Strings.verbOptErrMessage);
					}
					JOptionPane.showMessageDialog(null, Strings.optionErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
				}
				finally {
					//DIE POTATO!!!
					if (thread.isAlive()) thread.interrupt();
					if (defs.verbose) System.out.println(Strings.verbExit);
					System.exit(0);
				}
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (thread.isAlive()) thread.interrupt();
				if (defs.verbose) System.out.println(Strings.verbExit);
				System.exit(0);
				//So long and thanks for all the fish
			}
		});
	}
}