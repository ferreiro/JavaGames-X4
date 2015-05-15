package tp.pr5.control;																																							

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import tp.pr5.Resources.Resources;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameType;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;
import tp.pr5.logic.PlayerType;		
 
public class WindowController extends Controller {
	static java.util.Scanner in;
//	Thread autoThread;
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
		Move rMove = getGameTypeFactory().createRandomPlayer().getMove(getGame().getBoard(), getGame().getTurn());
		try {
			getGame().executeMove(rMove);
		} catch (InvalidMove e) {}
	}
	
	// Quit the application.
	public void requestQuit() {
		game.closeGame();
	}
	
	public void reset() {
		stopAutoPlayer();
		initGame();	// Reset players, current player and 
		game.resetGame(); // Notify the window that the reset is done
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
		if(Thread.currentThread() != null){
			Thread.currentThread().interrupt();
			//2.wait for autoThread to terminate
		}
	}
	
	private void automaticMove(){
		if(game.getTurn().getMode() == PlayerType.HUMAN)
			return;
		
		exec.execute(new Runnable(){
			public void run(){
				
				while(game.getNextPlayer().getMode() == PlayerType.AUTO && !game.isFinished() && !Thread.currentThread().isInterrupted()){
					try {
						Thread.sleep(2000);
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
