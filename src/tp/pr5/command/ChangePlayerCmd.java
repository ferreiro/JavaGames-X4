package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class ChangePlayerCmd implements Command {

	int pos;
	int typePlayer;
	
	private ChangePlayerCmd(int pos, int typePlayer){
		this.pos = pos;
		this.typePlayer = typePlayer;
	}
	
	public ChangePlayerCmd(){}
	
	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		if (typePlayer == 0)
			c.setPlayerInPosition(c.getGameType().createHumanPlayerAtConsole(in) , pos);
		else if (typePlayer == 1)
			c.setPlayerInPosition(c.getGameType().createRandomPlayer() , pos);
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 3 || !tokens[0].equalsIgnoreCase("player")) return null;
		else{
			if(tokens[1].equalsIgnoreCase("white") && tokens[2].equalsIgnoreCase("human"))
				return new ChangePlayerCmd(0, 0);
			else if (tokens[1].equalsIgnoreCase("white") && tokens[2].equalsIgnoreCase("random"))
				return new ChangePlayerCmd(0, 1);
			else if (tokens[1].equalsIgnoreCase("black") && tokens[2].equalsIgnoreCase("human"))
				return new ChangePlayerCmd(1, 0);
			else if (tokens[1].equalsIgnoreCase("black") && tokens[2].equalsIgnoreCase("random"))
				return new ChangePlayerCmd(1, 1);
			else
				return null;
		}
	}
}
