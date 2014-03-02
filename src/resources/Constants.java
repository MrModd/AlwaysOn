package resources;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public interface Constants {
	//If you don't remember the alphabet, this should be a good start point :D
	public static String[] letters = { "--", "A", "B", "C", "D", "E", "F", "G", "H",
		"I", "J", "K", "L", "M", "N", "O", "P", "Q",
		"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	
	//Where to read and write program status on opening and closure
	public static String dataFile = "data";
	
	//Where to read and write program settings
	public static String statusFile = "state";
	
	//Minimum number of minutes which can be chosen
	public static int minMinutes = 1;
	
	//Maximum number of minutes which can be chosen
	public static int maxMinutes = 180;
	
	//Single increment on the JSpinner object
	public static int stepMinutes = 1;
}