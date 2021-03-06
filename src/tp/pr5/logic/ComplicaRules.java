package tp.pr5.logic;

import tp.pr5.logic.Board;
import tp.pr5.Resources.Counter;
import tp.pr5.Resources.Resources;

public class ComplicaRules implements GameRules {
	
	private int whiteCounter, blackCounter;
	private int dimX = Resources.DIMX_COMPLICA;
	private int dimY = Resources.DIMY_COMPLICA;
	private Counter winner;  

	/*************************************/
	/********* CONSTRUCTOR ***************/
	/*************************************/

	public ComplicaRules() {  
		winner = Counter.EMPTY;
	}

	public Board newBoard() {
		return new Board(dimX, dimY); // Creates a new board with Complica dimensions
	}

	public Counter initialPlayer() {
		return Counter.WHITE;
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
		return 1;
	}

	/*************************************/
	/********* OTHER METHODS *************/
	/*************************************/
 
	public boolean isDraw(Counter lastMove, Board b) {
		return false; // Can't be a draw in Complica
	}
	
	public Counter nextTurn(Counter colorMove, Board b) {
		Counter nextTurn = Counter.EMPTY;
		
		if (colorMove == Counter.WHITE) {
			nextTurn = Counter.BLACK;
		}
		else if (colorMove == Counter.BLACK) {
			nextTurn = Counter.WHITE;
		}

		return nextTurn;
	}
	
	// Checks whether or not, with the current board, one of the players has won and, if so, returns the colour of the winner

	public Counter winningMove(Move lastMove, Board b) {
		winner = Counter.EMPTY; // Nobody Wins
		blackCounter = whiteCounter = 0;
		
		checkHorizontal(b);	
		checkVertical(b);
		checkDiagonal1(b);		
		checkDiagonal2(b);
		complicaFinished(); // Updates Winner after all the checks
		
		return this.winner; 
	}
		
	boolean complicaFinished() { // Checks if somebody Wins the game
		boolean isWinner = false;
		
		if ((blackCounter > 0) && (whiteCounter > 0)) {
			isWinner = false; 		// Each color has formed tiles. 
			winner = Counter.EMPTY; // Nobody win.
		}
		else {
			isWinner = true;
			if ((blackCounter > 0) && (whiteCounter == 0)){
				winner = Counter.BLACK;
			}
			else if ((blackCounter == 0) && (whiteCounter > 0)){
				winner = Counter.WHITE;
			}
		}
		
		return isWinner;
	}
	
	
	/**************************************/
	/************ EXTRA METHODS ***********/
	/**************************************/
	
	// Calculates how much Horizontal tiles of a color has been made.
	
	public void checkHorizontal(Board board) {
		int tilesCounter, y, x;
		Counter counter, nextCounter;
		
		y = dimY; // Starts at bottom
		
		while(y >= 1) 
		{	
			tilesCounter = 1; // Reset counter
			x = 1; // Starts at first position
			counter = board.getPosition(x, y); // Color of first cell on each iteration
			
			while (x < dimX)
			{
				nextCounter = board.getPosition(x + 1, y);
				
				if ((counter == nextCounter) && (counter != Counter.EMPTY)) {
					tilesCounter++;
					if (tilesCounter == Resources.TILES_TO_WIN) {
						if (counter.equals(Counter.BLACK)){
							blackCounter++;
						}
						else if (counter.equals(Counter.WHITE)){
							whiteCounter++;
						}
						tilesCounter = 1;
					}
				}
				else {
					tilesCounter = 1;
					counter = board.getPosition(x + 1, y); // next Cell color
				}		
				
				x++; // Go to right
			}			
			y--; // Decrease the row (from bottom to top)
		}
	}
	
	public void checkVertical(Board board) {
		int tilesCounter, y, x;
		Counter counter, nextCounter; 
		
		x = 1;
		
		while(x <= dimX) 
		{
			tilesCounter = 1; // Reset counter
			y = board.getHeight(); // Start at bottom
			counter = board.getPosition(x, y); // Color of first cell on each iteration
			
			while(y > 1)  
			{
				nextCounter = board.getPosition(x, y - 1); // take the color of row before
				
				if ((counter == nextCounter) && (counter != Counter.EMPTY)) {
					tilesCounter++;
					if (tilesCounter == Resources.TILES_TO_WIN) {
						if (counter.equals(Counter.BLACK)){
							blackCounter++;
						}
						else if (counter.equals(Counter.WHITE)){
							whiteCounter++;
						}
						tilesCounter = 1;
					}
				}
				else {
					tilesCounter = 1;
					counter = board.getPosition(x, y - 1); // next Cell color
				}		
				
				y--;
			}			
			x++;
		}
	}
	
	public void checkDiagonal1(Board board) {
		int y, x, tilesCounter, aux_Y, aux_X, numIterations;
		Counter color, nextColor; 
		
		// starting bottom left position
		// Checks diagonals until the first cell (1,1)

		x = 1; // Always start in the first column
		y = 1; // Always start in the last row 
		numIterations = y;
		
		while (y <= dimY) {
			x = 1;
			aux_Y = y;
			tilesCounter = 1;
			
			if (numIterations > dimX) //it was with get width so it has to be simX
			{
				numIterations = dimX;
			}
			
			while (x < numIterations) {
				color = board.getPosition(x, aux_Y);
				nextColor = board.getPosition(x + 1, aux_Y - 1);
				
				if ((color == nextColor) && (color != Counter.EMPTY)) {
					tilesCounter++;
					if (tilesCounter == Resources.TILES_TO_WIN) {
						if (color.equals(Counter.BLACK)){
							blackCounter++;
						}
						else if (color.equals(Counter.WHITE)){
							whiteCounter++;
						}
						tilesCounter = 1;
					}
				}
				else
				{
					tilesCounter = 1;
				}	
				aux_Y--;
				x++;
			}			
			y++;
			numIterations++;
		}
		
			// starting at (height, height) ex: (5,5)
			// Checks from bottom to top right
	 
			y = dimY; // Always start in the last row
			x = dimX; // Always start in the first column; pero aqui ponia getHeight no Width
			color = board.getPosition(x, y);
			int counter = 1;
			
			while (x > 1) {
				y = dimY;
				aux_X = x;
				tilesCounter = 1;
				numIterations = dimX - x + 1;
				if (numIterations > dimY)
				{
					numIterations = dimY;
				}
				counter = 1;
				while (counter < numIterations){
					color = board.getPosition(aux_X, y);
					nextColor = board.getPosition(aux_X + 1, y - 1);
					
					if ((color == nextColor) && (color != Counter.EMPTY)) {
						tilesCounter++;
						if (tilesCounter == Resources.TILES_TO_WIN) {
							if (color.equals(Counter.BLACK)){
								blackCounter++;
							}
							else if (color.equals(Counter.WHITE)){
								whiteCounter++;
							}
							tilesCounter = 1;
						}
					}
					else
					{
						tilesCounter = 1;
					}	
					y--;
					aux_X++;
					
					counter++;
				}			
				x--;
			}			

	}
	
	public void checkDiagonal2(Board board) {
		int y, x, tilesCounter, aux_X, aux_Y, numIterations;
		Counter color, nextColor;
		
		y = 1; // Always start in the firt row
		x = dimX; // Always start in the last column
		color = board.getPosition(x, y);
		numIterations = 1;
		// starting top right position
		// Checks until the first diagonal
		
		while (x > 1){
			y = 1;
			aux_X = x;
			tilesCounter = 1;
			if (numIterations > Resources.DIMY_COMPLICA)
			{
				numIterations = Resources.DIMY_COMPLICA;
			}
			
			while (y < numIterations) { // o aqui
				color = board.getPosition(aux_X, y);
				nextColor = board.getPosition(aux_X + 1, y + 1);
				
				if ((color == nextColor) && (color != Counter.EMPTY)) {
					tilesCounter++;
					if (tilesCounter == Resources.TILES_TO_WIN) {
						if (color.equals(Counter.BLACK)){
							blackCounter++;
						}
						else if (color.equals(Counter.WHITE)){
							whiteCounter++;
						}
						tilesCounter = 1;
					}
				}
				else
				{
					tilesCounter = 1;
				}	
				y++;
				aux_X++;
			}			
			x--;
			numIterations++;
		}
			// starting at (height, 1)
			// Checks diagonals to bottom

			x = 1; // Always start in the first column
			y = Resources.DIMY_COMPLICA; // Always start in the firt row
			color = board.getPosition(x, y);
			
			while (y >= 1) {
				x = 1;
				aux_Y = y;
				tilesCounter = 1;
				numIterations = Resources.DIMY_COMPLICA - y + 1;//antes era width y sin el +1
				if (numIterations > Resources.DIMX_COMPLICA)
				{
					numIterations = Resources.DIMX_COMPLICA;
				}
				
				while ((x < numIterations)) {// && !(isWinner)
					color = board.getPosition(x, aux_Y);
					nextColor = board.getPosition(x + 1, aux_Y + 1);
					
					if ((color == nextColor) && (color != Counter.EMPTY)) {
						tilesCounter++;
						if (tilesCounter == Resources.TILES_TO_WIN) {
							if (color.equals(Counter.BLACK)){
								blackCounter++;
							}
							else if (color.equals(Counter.WHITE)){
								whiteCounter++;
							}
							tilesCounter = 1;
						}
					}
					else
					{
						tilesCounter = 1;
					}	
					x++;
					aux_Y++;
				}			
				y--;
			}			
	}

	
}
