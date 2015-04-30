package tp.pr5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import tp.pr5.Resources.Resources;
import tp.pr5.control.ComplicaFactory;
import tp.pr5.control.Connect4Factory;
import tp.pr5.control.ConsoleController;
import tp.pr5.control.Controller;
import tp.pr5.control.GameTypeFactory;
import tp.pr5.control.GravityFactory;
import tp.pr5.control.ReversiFactory;
import tp.pr5.control.WindowController; 
import tp.pr5.logic.ComplicaRules;
import tp.pr5.logic.Connect4Rules; 
import tp.pr5.logic.Game;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.GravityRules; 
import tp.pr5.logic.ReversiRules;
import tp.pr5.views.console.ConsoleView;
import tp.pr5.views.window.MainWindow;

public class Main {
	
	/*
	public static void main(String[] args) throws InvalidMove {
		GameTypeFactory factory = new ReversiFactory();
		GameRules rules = new ReversiRules();
		Game g = new Game( rules );
		Controller c = new WindowController(factory, g );
		
		for (int i = 1; i <= g.getBoard().getWidth(); i++) {
			for (int j = 1; j <= g.getBoard().getHeight(); j++) {
				Counter c1 = Counter.BLACK;
				if (i % 2 == 0 && j % 2 == 0) {
					c1 = Counter.WHITE;
				}
				g.getBoard().setPosition(i, j, c1);
			}
		}
		
		g.getBoard().setPosition(2, 1, Counter.EMPTY);		
		g.getBoard().printBoard();
		c.getGame().setTurn(Counter.BLACK);
		
		ReversiMove mov = new ReversiMove(2, 1, Counter.BLACK);
		
		c.getGame().executeMove(mov);
		g.getBoard().printBoard();
		
		System.out.println(g.getWinner());
		
	}
	*/
	
	public static void main(String[] args) {
		boolean valid = true;
		Deque<String> deque = new ArrayDeque<>();
		int modeInt = 0; // 0 window, 1 console
		int gameInt = 0; // 0 c4, 1 co, 2 gr
		int counter = 0;
		Scanner in = new Scanner(System.in);
		GameRules r = new Connect4Rules();
		Game g = new Game(r);	// The game is the model
		GameTypeFactory factory = new Connect4Factory();
		Controller controller;
		
		for (int i = 0; i< args.length; i++) {
			deque.addLast(args[i].toLowerCase());
		}
		
		while (counter < args.length && valid) {
			if (deque.getFirst().equals("-h") || deque.getFirst().equals("--help")){
				deque.removeFirst();
				Resources.helpInit();
			}
			else if(deque.getFirst().equals("-g") || deque.getFirst().equals("--game")){
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
				else if (deque.getFirst().equals("rv")){
					deque.removeFirst();
					gameInt = 3;
				}
				else {
					System.err.println("Incorrect use: game ’" + deque.getFirst().toLowerCase() + "’ incorrect.");
					System.err.println("For more details, use -h|--help.");
					valid = false;
				}
				counter++;
			}
			else if(deque.getFirst().equals("-u") || deque.getFirst().equals("--ui")){
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
		
		if (gameInt == 1) {
			r = new ComplicaRules();
			g = new Game(r);	// The game is the model
			factory = new ComplicaFactory();
		}
		else if (gameInt == 2) {
			r = new GravityRules(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
			g = new Game(r);	// The game is the model
			factory = new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY);
		}
		else if (gameInt == 3) {
			r = new ReversiRules();
			g = new Game(r);	// The game is the model
			factory = new ReversiFactory();
		}
		
		if (modeInt == 0) {
			controller = new WindowController(factory, g);
			g.addObserver(new MainWindow(factory, g));
		} else {
			controller = new ConsoleController(factory, g, in);
			g.addObserver(new ConsoleView(g));
		}
		
		if(valid) {
			g = new Game(r);
			controller.run();
		}
		else{
			System.exit(1);
		}
	}
	
	 
	
}