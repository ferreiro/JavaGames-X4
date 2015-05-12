package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class RestartCmd implements Command {
	
	public RestartCmd(){}

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		c.initGame();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("restart") )
			return null;
		return this;
	}

}
