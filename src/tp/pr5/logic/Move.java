package tp.pr5.logic;

public abstract class Move {
	protected int column;
	protected int row;
	protected Counter currentPlayer;
	
	public Move(Counter color, int column) {
		currentPlayer = color;
		this.column = column;
	}
	
	// ejecutaMovimiento(Tablero tab)
	public abstract boolean executeMove(Board board) throws InvalidMove;
	
	// getJugador() devuelve el color del jugador al que pertenece el movimiento
	public Counter getPlayer() {
		return currentPlayer;
	}
	
	// undo(Tablero tab) deshace el ultimo movimiento del tablero recibido como parametro
	public abstract void undo(Board board);
	public abstract int getColumn();
	public abstract int getRow();

	// Devuelve si es posible formar tiles en celdas empty
	/*
	public boolean availableEmpty(Board board) {
		return true;	// This method is written on Reversi
	}
	*/
	
	public Counter getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(Counter c) {
		currentPlayer = c;
	}
}
