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
	/*
	public boolean checkHorizontal(Board b, int x, int y, boolean left) {
		boolean valid = false;
		int auxColumn = x, total = 0;
		Counter color = b.getPosition(x, y);
		
		if (left) { // Check left tiles
			while((auxColumn > 1) && (b.getPosition(auxColumn - 1, y) != color)) {
				total += 1; auxColumn -= 1; 
			}
			if ((total >= 1) && (b.getPosition(auxColumn - 1, y) == color)) {
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// Si se han formado celdas y la �ltima ficha es del mismo color que la primera 
			}
		} 
		else { // Check Right tiles
			while((auxColumn < b.getWidth()) && (b.getPosition(auxColumn + 1, y) != color)) {
				total += 1; auxColumn += 1;	// When right, move to right
			}
			if ((total >= 1) && (b.getPosition(auxColumn, y) == color)) {
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
			}
		}
		if (valid) {
			// TODO: Marcar las celdas
			// Posici�n final para guardar: (auxColumn, y)
		}
	 
		return valid;
	}
	*/

	public Counter winningMove(Move lastMove, Board b) {
		boolean valid = false;
		Counter c = Counter.EMPTY;
			
		valid = checkHorizontal(b, lastMove.column, lastMove.getRow(), false);
 
		
		return c;
	}

	public boolean checkHorizontal(Board b, int x, int y, boolean left) {
		boolean valid = false;
		int auxColumn = x, total = 0;
		Counter color = b.getPosition(x, y);

		// DEBUG System.out.println("X, Y = " + x + " " + y);
		// DEBUG System.out.println("Color = " + color);
		// DEBUG System.out.println("AuxColumn = " + auxColumn);
		
		while((auxColumn > 1) && (b.getPosition(auxColumn - 1, y) != color)) {

			total += 1; auxColumn -= 1; 
			
			// DEBUG System.out.println("----");
			// DEBUG System.out.println("WHILE " + total);
			// DEBUG System.out.println("Total = " + total);
			// DEBUG System.out.println("AuxColumn = " + auxColumn);
		} 
		if ((total >= 1) && (b.getPosition(auxColumn - 1, y) == color)) {
			// DEBUG System.out.println("----");
			// DEBUG System.out.println("Valid");

			valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
			
			// TODO Si se han formado celdas y la �ltima ficha es del mismo color que la primera 
			// TODO: Marcar las celdas
			// Posici�n final para guardar: (auxColumn, y)
		}

		// DEBUG System.out.println("====================================");
	 
		return valid;
	}
	
	
	
	
	
	public Board newBoard() {
		Board b = new Board(dimX, dimY);
		
		b.setPosition(4, 4, Counter.BLACK);
		b.setPosition(5, 5, Counter.BLACK);
		b.setPosition(5, 4, Counter.WHITE);
		b.setPosition(4, 5, Counter.WHITE);
		
		return b;
	}

	public boolean isDraw(Counter lastMove, Board b) {
		// TODO Auto-generated method stub
		return false;
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