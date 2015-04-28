package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public class ReversiRules implements GameRules {

	private int dimX = Resources.DIMX_REVERSI;
	private int dimY = Resources.DIMY_REVERSI;
	private Counter winner;
	
	public ReversiRules() {
		winner = Counter.EMPTY;
	}
	
	// Horizontal Algorithm

	public boolean horizontalFormed(Board b, int x, int y) {
		boolean valid = false;
		int total = 0, auxColumn = x; // Copy the current column
		Counter currentColor = b.getPosition(x, y);
		
		// Check Left titles
		while((auxColumn >= 1) && (b.getPosition(auxColumn - 1, y) != currentColor)) {
			total++;			
			auxColumn -= 1;
		}
		if (total >= 1 && (auxColumn >= 1)) {
			// Si se han formado celdas y la ficha última es de otro color
			valid = true;
			// MARCAR CELDAS O GUARDAR LA POSICIÓN FINAL EN UN ARRAY
			// Posición final: (auxColumn, y)
		}
		 
		// Check Right titles
		total = 0; 
		auxColumn = x;

		// swhile(auxColumn <= b.getWidth() && )
		
		return valid;
	}
	 
	
	
	
	
	
	
	
	public Board newBoard() {
		return new Board(dimX, dimY);
	}

	public boolean isDraw(Counter lastMove, Board b) {
		// TODO Auto-generated method stub
		return false;
	}

	public Counter winningMove(Move lastMove, Board b) {
		// TODO Auto-generated method stub
		return null;
	}

	public Counter initialPlayer() {
		return Counter.BLACK;
	}

	public Counter nextTurn(Counter lastMove, Board b) {
		Counter nextTurn = Counter.EMPTY;
		
		if (lastMove == Counter.WHITE) {
			nextTurn = Counter.BLACK;
		}
		else if (lastMove == Counter.BLACK) {
			nextTurn = Counter.WHITE;
		}

		return nextTurn;
	}

	public int getDimX() {return this.dimX;}
	public int getDimY() {return this.dimY;}
	public int intRules() {return 3;}

}
