package tp.pr4;

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
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.GravityRules;
import tp.pr4.logic.Rules;
import tp.pr4.views.console.ConsoleView;
import tp.pr4.views.window.MainWindow;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		GameRules r = new Connect4Rules();
		Game g = new Game(r);	// The game is the model
		GameTypeFactory factory = new Connect4Factory();

		WindowController w = new WindowController(factory , g);
		ConsoleController c = new ConsoleController(factory , g);
		  
		// Adding observers from the model (Game)
		g.addObserver(new ConsoleView(g));
		g.addObserver(new MainWindow());
		 
/*
 * 		PREVIOUS MAIN OF THE PROJECT
 * 
		Game game;
		Scanner in = new Scanner(System.in);
		Controller controller;
		
		GameRules gameRules = new Connect4Rules();
		GameTypeFactory f = new Connect4Factory();
		
		boolean valid = false, help = false;
		
			if (args.length == 0){
				valid = true;
			}
			else if (args.length == 1) {
				if (args[0].equals("-h") || args[0].equals("--help")){
					Resources.helpInit();
					help = true;
				}
				else{
					System.err.println("Incorrect use: Unrecognized option: " + args[0]);
					System.err.println("For more details, use -h|--help.");
				}
			}
			else if (args.length == 2) {
				if (args[0].equals("-g") || args[0].equals("--game")){
					if (args[1].equals("c4")){
						gameRules = new Connect4Rules();
						f = new Connect4Factory();
						valid = true;
					}
					else if (args[1].equals("co")){
						gameRules = new ComplicaRules();
						f = new ComplicaFactory();
						valid = true;
					}
					else if (args[1].equals("gr")){
						gameRules = new GravityRules(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
						f = new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
						valid = true;
					}
					else {
						System.err.println("Incorrect use: game ’" + args[1].toLowerCase() + "’ incorrect.");
						System.err.println("For more details, use -h|--help.");
					}
				}
				else {
					System.err.println("Incorrect use: Unrecognized option: " + args[0].toLowerCase());
					System.err.println("For more details, use -h|--help.");
				}
			}
			else if (args.length == 5) {
				if (args[0].equals("-g") || args[0].equals("--game")){
					if(args[1].equals("c4") || args[1].equals("co")){
						System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4]);
						System.err.println("For more details, use -h|--help.");
					}
				}
			}
			else if (args.length == 6){
				if (args[0].equals("-g") || args[0].equals("--game")){
					if (args[1].equals("gr")){
						if (args[2].equals("-x") || args[2].equals("--dimX")){
							try {
								   Integer.parseInt(args[3]);
								   if (args[4].equals("-y") || args[4].equals("--dimY")){
									   try {
										   Integer.parseInt(args[5]);
										   Resources.setGravityDimX(Integer.parseInt(args[3]));
										   Resources.setGravityDimY(Integer.parseInt(args[5]));
										   gameRules = new GravityRules(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
										   f = new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
										   valid = true;
									   }
									   catch(NumberFormatException e){
										   System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
										   System.err.println("For more details, use -h|--help.");
									   }
								   }
								   else {
									   System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
									   System.err.println("For more details, use -h|--help.");
								   }
							}
							catch(NumberFormatException e){
								System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
								System.err.println("For more details, use -h|--help.");
							}
						}
						else {
							System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
							System.err.println("For more details, use -h|--help.");
						}
					}
					else if(args[1].equals("c4") || args[1].equals("co")){
						System.err.println("Incorrect use: illegal arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]);
						System.err.println("For more details, use -h|--help.");
					}
					else{
						System.err.println("Incorrect use: game ’" + args[1].toLowerCase() + "’ incorrect.");
						System.err.println("For more details, use -h|--help.");
					}
				}
				else {
					System.err.println("Incorrect use: Unrecognized option: " + args[0].toLowerCase());
					System.err.println("For more details, use -h|--help.");
				}
			}
			else {
				System.err.println("Incorrect use: Unrecognized option: " + args[0].toLowerCase());
				System.err.println("For more details, use -h|--help.");
			}
			
			if(valid){
				game = new Game(gameRules);
				
				controller = new Controller(f, game, in);
				controller.run();
				System.exit(0);
			}
			else if (help){
				System.exit(0);
			}
			else{
				System.exit(1);
			}
 			*/
		
	}
}
	
	

