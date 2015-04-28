package tp.pr5.views.console;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;

public class ConsoleView implements GameObserver {
	
	public ConsoleView(Observable<GameObserver> g) {
	}
	
	@Override
	// When the execution of a move finishes, the observer receives a notification through this method.
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		// To be implemented
	}
	
	@Override
	// When a move starts executing, the observer receives a notification through this method.
	public void moveExecStart(Counter player) {
		// To be implemented
	}
	
	@Override
	// When the game finishes, the observer receives a notification through this method.
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		System.out.print("Game over."); 
		if (winner != Counter.EMPTY) {
			if (winner == Counter.WHITE) {
				System.out.println("White wins"); 
			}
			if (winner == Counter.BLACK) {
				System.out.println("Black wins"); 
			}
		}
		else {
			System.out.println("Tie game, no winner");
		}
		System.out.println("Closing the game...  ");
	}
	
	@Override
	// 	Move errors are reported to observers through this method.
	public void onMoveError(java.lang.String msg) {
		System.err.println(msg);
	}
	
	@Override
	// 	When the execution of undo finishes, the observer receives a notification through this method.
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		// To be implemented
	}
	
	@Override
	// 	When the undo fails, because it is not possible, observers are notified through this method.
	public void onUndoNotPossible() {
		System.out.println("Nothing to undo, please try again");
	}
	
	@Override
	// 	When the game is reset, either with a new game or simply restarting the current game, the observer receives a notification through this method.
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		System.out.println("Game restarted.");
	} 


}
