package resources;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public interface Strings {
	//Title
	public static String title = "Always On";
	
	//CLI interface
	public static String man = "Always On\n" +
								"Version 1.0 (20111001)\n" +
								"Created by MrModd (http://mrmodd.it/)\n" +
								"First built: 28th, April 2011\n" +
								"\n" +
								"Prevents the appearance of the screensaver and inhibits the standby mode\n" +
								"simulating a key pressure from the keyboard and keeping active the screen.\n" +
								"\n" +
								"Sintax for the command line program:\n" +
								"    java -jar <JAR FILE> [Options]\n" +
								"\n" +
								"OPTIONS:\n" +
								"    -h:        Help: Show this screen\n" +
								"    -v:        Verbose: Show error and information messages in console\n" +
								"    -i <FILE>: Input file: Set the file from which to read program status\n" +
								"    -o <FILE>: Output file: Set the file where to save program status\n" +
								"    -g:        Graphic: Use Java default graphic libraries\n" +
								"    -t:        Tray: Disable system tray\n" +
								"    -s:        Save: Save options choosen in the program calling";
	public static String argsError = "Error on sintax.\n" +
									 "Please call program with -h argument to see all arguments.";
	
	//Buttons
	public static String startHideButton = "Start & Hide window";
	public static String startButton = "Start";
	public static String stopButton = "Stop";
	public static String exitButton = "Exit";
	public static String saveAndExitButton = "Save and Exit";
	
	//Texts in the window
	public static String label1 = "Always On";
	public static String label2 = "v1.0 by MrModd";
	public static String label3 = "Distributed under GPL v3";
	public static String separator = "----------------------------------";
	public static String instructions1 = "Select which key to press:";
	public static String instructions2 = "Select interval between two keystrokes:";
	public static String instructions3 = "minutes.";
	
	//Special characters label
	public static String alt = "ALT";
	public static String ctrl = "CTRL";
	public static String shift = "SHIFT";
	
	//Exception messages
	public static String error = "Error";
	public static String IconError = "Icon not supported by this system.\nIt will be disabled.";
	public static String letterException = " is not a valid value for letter field.";
	public static String timeException = " is not a valid value for times field.";
	public static String optionErrorMessage = "An error occurred while trying to set parameters.\nException error is:\n";
	public static String robotErrorMessage = "An error occurred initializing libraries.\nPlease, try again.\nException error is:\n";
	public static String IOErrorMessage = "Unable to save status on file.\nExiting without saving.";
	
	//TrayIcon fields
	public static String trayShow = "Show window";
	public static String trayHide = "Hide window";
	public static String trayStart = "Start simulation";
	public static String trayStop = "Stop simulation";
	public static String trayHelp = "Help";
	public static String traySaveExit = "Save and Exit";
	public static String trayExit = "Exit";
	
	//Confirm dialog
	public static String ConfirmExit = "Reduce to icon?\nChoose No if you want to close.";
	public static String ConfirmExitTitle = "What should I do?";
	
	//Credits
	public static String credits = "Always On\nVersion 1.0 (20111001)\nCreated by MrModd (http://mrmodd.it/)\nFirst built: 28th, April 2011\nThis program is under the GPLv3\nCheck the README file for all the informations";
	
	//Verbose messages
	public static String verbStart = "Program started.";
	public static String verbExit = "Program ended.";
	public static String verbInFileError = "Unable to read from selected file.\nDefault configuration will be used.";
	public static String verbStateFileError = "Unable to read from selected file.\nNo arguments imported.";
	public static String verbOutFileError = "Unable to write to selected file.\nExiting without saving.";
	public static String verbContentFileError = "Invalid file format. Default configuration will be used.";
	public static String verbContentStateFileError = "Invalid file format. No arguments imported.";
	public static String verbCreatingIcon = "Creating TrayIcon...";
	public static String verbCreatingGUI = "Creating GUI...";
	public static String verbNoIcon = "Icon will be disabled.";
	public static String verbIconError = "Error adding icon on the System Tray.";
	public static String verbIconUnsupported = "Icon not supported by this system.";
	public static String verbLook = "Unable to set System Look And Feel";
	public static String verbThreadStart = "Thread is now starting.";
	public static String verbThreadStop = "Thread will be killed.";
	public static String verbOptErrMessage = "An error occurred while trying to set parameters.";
	public static String verbSaving = "Saving settings state on file.";
	public static String verbSavingState = "Saving arguments on file.";
	public static String verbKeyPressed = "Keys pressed.";
	public static String verbKeyReleased = "Keys released.";
}