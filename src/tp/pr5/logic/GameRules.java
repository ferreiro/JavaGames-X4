package tp.pr5.logic;

import tp.pr5.Resources.Counter;
  
public interface GameRules {
		
	public Board newBoard(); // Build a board that is to be used in the game, according to the rules of that game.
	public Counter initialPlayer();	// Says which is the firt player to move

	// Getters and setters 
	public int getDimX();	// Get with of the Board.
	public int getDimY();	// Get height of the Board.
	public int intRules();	// 0 = Connect, 1 = Complica, 2 = Gravity, 3 = Reversi

	// Other methods
	public boolean isDraw(Counter lastMove, Board b); // Consulta si hay empate. tablas(Ficha ultimoEnPoner, Tablero t) 
	public Counter winningMove(Move lastMove, Board b); // Checks whether or not, with the current board, one of the players has won and, if so, returns the colour of the winner
	public Counter nextTurn(Counter lastMove, Board b); // Returns the colour of the player whose turn it is.
	
}
