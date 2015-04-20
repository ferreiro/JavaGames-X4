package tp.pr4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import tp.pr4.Resources.Resources;
import tp.pr4.control.ComplicaFactory;
import tp.pr4.control.Connect4Factory;
import tp.pr4.control.ConsoleController;
import tp.pr4.control.Controller;
import tp.pr4.control.GameTypeFactory;
import tp.pr4.control.GravityFactory;
import tp.pr4.control.WindowController;
import tp.pr4.logic.ComplicaRules;
import tp.pr4.logic.Connect4Rules;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.GravityRules;
import tp.pr4.logic.Move;
import tp.pr4.views.console.ConsoleView;
import tp.pr4.views.window.MainWindow;

public class Main {

	public static void main(String[] args) {
		boolean valid = true, help = false;
		Deque<String> deque = new ArrayDeque<>();
		int modeInt = 0; // 0 window, 1 console
		int gameInt = 0; // 0 c4, 1 co, 2 gr
		int counter = 0;
		Scanner in = new Scanner(System.in);
		GameRules r = new Connect4Rules();
		Game g = new Game(r);	// The game is the model
		GameTypeFactory factory = new Connect4Factory();
		Controller controller;
		
		// Test some stuff
		/*
		GameRules testR = new Connect4Rules();
		Game testG = new Game(testR); 
		GameTypeFactory testF = new Connect4Factory();
		testG.addObserver(new MainWindow(testF, testG));
		WindowController testW = new WindowController(testF, testG);
		testW.run();
		System.out.println("Finishing game test");
		*/
		
		for (int i = 0; i< args.length; i++) {
			deque.addLast(args[i].toLowerCase());
		}
		
		while (counter < args.length && valid) {
			if (deque.getFirst().equals("-h") || deque.getFirst().equals("--help")){
				deque.removeFirst();
				Resources.helpInit();
				help = true;
			}
			else if(deque.getFirst().equals("-g") || deque.getFirst().equals("--game")){
				help = false;
				deque.removeFirst();
				if (deque.getFirst().equals("c4")){
					deque.removeFirst();
					gameInt = 0;
				}
				else if (deque.getFirst().equals("co")){
					deque.removeFirst();
					gameInt = 1;
				}
				else if (deque.getFirst().equals("gr")){
					deque.removeFirst();
					gameInt = 2;
				}
				else {
					System.err.println("Incorrect use: game ’" + deque.getFirst().toLowerCase() + "’ incorrect.");
					System.err.println("For more details, use -h|--help.");
					valid = false;
				}
				counter++;
			}
			else if(deque.getFirst().equals("-u") || deque.getFirst().equals("--ui")){
				help = false;
				deque.removeFirst();
				if (deque.getFirst().equals("console")){
					deque.removeFirst();
					modeInt = 1;
				}
				else if (deque.getFirst().equals("window")){
					deque.removeFirst();
					modeInt = 0;
				}
				else{
					System.err.println("Incorrect use: Unrecognized option: " + deque.getFirst());
					System.err.println("For more details, use -h|--help.");
					valid = false;
				}
				counter++;
			}
			else if(deque.getFirst().equals("-x") || deque.getFirst().equals("--dimX")){
				help = false;
				deque.removeFirst();
				try {
					   Integer.parseInt(deque.getFirst());
					   Resources.setGravityDimX(Integer.parseInt(deque.getFirst()));
					   deque.removeFirst();
				   }
				   catch(NumberFormatException e){
					   System.err.println("Incorrect use: illegal arguments: ");
					   System.err.println("For more details, use -h|--help.");
					   valid = false;
				   }
				counter++;
			}
			else if(deque.getFirst().equals("-y") || deque.getFirst().equals("--dimY")){
				help = false;
				deque.removeFirst();
				try {
					   Integer.parseInt(deque.getFirst());
					   Resources.setGravityDimY(Integer.parseInt(deque.getFirst()));
					   deque.removeFirst();
				   }
				   catch(NumberFormatException e){
					   System.err.println("Incorrect use: illegal arguments: ");
					   System.err.println("For more details, use -h|--help.");
					   valid = false;
				   }
				counter++;
			}
			counter++;
		}
		
		if (gameInt == 1){
			r = new ComplicaRules();
			g = new Game(r);	// The game is the model
			factory = new ComplicaFactory();
		}
		else if (gameInt == 2){
			r = new GravityRules(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
			g = new Game(r);	// The game is the model
			factory = new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
		}
		
		if (modeInt == 0) {
			controller = new WindowController(factory, g);
		} else {
			controller = new ConsoleController(factory, g, in);
		}
		
		if(valid) {
			g = new Game(r);
			
			// Adding observers from the model (Game)
			g.addObserver(new ConsoleView(g));
			g.addObserver(new MainWindow(factory, g));
			
			controller.run();
			// System.exit(0);
		}
		else if (help){
			System.exit(0);
		}
		else{
			System.exit(1);
		}
	}
}
	
	

