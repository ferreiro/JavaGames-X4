package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.command.Command;
import tp.pr5.command.CommandSet;
import tp.pr5.logic.Game;

public class ConsoleController extends Controller{
	private Scanner in;
	private boolean exit = false;
	
	public ConsoleController(GameTypeFactory factory, Game g, java.util.Scanner in) {
		super(factory,g, in);
		this.in= in;
	}
	
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
