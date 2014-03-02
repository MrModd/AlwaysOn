package threads;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

import resources.Constants;
import resources.Strings;
import structures.Definitions;
import structures.Options;

/** 
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 */
public class Simulator implements Runnable {
	private Options opts;
	private Definitions defs;
	private Lock lock;
	private HashMap<String, Integer> lettersHash;
	
	public Simulator(Definitions d, Options o) {
		opts = o;
		defs = d;
		lock = new ReentrantLock();
		
		//Building Hash table
		lettersHash = new HashMap<String, Integer>(50);
		lettersHash.put(Constants.letters[1], KeyEvent.VK_A);
		lettersHash.put(Constants.letters[2], KeyEvent.VK_B);
		lettersHash.put(Constants.letters[3], KeyEvent.VK_C);
		lettersHash.put(Constants.letters[4], KeyEvent.VK_D);
		lettersHash.put(Constants.letters[5], KeyEvent.VK_E);
		lettersHash.put(Constants.letters[6], KeyEvent.VK_F);
		lettersHash.put(Constants.letters[7], KeyEvent.VK_G);
		lettersHash.put(Constants.letters[8], KeyEvent.VK_H);
		lettersHash.put(Constants.letters[9], KeyEvent.VK_I);
		lettersHash.put(Constants.letters[10], KeyEvent.VK_J);
		lettersHash.put(Constants.letters[11], KeyEvent.VK_K);
		lettersHash.put(Constants.letters[12], KeyEvent.VK_L);
		lettersHash.put(Constants.letters[13], KeyEvent.VK_M);
		lettersHash.put(Constants.letters[14], KeyEvent.VK_N);
		lettersHash.put(Constants.letters[15], KeyEvent.VK_O);
		lettersHash.put(Constants.letters[16], KeyEvent.VK_P);
		lettersHash.put(Constants.letters[17], KeyEvent.VK_Q);
		lettersHash.put(Constants.letters[18], KeyEvent.VK_R);
		lettersHash.put(Constants.letters[19], KeyEvent.VK_S);
		lettersHash.put(Constants.letters[20], KeyEvent.VK_T);
		lettersHash.put(Constants.letters[21], KeyEvent.VK_U);
		lettersHash.put(Constants.letters[22], KeyEvent.VK_V);
		lettersHash.put(Constants.letters[23], KeyEvent.VK_W);
		lettersHash.put(Constants.letters[24], KeyEvent.VK_X);
		lettersHash.put(Constants.letters[25], KeyEvent.VK_Y);
		lettersHash.put(Constants.letters[26], KeyEvent.VK_Z);
	}
	
	public void run() {
		if (defs.verbose) System.out.println(Strings.verbThreadStart);
		lock.lock(); //Lock resources and prevent data corruption even if no other threads will be instantiated O_o
		try {
			Robot robot = new Robot();
			while(true) {
				Thread.sleep((opts.getMinutes()*1000*60)-100); //While sleeping it catch isInterrupted() status
				if (opts.getAlt()) robot.keyPress(KeyEvent.VK_ALT);
				if (opts.getCtrl()) robot.keyPress(KeyEvent.VK_CONTROL);
				if (opts.getShift()) robot.keyPress(KeyEvent.VK_SHIFT);
				if (opts.getLetterIndex()>0) robot.keyPress(lettersHash.get(Constants.letters[opts.getLetterIndex()])); //No exception detection implemented, but the program works fine as is =)
				if (defs.verbose) System.out.println(Strings.verbKeyPressed);
				Thread.sleep(opts.getMinutes()*10); //Jet another sleep period. I'm tired Zzz...
				if (opts.getAlt()) robot.keyRelease(KeyEvent.VK_ALT);
				if (opts.getCtrl()) robot.keyRelease(KeyEvent.VK_CONTROL);
				if (opts.getShift()) robot.keyRelease(KeyEvent.VK_SHIFT);
				if (opts.getLetterIndex()>0) robot.keyRelease(lettersHash.get(Constants.letters[opts.getLetterIndex()]));
				if (defs.verbose) System.out.println(Strings.verbKeyReleased);
			}
		} catch (AWTException e) { //Generated from Robot class instantiation
			JOptionPane.showMessageDialog(null, Strings.robotErrorMessage+e.getMessage(), Strings.error, JOptionPane.ERROR_MESSAGE);
		} catch(InterruptedException e) {
			//Oh no!!! This process is trying to suicide!!!
			if (defs.verbose) System.out.println(Strings.verbThreadStop);
			Thread.currentThread().interrupt();
		} finally {
			lock.unlock(); //Unlock resources
		}
	}
}