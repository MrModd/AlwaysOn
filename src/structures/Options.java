package structures;

import java.io.Serializable;

import resources.Constants;
import resources.Strings;
import exceptions.OptionException;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class Options implements Serializable {
	private static final long serialVersionUID = 201105020015L; //Date and time when I set up for the first time this constant :D
	private boolean alt;
	private boolean ctrl;
	private boolean shift;
	private int letterIndex;
	private int minutes;
	
	public Options() {
		alt = false;
		ctrl = false;
		shift = false;
		letterIndex = 0;
		minutes = Constants.minMinutes;
	}
	
	public Options(boolean a, boolean c, boolean s, int l, int m) throws OptionException {
		this.putAlt(a);
		this.putCtrl(c);
		this.putShift(s);
		this.putLetterIndex(l);
		this.putMinutes(m);
	}
	
	public boolean getAlt() { return alt; }
	
	public boolean getCtrl() { return ctrl; }
	
	public boolean getShift() { return shift; }
	
	public int getLetterIndex() { return letterIndex; }
	
	public int getMinutes() { return minutes; }
	
	public void putAlt(boolean a) { alt = a; }
	
	public void putCtrl(boolean c) { ctrl = c; }
	
	public void putShift(boolean s) { shift = s; }
	
	public void putLetterIndex(int l) throws OptionException {
		if (l<0 || l>Constants.letters.length-1) throw new OptionException(l+Strings.letterException);
		else letterIndex = l;
	}
	
	public void putMinutes(int m) throws OptionException {
		if (m<Constants.minMinutes || m>Constants.maxMinutes) throw new OptionException(m+Strings.timeException);
		else minutes = m;
	}
}