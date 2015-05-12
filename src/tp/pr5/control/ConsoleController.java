package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.Resources.Resources;
import tp.pr5.command.Command;
import tp.pr5.command.CommandSet;
import tp.pr5.logic.Game;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;

public class ConsoleController extends Controller{
	private Scanner in;
	private boolean exit = false;
	
	public ConsoleController(GameTypeFactory factory, Game g, java.util.Scanner in) {
		super(factory,g, in);
		this.in= in;
	}
	
	/*public void run() {
		Move move = null;
		int option;
		boolean valid, undo;
		exit = false;
		
		do {
			option = Resources.menu(getGame(), in);
			
			switch(option) {
			case 0: 
				// Make a move
				
				move = getPlayers()[getCurrentPlayer()].getMove(getGame().getBoard(), getC()[getCurrentPlayer()]);
				
				try {
					valid = getGame().executeMove(move);
					if (valid) {
						changePlayer(); // Change Current player
					}
				}
				catch(InvalidMove e) {
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
				}

				break;
			case 2:
				// Restart 
				initGame(); // restart the game
				break;
				
			case 3:
				// Exit
				requestQuit(true);
				break;

			case 4://c4
				
				setGameType(new Connect4Factory());
				initGame();
				 
				break;
			case 5://co
				
				setGameType(new ComplicaFactory());
				initGame();
				
				break;
			case 6: //gr
				
				setGameType(new GravityFactory(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY)); 
				initGame();

				break;
			case 12:
				// PLAY Reversi
				setGameType(new ReversiFactory()); 
				initGame();
				
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
				in.close();
				exit = true;					
			}  
		} while(!exit);	
		 
	}*/
	
	public void run(){
		while ( !exit ) {
			System.out.print("> ");
			String line = in.nextLine();
			Command cmd = CommandSet.parse(line);
			if ( cmd != null){
				cmd.execute(this.getGame(),this,in);
				getGame().getBoard().printBoard();
			}
				else 
					System.err.print("Error: "+line);
		}
		in.close();
	}
	
	public void requestQuit(boolean exitAsk) {
		exit  = true;
		if (exitAsk)
			System.out.println("Exit requested. ");
	}

}
