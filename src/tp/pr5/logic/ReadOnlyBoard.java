package tp.pr4.logic;

public interface ReadOnlyBoard {

	// Accessor method which returns the width of the board.
	int	getWidth();
	
	// 	Accessor method which returns the height of the board.
	int	getHeight();
	
	// 	Accessor method which returns the state of a particular position on the board (i.e.
	Counter	getPosition(int x, int y);
	
}
