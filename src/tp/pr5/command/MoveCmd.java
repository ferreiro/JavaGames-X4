package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class MoveCmd implements Command {
	
	
	public MoveCmd(){}
	

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		// TODO Auto-generated method stub

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
