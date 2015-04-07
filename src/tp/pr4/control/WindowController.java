package tp.pr4.control;																																							

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameType;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.logic.ReadOnlyBoard;
import tp.pr4.views.window.MainWindow;
 
public class WindowController extends Controller {
	static java.util.Scanner in;
	
	
	public WindowController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
	}
	
	private void changeGame(GameType gameType, int dimX, int dimY) {
		if (gameType == gameType.Gravity)
			Resources.setGravityDimX(dimX);
			Resources.setGravityDimY(dimY);
		changeG(gameType, dimX, dimY);	
	}
	
	public void makeMove(int col, int row, Counter turn) {
		boolean valid;
			
		try {
			Move mov = getGameTypeFactory().createMove(col, row, turn);
			valid = game.executeMove(mov);
			if (valid) 
				this.changePlayer();
		}
		catch (InvalidMove e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void randomMove(Counter player) {
		getGameTypeFactory().createRandomPlayer().getMove(getGame().getBoard(), player);
	}
	
	// Quit the application.
	public void requestQuit() {
		// window.onGameOver(BReadOnly, getGame().getWinner()); TODO: The window doesn't have to communicate to the views
	}
	
	public void reset() {
		initGame();
		// window.reset(BReadOnly, getGame().getTurn(), false); TODO: The window doesn't have to communicate to the views
	}
	
	public void run() {
		// window = new MainWindow(); TODO: The window doesn't have to communicate to the views
	}
	
	public void undo() {
		boolean undo = false;
		
		undo = getGame().undo();
		if (undo){
			changePlayer(); // Change Current player
			// window.onUndo(BReadOnly, nextPlayer(getGame().getTurn()), undo); TODO: The window doesn't have to communicate to the views
		}
		else{
			// window.onUndoNotPossible(); TODO: The window doesn't have to communicate to the views
		}		
	}
	
}
