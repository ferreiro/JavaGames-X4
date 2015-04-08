package tp.pr4.control;

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;

public class ConsoleController extends Controller{
	static java.util.Scanner in;
	
	public ConsoleController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
	}
	
	public void run() {
		Move move = null;
		int option;
		boolean exit = false, valid, undo;
		
		do {
			
			option = Resources.menu(getGame(), in);
			
			switch(option) {
			case 0: 
				
				move = getPlayers()[getCurrentPlayer()].getMove(getGame().getBoard(), getC()[getCurrentPlayer()]);
				
				try {
					valid = getGame().executeMove(move);
					if (valid) {
						changePlayer(); // Change Current player
					}
				}
				catch(InvalidMove e) {
//					System.err.println(e.getMessage());
				} 
				if (getGame().isFinished()) {
					exit = true;
				} 
					
				break;
			case 1:
				// Undo 
				undo = false;
				undo = getGame().undo();
				if (undo){
					changePlayer(); // Change Current player
				}else{ 
//					System.out.println("Nothing to undo, please try again");
				}

				break;
			case 2:
				// Restart 
				initGame(); // restart the game
//				System.out.println("Game restarted.");
				break;
				
			case 3:
				// Exit
				exit = true;
				System.out.println("Exit requested. ");//es raro pero en console view no hay ninguna funcion para cuando se acaba el juego
				break;

			case 4://c4
				
				setGameType(new Connect4Factory());
				initGame();
//				System.out.println("Game restarted.");
				 
				break;
			case 5://co
				
				setGameType(new ComplicaFactory());
				initGame();
//				System.out.println("Game restarted.");
				
				break;
			case 6: //gr
				
				setGameType(new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY)); 
				initGame();
//				System.out.println("Game restarted.");

				break;
			case 7:
				Resources.help();
				break;
			case 8:
				// WHITE HUMAN
				setPlayerInPosition(getGameType().createHumanPlayerAtConsole(in),0);
 				
				break;
			case 9:
				// WHITE RANDOM
				setPlayerInPosition(getGameType().createRandomPlayer(),0);

				break;
			case 10:
				// BLACK HUMAN
				setPlayerInPosition(getGameType().createHumanPlayerAtConsole(in),1);

				break;
			case 11:
				// BLACK RANDOM
				setPlayerInPosition(getGameType().createRandomPlayer(),1);
			}
			 
			// If it's finished. Then exit the loop.
			
			if (getGame().isFinished()) 
			{
				/*
				 * This method is not used in the fourth assigment
				 * game.getBoard().printBoard();
				 */
				in.close();
				Counter counterWinner = getGame().getWinner();
				exit = true;
				
//				System.out.print("Game over."); 
//				if (counterWinner != Counter.EMPTY) {
//					if (counterWinner == Counter.WHITE) {
//						System.out.println("White wins"); 
//					}
//					if (counterWinner == Counter.BLACK) {
//						System.out.println("Black wins"); 
//					}
//				}
//				else {
//					System.out.println("Tie game, no winner");
//				}					
			}  
		} while(!exit);	
		
		System.out.println("Closing the game...  ");
		 
	}
}
