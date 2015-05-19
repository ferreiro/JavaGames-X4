package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.Resources.Resources;
import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class HelpCmd implements Command{
	
	public HelpCmd(){}

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		Resources.help();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("help") )
			return null;
		return this;
	}

}
