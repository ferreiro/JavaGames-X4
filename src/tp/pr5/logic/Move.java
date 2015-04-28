package tp.pr5.logic;

import java.util.ArrayList; 

public abstract class Move {
	private Counter currentPlayer;
	protected int column;
	private ArrayList<SwappedMove> swapTiles;
	
	public Move(Counter color, int column) {
		currentPlayer = color;
		this.column = column;
		swapTiles = new ArrayList<SwappedMove>();
	}
	
	public void addswapped() {

		SwappedMove i = new SwappedMove();
		swapTiles.add(i);
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
	
}
