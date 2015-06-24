package tp.pr5.control;																																							

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import tp.pr5.Resources.Counter;
import tp.pr5.Resources.GameType;
import tp.pr5.Resources.PlayerType;
import tp.pr5.Resources.Resources;
import tp.pr5.logic.Game;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;
 
public class WindowController extends Controller {
	static java.util.Scanner in;
	private static Executor exec;
	
	public WindowController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
		 exec = Executors.newSingleThreadExecutor();
	}
	
	@SuppressWarnings("static-access")
	public void changeGame(GameType gameType, int dimX, int dimY) {
		if (gameType == gameType.gravity) {
			Resources.setGravityDimX(dimX); 
			Resources.setGravityDimY(dimY);
		}
		changeG(gameType, dimX, dimY);
		automaticMove();
	}
	
	public void makeMove(int col, int row, Counter turn) {
		boolean valid;
	
		try {
			Move mov = getGameTypeFactory().createMove(col, row, turn);
			valid = game.executeMove(mov);
			if (valid) this.changePlayer();
		}
		catch (InvalidMove e) {
			System.out.println(e.getMessage());
		}
		automaticMove();
	}
	
	public void randomMove() {
		Move rMove = getGameTypeFactory().createRandomPlayer().getMove( getGame().getBoard(), getGame().getTurn());
		try {
			getGame().executeMove(rMove);
		} catch (InvalidMove e) {}
		automaticMove();
	}
	
	// Quit the application.
	public void requestQuit() {
		game.closeGame();
	}
	
	public void reset() {
		stopAutoPlayer();
		initGame();	// Reset players, current player and 
		game.resetGame(); // Notify the window that the reset is done
		automaticMove();
	}
	
	public void run() {
		automaticMove();
	}
	
	public void undo() {
		boolean undo = game.undo();
		if (undo){
			changePlayer(); // Change Current player 
		}
		automaticMove();
	}
	
	public void setPlayerMode(Counter player, PlayerType mode){
		player.setMode(mode);
		automaticMove();
	}
	
	private void stopAutoPlayer(){
		
	}
	
	private void automaticMove(){
		if(game.getTurn().getMode() == PlayerType.HUMAN)
			return;
		
		exec.execute(new Runnable(){
			public void run(){
				
				while(game.getTurn().getMode() == PlayerType.AUTO && !game.isFinished() && !Thread.currentThread().isInterrupted()){
					try {
						Thread.sleep(Resources.SLEEPTIME);
						if (!Thread.currentThread().isInterrupted()){
							randomMove();
						}
					} catch (InterruptedException e) {
					}
				}
			}	
		});
	}
}
