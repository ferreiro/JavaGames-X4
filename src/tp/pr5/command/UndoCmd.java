package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class UndoCmd implements Command {

	public UndoCmd(){}

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		boolean undo = g.undo();
		if (undo) c.changePlayer();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("undo") )
			return null;
		return this;
	}

}
