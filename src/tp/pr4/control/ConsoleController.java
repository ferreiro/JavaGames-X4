package tp.pr4.control;

import tp.pr4.logic.Game;

public class ConsoleController extends Controller{
	static java.util.Scanner in;
	
	public ConsoleController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
	}
	
	public void run() {
		super.run();
	}
}
