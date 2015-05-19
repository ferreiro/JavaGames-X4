package tp.pr5.logic;

import tp.pr5.control.Controller;

public interface Command {
	public void execute(Game g, Controller controller);
	public Command parse(String line);
}
