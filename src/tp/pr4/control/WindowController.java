package tp.pr4.control;																																							

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameType;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.logic.ReadOnlyBoard;
import tp.pr4.views.window.MainWindow;

public class WindowController extends Controller{
	static java.util.Scanner in;
	private MainWindow window = null;
	private ReadOnlyBoard BReadOnly = getGame().getBoard();
	
	public WindowController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
		// Constructor
	}
	
	private void changeGame(GameType gameType, int dimX, int dimY) {
		if (gameType == gameType.Gravity){
			Resources.setGravityDimX(dimX);
			Resources.setGravityDimY(dimY);
		}
		changeG(gameType, dimX, dimY);	
	}
	
	private Counter nextPlayer(Counter turn){
		Counter nextPlayer = null;
		
		if (turn == Counter.BLACK)
			nextPlayer = Counter.WHITE;
		else if (turn == Counter.WHITE)
			nextPlayer = Counter.BLACK;
		
		return nextPlayer;
	}
	
	
	public void makeMove(int col, int row, Counter turn) {
		boolean valid = false;
		
		window.moveExecStart(turn);
		
		Move move = getGameTypeFactory().createMove(col, row, turn);
		
		try{
			valid = getGame().executeMove(move);
			if (valid){
				changePlayer(); // Change Current player
			}
		}
		catch(InvalidMove e) {
			window.onMoveError(e.getMessage());
		} 
		
		window.moveExecFinished(BReadOnly, turn, nextPlayer(getGame().getTurn()));
	}
	
	public void randomMove(Counter player) {
		getGameTypeFactory().createRandomPlayer().getMove(getGame().getBoard(), player);
	}
	
	public void requestQuit() {
		// Quit the application.
		window.onGameOver(BReadOnly, getGame().getWinner());
	}
	
	public void reset() {
		initGame();
		window.reset(BReadOnly, getGame().getTurn());
	}
	
	public void run() {
		window = new MainWindow();
	}
	
	public void undo() {
		boolean undo = false;
		
		undo = getGame().undo();
		if (undo){
			changePlayer(); // Change Current player
			window.onUndo(BReadOnly, nextPlayer(getGame().getTurn()), undo);
		}
		else{
			window.onUndoNotPossible();
		}
		
		
	}
	
}
