package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public class ReversiRules implements GameRules {

	private int dimX = Resources.DIMX_REVERSI;
	private int dimY = Resources.DIMY_REVERSI;
	private Counter winner;
	
	public ReversiRules() {
		winner = Counter.EMPTY;
	}
 
	public Counter initialPlayer() {
		return Counter.BLACK;
	}

	/**************************
	 	Winning move
	***************************/
	
	public Counter winningMove(Move lastMove, Board b) {
		Counter colorWinner = Counter.EMPTY;
		int totalWhite, totalBlack;

		if (b.isFull()) { // Si está lleno el tablero, vemos qué color tiene más fichas
			totalWhite = countTiles(Counter.WHITE, b);
			totalBlack = countTiles(Counter.BLACK, b);
			
			if (totalWhite < totalBlack) {
				colorWinner = Counter.BLACK;
			}
			else if (totalWhite > totalBlack) {
				colorWinner = Counter.WHITE;	// Hay más fichas BLANCAS! Gana
			} 
			winner = colorWinner;
		}
		
		return colorWinner;
	}
	
	// Return total number of appearances of a Color 
	
	public int countTiles(Counter c, Board b) { 
		int total = 0; 
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {
				if (b.getPosition(i, j) == c) 
					total++;
			}
		}

		return total;
	}
	

	/**************************
	 	Other functions
	***************************/
	
	public boolean isDraw(Counter lastMove, Board b) {
		boolean isDraw = false;
		
		if ((b.isFull()) && (winner == Counter.EMPTY)) {
			isDraw = true;
		}
		else {
			isDraw = false;
		} 
		 
		return isDraw;
	}
	
	public Board newBoard() { // Creates a Board with some tiles on the center
		Board b = new Board(dimX, dimY);
		int initX = dimX / 2, initY = dimY / 2;
 
		b.setPosition(initX, initX+1, Counter.WHITE); // Initializing board with tiles
		b.setPosition(initX, initY, Counter.BLACK);
		b.setPosition(initX+1, initY+1, Counter.BLACK);
		b.setPosition(initX+1, initY, Counter.WHITE);
		
		return b;
	}

	public Counter nextTurn(Counter lastMove, Board b) {
		Counter nextTurn = Counter.EMPTY;
		
		if (lastMove == Counter.WHITE) nextTurn = Counter.BLACK;
		else if (lastMove == Counter.BLACK) nextTurn = Counter.WHITE; 

		return nextTurn;
	}

	/**************************
	 	Getters and setters 
	***************************/
	
	public int getDimX() {
		return this.dimX;
	}
	public int getDimY() {
		return this.dimY;
	}
	public int intRules() {
		return 3;
	}

}
