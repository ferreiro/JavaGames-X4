package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;

public class MoveCmd implements Command {
	
	
	public MoveCmd(){}
	
	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		Move move = c.getPlayers()[c.getCurrentPlayer()].getMove(c.getGame().getBoard(), c.getC()[c.getCurrentPlayer()]);
		try {
			boolean valid = c.getGame().executeMove(move);
			if (valid) {
				c.changePlayer(); // Change Current player
			}
		}
		catch(InvalidMove e) {
		} 
		if (c.getGame().isFinished()) {
			c.requestQuit(false);
		} 
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 3 || !tokens[0].equalsIgnoreCase("make")) return null;
		else{
			if(tokens[1].equalsIgnoreCase("a") && tokens[2].equalsIgnoreCase("move")){
				return new MoveCmd();
			}
			else
				return null;
		}
	}

}
