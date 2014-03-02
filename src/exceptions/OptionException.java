package exceptions;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class OptionException extends Exception {
	private static final long serialVersionUID = 1L;

	public OptionException() {
		super();
	}
	
	public OptionException(String e) {
		super(e);
		
	}
}