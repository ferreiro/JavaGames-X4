package tp.pr4.logic;

public interface GameObserver {
	
	// When the execution of a move finishes, the observer receives a notification through this method.
	void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer);
	
	// When a move starts executing, the observer receives a notification through this method.
	void moveExecStart(Counter player);
	
	// When the game finishes, the observer receives a notification through this method.
	void onGameOver(ReadOnlyBoard board, Counter winner);
	
	// 	Move errors are reported to observers through this method.
	void onMoveError(java.lang.String msg);
	
	// 	When the execution of undo finishes, the observer receives a notification through this method.
	void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);
	
	// 	When the undo fails, because it is not possible, observers are notified through this method.
	void onUndoNotPossible();
	
	// 	When the game is reset, either with a new game or simply restarting the current game, the observer receives a notification through this method.
	void reset(ReadOnlyBoard board, Counter player);//, java.lang.Boolean undoPossible

}
