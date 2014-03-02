package structures;

import java.io.Serializable;

import resources.Constants;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class Definitions implements Serializable {
	private static final long serialVersionUID = 201105062216L;
	public boolean verbose;
	public boolean tray;
	public boolean graphic;
	public boolean disableTray;
	public String input;
	public String output;
	
	public Definitions() {
		verbose = false;
		tray = false;
		graphic = false;
		disableTray = false;
		input = new String(Constants.dataFile);
		output = new String(Constants.dataFile);
	}
}
