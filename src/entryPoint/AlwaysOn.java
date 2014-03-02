package entryPoint;

import gui.MainGUI;
import gui.SysTray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import resources.Constants;
import resources.Strings;
import structures.Definitions;
import structures.Options;

/** 
 * Always On version 1.0 (20111001)
 * Copyright (C) 2011  Federico "MrModd" Cosentino (http://mrmodd.it/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
public class AlwaysOn{
	@SuppressWarnings("unused")
	static public void main(String[] args) {
		Definitions defs = new Definitions();
		boolean saveState = false;
		
		//Read arguments from file
		try {
			FileInputStream stateInputFile = new FileInputStream(Constants.statusFile);
			ObjectInputStream stateInputObj = new ObjectInputStream(stateInputFile);
			defs = (Definitions)stateInputObj.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Strings.verbStateFileError);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(Strings.verbContentStateFileError);
		}
		
		//Read arguments from program call
		for (int i=0; i<args.length; i++) {
			if (args[i].equals("-h")) {
				System.out.println(Strings.man);
				System.exit(0);
			}
			else if (args[i].equals("-v"))
				defs.verbose = true;
			else if (args[i].equals("-g"))
				defs.graphic = true;
			else if (args[i].equals("-t"))
				defs.disableTray = true;
			else if (args[i].equals("-s"))
				saveState = true;
			else if (args[i].equals("-i")) {
				if (i<args.length-1) {
					i++;
					defs.input = args[i];
				} else {
					System.out.println(Strings.argsError);
					System.exit(-1);
				}
			}
			else if (args[i].equals("-o")) {
				if (i<args.length-1) {
					i++;
					defs.output = args[i];
				} else {
					System.out.println(Strings.argsError);
					System.exit(-1);
				}
			}
			else {
				System.out.println(Strings.argsError);
				System.exit(-1);
			}
		}
		
		//Save arguments to file
		if (saveState) {
			try {
				if (defs.verbose) System.out.println(Strings.verbSavingState);
				FileOutputStream stateOutputFile = new FileOutputStream(Constants.statusFile);
				ObjectOutputStream stateOutputObj = new ObjectOutputStream(stateOutputFile);
				stateOutputObj.writeObject(defs);
				stateOutputObj.close();
			} catch (IOException e) {
				if (defs.verbose) {
					e.printStackTrace();
					System.out.println(Strings.verbOutFileError);
				}
			}
		}
		
		//Starting program
		if (defs.verbose) System.out.println(Strings.verbStart);
		
		//Read variable from file
		try {
			FileInputStream f = new FileInputStream(defs.input);
			ObjectInputStream o = new ObjectInputStream(f);
			Options opts = (Options)o.readObject();
			if (!defs.disableTray) { //Start GUI normally
				if (defs.verbose) System.out.println(Strings.verbCreatingIcon);
				SysTray tray = new SysTray(defs, opts);
			} else { //Start GUI without tray icon
				if (defs.verbose) System.out.println(Strings.verbNoIcon);
				if (defs.verbose) System.out.println(Strings.verbCreatingGUI);
				MainGUI gui = new MainGUI(defs, opts, null);
			}
		} catch (IOException e) {
			if (defs.verbose) {
				e.printStackTrace();
				System.out.println(Strings.verbInFileError);
			}
			if (!defs.disableTray) { //Start GUI normally
				if (defs.verbose) System.out.println(Strings.verbCreatingIcon);
				SysTray tray = new SysTray(defs, new Options());
			} else { //Start GUI without tray icon
				if (defs.verbose) System.out.println(Strings.verbNoIcon);
				if (defs.verbose) System.out.println(Strings.verbCreatingGUI);
				MainGUI gui = new MainGUI(defs, new Options(), null);
			}
		} catch (ClassNotFoundException e) {
			if (defs.verbose) {
				e.printStackTrace();
				System.out.println(Strings.verbContentFileError);
			}
			if (!defs.disableTray) { //Start GUI normally
				if (defs.verbose) System.out.println(Strings.verbCreatingIcon);
				SysTray tray = new SysTray(defs, new Options());
			} else { //Start GUI without tray icon
				if (defs.verbose) System.out.println(Strings.verbNoIcon);
				if (defs.verbose) System.out.println(Strings.verbCreatingGUI);
				MainGUI gui = new MainGUI(defs, new Options(), null);
			}
		}
		
	}
}