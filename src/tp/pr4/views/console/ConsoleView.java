package tp.pr4.views.console;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class ConsoleView implements GameObserver {
	
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
		// To be implemented
	}
	
	@Override
	// 	Move errors are reported to observers through this method.
	public void onMoveError(java.lang.String msg) {
		// To be implemented
	}
	
	@Override
	// 	When the execution of undo finishes, the observer receives a notification through this method.
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		// To be implemented
	}
	
	@Override
	// 	When the undo fails, because it is not possible, observers are notified through this method.
	public void onUndoNotPossible() {
		// To be implemented
	}
	
	@Override
	// 	When the game is reset, either with a new game or simply restarting the current game, the observer receives a notification through this method.
	public void reset(ReadOnlyBoard board, Counter player) {
		// To be implemented
	}//, java.lang.Boolean undoPossible


}
