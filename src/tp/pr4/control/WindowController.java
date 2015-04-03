package tp.pr4.control;																																							

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameType;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.views.window.MainWindow;

public class WindowController extends Controller{
	static java.util.Scanner in;
	
	public WindowController(GameTypeFactory factory, Game g) {
		
		super(factory,g, in);
		// Constructor
	}
	
	public void changeGame(GameType gameType, int dimX, int dimY) {
		if (gameType == gameType.Gravity){
			Resources.setGravityDimX(dimX);
			Resources.setGravityDimY(dimY);
		}
		changeG(gameType, dimX, dimY);
		
	}
	public void makeMove(int col, int row, Counter turn) {
		boolean valid = false;
		Move move = getGameTypeFactory().createMove(col, row, turn);
		
		try{
		valid = getGame().executeMove(move);
		if (valid) {
			changePlayer(); // Change Current player
		}
		}
		catch(InvalidMove e) {
			System.err.println(e.getMessage());
		} 
	}
	
	public void randomMove(Counter player) {
		// Make a random move.
	}
	
	public void requestQuit() {
		// Quit the application.
	}
	
	public void reset() {
		initGame();
	}
	
	public void run() {
		MainWindow window = new MainWindow();
	}
	
	public void undo() {
		boolean undo = false;
		undo = getGame().undo();
		if (undo){
			changePlayer(); // Change Current player
		}
	}
	
}
