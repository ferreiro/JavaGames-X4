package tp.pr5.logic;

import java.util.ArrayList;

import tp.pr5.Resources.Resources;

public class ReversiMove extends Move {
    private ArrayList<SwappedMove> listCoordinates;

	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		super(moveColour, moveColumn);
		super.row = moveRow;
		listCoordinates = new ArrayList<SwappedMove>();		
	}

	@Override
	
	// Un movimiento es válido si puede revertir alguna celda formada
	// Y además las posiciones estÃ¡n dentro del tablero y es una celda vacÃ­a
	
	public boolean executeMove(Board b) throws InvalidMove {
		boolean valid = false;
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && 
			(row >= 1 && row <= Resources.DIMY_REVERSI) 	  && 
			(b.getPosition(column, row) == Counter.EMPTY)) { 
				
			checkHorizontal(b, column, row, true);	// True  = Left
			checkHorizontal(b, column, row, false);	// False = Right
			checkVertical(b, column, row, true); 	// True  = UP	
			checkVertical(b, column, row, false); 	// False = Down	
			checkDiagonal1(b, column, row, true); 	// True = Top Left
			checkDiagonal1(b, column, row, false);  // False = Bottom Right
			checkDiagonal2(b, column, row, true); 	// True = Top Right
			checkDiagonal2(b, column, row, false);  // False = Bottom Left

			if (listCoordinates.size() >= 1) { // => si hay alguna coordenada, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
				valid = true;	
				for (int i = 0; i < listCoordinates.size(); i++) {
					swapCells(b, column, row, listCoordinates.get(i)); // setear el tablero
				}
			}	
		}	 	
		
		/*
		 * if (!valid) {
			// Comprobar también si no se puede hacer algún movimiento en cualquier celda vacía
			valid =  !validEmpty(b); // Algún movimiento se va a poder hacer, así que no es valido
		}
		*/
		
		return valid;
	}
	
	public boolean validEmpty(Board b) {
		boolean valid = false; 
		
		valid = checkHorizontalEmpty(b, column, row, true);
		
		if (!valid) { 
			valid = checkHorizontalEmpty(b, column, row, false);
			if (!valid) {
				valid = checkVerticalEmpty(b, column, row, false);
				if (!valid) {
					valid = checkVerticalEmpty(b, column, row, true);
					if (!valid) {
						valid = checkDiagonal1Empty(b, column, row, false);
						if (!valid) {
							valid = checkDiagonal1Empty(b, column, row, true);
							if (!valid) {
								valid = checkDiagonal2Empty(b, column, row, false);
								if (!valid) {
									valid = checkDiagonal2Empty(b, column, row, true);
								}
							}
						}
					}
				}
			}
		}
		
		return valid;
	}
	
	public void swapCells(Board b, int column, int row, SwappedMove m) {
		int moveColumn = m.getX(), moveRow = m.getY(), iterations = 0;
		Counter color = changeColor(m.getColor()); // El color de origen es el de los counters que envolvían a las fichas
				
		if (row == moveRow) {	// Horizontal
			iterations = absoluteValue(column, moveColumn);
			if (moveColumn < column) { // El movimiento está a la derecha de las columnas a mover ([][]][] <= Movimiento)
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column - i, row, color);
				}
			}
			else if (moveColumn > column) {	// El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column + i, row, color);
				}
			} 
		}
		else if (column == moveColumn) { // Vertical
			iterations = absoluteValue(row, moveRow);
			System.out.println("It's a vertical move");
			if (moveRow < row) { // El movimiento está a la derecha de las columnas a mover ([][]][] <= Movimiento)
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column, row - i, color);
				}
			}
			else if (moveRow > row) {	// El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column, row + i, color);
				}
			} 
		}
		else {	// Diagonal
			iterations = absoluteValue(row, moveRow);

			if (moveRow < row && moveColumn < column) { // Bottom Right to TopLeft // El movimiento está a la derecha de las columnas a mover ([][]][] <= Movimiento)
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column - i, row - i, color);
				}
			}
			else if (moveRow > row && column < moveColumn) {	// TopRight to BottomLeft // El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column + i, row + i, color);
				}
			} 
			else if (row < moveRow && column > moveColumn) { // TopRight to BottomLeft // El movimiento está a la derecha de las columnas a mover ([][]][] <= Movimiento)
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column - i, row + i, color);
				}
			}
			else if (row > moveRow && column < moveColumn) {	// BottomLeft to TopRight // El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column + i, row - i, color);
				}
			} 
		}
		
	}
	
	public int absoluteValue(int a, int b) {
		int total = a - b;
		if (total < 0) total *= -1; // Change to positive value
		return total;
	}
	 
	public Counter changeColor(Counter c) {
		if (c == Counter.WHITE) return Counter.BLACK;
		else if(c == Counter.BLACK) return Counter.WHITE;
		else return Counter.EMPTY;
	}
	
	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public void checkHorizontal(Board b, int x, int y, boolean left) {
		int total = 0, auxColumn = x;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (left) {
			while((auxColumn >= 1) && (b.getPosition(auxColumn - 1, y) == nextColor)) {
				auxColumn--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, y) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, y, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else {
			while((auxColumn <= b.getWidth()) && (b.getPosition(auxColumn + 1, y) == nextColor)) {
				auxColumn++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, y) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, y, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);										// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}

	// CheckVertical: Function to Check top and bottom colors given a fixed position
	
	public void checkVertical(Board b, int x, int y, boolean up) {
		int total = 0, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (up) {
			while((auxRow >= 1) && (b.getPosition(x, auxRow - 1) == nextColor)) {
				auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(x, auxRow - 1) == color)) {
				SwappedMove s = new SwappedMove(x, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else {
			while((auxRow <= b.getHeight()) && (b.getPosition(x, auxRow + 1) == nextColor)) {
				auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(x, auxRow + 1) == color)) {
				SwappedMove s = new SwappedMove(x, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}

	// CheckDiagonal1: Function to Check diagonals given a fixed position
	
	public void checkDiagonal1(Board b, int x, int y, boolean upLeft) {
		int total = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upLeft) {
			while((auxColumn >= 1) && (auxRow >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == nextColor)) {
				auxColumn--; auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else { // Down Right
			while((auxColumn <= b.getWidth()) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) == nextColor)) {
				auxColumn++; auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}

	// CheckDiagonal2: Function to Check diagonals given a fixed position
	
	public void checkDiagonal2(Board b, int x, int y, boolean upRight) {
		int total = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upRight) {
			while((auxColumn <= b.getWidth()) && (auxRow >= 1) && (b.getPosition(auxColumn + 1, auxRow - 1) == nextColor)) {
				auxColumn++; auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, auxRow - 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else { // Bottom Left
			while((auxColumn >= 1) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn - 1, auxRow + 1) == nextColor)) {
				auxColumn--; auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, auxRow + 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}
	
	
	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public boolean checkHorizontalEmpty(Board b, int x, int y, boolean left) {
		boolean valid = false;
		int total = 0, auxColumn = x;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (left) {
			while((auxColumn >= 1) && (b.getPosition(auxColumn - 1, y) == nextColor)) {
				auxColumn--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, y) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		else {
			while((auxColumn <= b.getWidth()) && (b.getPosition(auxColumn + 1, y) == nextColor)) {
				auxColumn++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, y) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		
		return valid;
	}

	// CheckVertical: Function to Check top and bottom colors given a fixed position
	
	public boolean checkVerticalEmpty(Board b, int x, int y, boolean up) {
		boolean valid = false;
		int total = 0, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (up) {
			while((auxRow >= 1) && (b.getPosition(x, auxRow - 1) == nextColor)) {
				auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(x, auxRow - 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		else {
			while((auxRow <= b.getHeight()) && (b.getPosition(x, auxRow + 1) == nextColor)) {
				auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(x, auxRow + 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		return valid;
	}

	// CheckDiagonal1: Function to Check diagonals given a fixed position
	
	public boolean checkDiagonal1Empty(Board b, int x, int y, boolean upLeft) {
		boolean valid = false;
		int total = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upLeft) {
			while((auxColumn >= 1) && (auxRow >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == nextColor)) {
				auxColumn--; auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		else { // Down Right
			while((auxColumn <= b.getWidth()) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) == nextColor)) {
				auxColumn++; auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		
		return valid;
	}

	// CheckDiagonal2: Function to Check diagonals given a fixed position
	
	public boolean checkDiagonal2Empty(Board b, int x, int y, boolean upRight) {
		boolean valid = false;
		int total = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upRight) {
			while((auxColumn <= b.getWidth()) && (auxRow >= 1) && (b.getPosition(auxColumn + 1, auxRow - 1) == nextColor)) {
				auxColumn++; auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, auxRow - 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		else { // Bottom Left
			while((auxColumn >= 1) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn - 1, auxRow + 1) == nextColor)) {
				auxColumn--; auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, auxRow + 1) == color)) {
				valid = true; // El movimiento es válido
			}
		}
		
		return valid;
	}
	
	@Override
	public void undo(Board b) {
		if (listCoordinates.size() >= 1) {
			for (int i = 0; i < listCoordinates.size(); i++) {
				SwappedMove m = listCoordinates.get(i);
				m.setColor(changeColor(m.getColor()));
				// Setear en la posición del movimiento EMPTY
				swapCells(b, column, row, m); // setear el tablero
				b.setPosition(m.getOrigenX(), m.getOrigenY(), Counter.EMPTY);
			}
		}
		
	}

	@Override
	public int getColumn() {
		return this.column;
	}
	public int getRow() {
		return this.row;
	}

}
