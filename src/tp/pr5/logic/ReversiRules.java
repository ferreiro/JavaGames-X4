package tp.pr4.logic;

import tp.pr4.Resources.Resources;

public class ReversiRules implements GameRules {

	private int dimX = Resources.DIMX_REVERSI;
	private int dimY = Resources.DIMY_REVERSI;
	private Counter winner;
	
	
	public ReversiRules(){
		winner = Counter.EMPTY;
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
