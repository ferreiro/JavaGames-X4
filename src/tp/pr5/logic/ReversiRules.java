package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public class ReversiRules implements GameRules {

	private int dimX = Resources.DIMX_REVERSI;
	private int dimY = Resources.DIMY_REVERSI;
	private Counter winner;
	
	public ReversiRules() {
		winner = Counter.EMPTY;
	}

	public Counter winningMove(Move lastMove, Board b) {
		boolean valid = false;
		Counter c = Counter.EMPTY;

		valid = checkHorizontal(b, lastMove.column, lastMove.getRow(), true); // True = left Part
		// valid = checkHorizontal(b, lastMove.column, lastMove.getRow(), false); // False = right part

		// valid = checkVertical(b, lastMove.column, lastMove.getRow(), true); // True = Up part
		valid = checkVertical(b, lastMove.column, lastMove.getRow(), false); // True = Up part

		return c;
	}

	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public boolean checkHorizontal(Board b, int x, int y, boolean left) {
		boolean valid = false;
		int auxColumn = x, total = 0;
		Counter color = b.getPosition(x, y);
 
		if (left) {
			while((auxColumn > 1) && (b.getPosition(auxColumn - 1, y) != color)) {
				total += 1; auxColumn -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn - 1, y) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (b.getPosition(auxColumn + 1, y) != color)) {
				total += 1; auxColumn += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, y) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}			
		}
 
		return valid;
	}

	// CheckVertical: Function to Check left and right colors given a fixed position

	public boolean checkVertical(Board b, int x, int y, boolean up) {
		boolean valid = false;
		int auxRow = y, total = 0;
		Counter color = b.getPosition(x, y);
		 
		if (up) {
			while((auxRow > 1) && (b.getPosition(x, auxRow - 1) != color)) {
				total += 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow - 1) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (x, auxRow)
			}
		} 
		else {
			while((auxRow < b.getHeight()) && (b.getPosition(x, auxRow + 1) != color)) {
				total += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow + 1) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}			
		} 
		return valid;
	}
	
	public Board newBoard() {
		Board b = new Board(dimX, dimY);
		int initX = dimX / 2, initY = dimY / 2;

		// Initializing board with tiles
		
		b.setPosition(initX, initX+1, Counter.WHITE);
		b.setPosition(initX, initY, Counter.BLACK);
		b.setPosition(initX+1, initY+1, Counter.BLACK);
		b.setPosition(initX+1, initY, Counter.WHITE);
		
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
