package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class ExitCmd implements Command {
	
	public ExitCmd(){}

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		c.requestQuit(true);
	} 	

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("exit") )
			return null;
		return this;
	}

	

}
