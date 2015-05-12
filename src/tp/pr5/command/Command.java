package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public interface Command {
	public void execute(Game g, ConsoleController c, Scanner in);
	public Command parse(String line);
}
