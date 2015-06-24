package tp.pr5.logic;

import tp.pr5.Resources.Counter;
import tp.pr5.Resources.Resources;

@SuppressWarnings("unused")
public class ReversiRules implements GameRules {

	private int dimX = Resources.DIMX_REVERSI;
	private int dimY = Resources.DIMY_REVERSI;
	private Counter winner;

	/*************************************/
	/********* CONSTRUCTOR ***************/
	/*************************************/

	public ReversiRules() {
		winner = Counter.EMPTY;
	}
	
	public Board newBoard() { 
		int initX = dimX / 2, initY = dimY / 2;
		Board b = new Board(dimX, dimY);
		
		// Set tiles in the middle of the board
		
		b.setPosition(initX, initX+1, Counter.WHITE); // Initializing board with tiles
		b.setPosition(initX, initY, Counter.BLACK);
		b.setPosition(initX+1, initY+1, Counter.BLACK);
		b.setPosition(initX+1, initY, Counter.WHITE);
		
		return b;
	}
	
	public Counter initialPlayer() {
		return Counter.BLACK;	// The first player now is black
	}

	/*************************************/
	/********* GETTERS / SETTERS *********/
	/*************************************/
	
	public int getDimX() {
		return this.dimX;
	}
	public int getDimY() {
		return this.dimY;
	}
	public int intRules() {
		return 3;
	}

	/*************************************/
	/********* OTHER METHODS *************/
	/*************************************/

	public boolean isDraw(Counter lastMove, Board b) {
		boolean isDraw = false;
		
			if (isGameOver(b)) {

			int totalWhite = countTiles(Counter.WHITE, b),
			totalBlack = countTiles(Counter.BLACK, b);
			 
			if ((totalWhite == totalBlack)) {
				isDraw = true;
			} 
		}
		 
		return isDraw;
	}
	
	public Counter nextTurn(Counter lastMove, Board b) {
		if (lastMove == Counter.EMPTY) {
			return Counter.EMPTY;
		}

		Counter otherColor = (lastMove == Counter.WHITE) ? Counter.BLACK : Counter.WHITE;
		boolean canMoveNextPlayer = Resources.canMakeMove(b, otherColor);
		
		if (canMoveNextPlayer) {
			return otherColor;
		} 
		else {
			return lastMove;
		}
	}

	public Counter winningMove(Move lastMove, Board b) {
		Counter colorWinner = Counter.EMPTY;
		int totalWhite, totalBlack;
		  
		if (isGameOver(b)) { // Si esta lleno el tablero o ya no hay movimientos disponibles para el blanco o negro...
 			
			totalWhite = countTiles(Counter.WHITE, b);
			totalBlack = countTiles(Counter.BLACK, b);
			
			if (totalWhite < totalBlack) {
				colorWinner = Counter.BLACK; 
			}
			else if (totalWhite > totalBlack) {
				colorWinner = Counter.WHITE;	// Hay mÃ¡s fichas BLANCAS! Gana
			}  		
		}
		winner = colorWinner;
		return colorWinner;
	}
	
	/********* AUXILIAR METHOFS *************/

	private boolean isGameOver(Board b) {
		return (b.isFull()) || (!Resources.canMakeMove(b, Counter.BLACK) && 
				!Resources.canMakeMove(b, Counter.WHITE));
	}
	
	public int countTiles(Counter c, Board b) { 
		int total = 0; 
		
		for(int i = 1; i <= dimX; i++) {
			for(int j = 1; j <= dimX; j++) {
				if (b.getPosition(i, j) == c) 
					total++;
			}
		}
		return total; // Total number of tiles for a given Color 
	} 
 
}
