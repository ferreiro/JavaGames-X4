package tp.pr5.logic;

public abstract class Move {
	protected int column;
	protected int row;
	protected Counter currentPlayer;
	protected boolean emptyMoves; // Hay movimientos disponibles en celdas vacías
	
	public Move(Counter color, int column) {
		currentPlayer = color;
		emptyMoves = true;
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

	// Metodo para el reversi Move
	public boolean availableEmpty(Board board) {
		return false;
	}

	public Counter getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(Counter c) {
		currentPlayer = c;
	}
	/*
	public Counter changeColor(Counter c) {
		if (c == Counter.WHITE) return Counter.BLACK;
		else if(c == Counter.BLACK) return Counter.WHITE;
		else return Counter.EMPTY;
	}
	*/
}
