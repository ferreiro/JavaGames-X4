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
		int total = 0;
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && 
			(row >= 1 && row <= Resources.DIMY_REVERSI) 	  && 
			(b.getPosition(column, row) == Counter.EMPTY)) { 
			
			boolean keepMove = true;
			
			total += checkHorizontal(b, column, row, true, keepMove);	// True  = Left
			total += checkHorizontal(b, column, row, false, keepMove);	// False = Right
			total += checkVertical(b, column, row, true, keepMove); 	// True  = UP	
			total += checkVertical(b, column, row, false, keepMove); 	// False = Down	
			total += checkDiagonal1(b, column, row, true, keepMove); 	// True = Top Left
			total += checkDiagonal1(b, column, row, false, keepMove);  // False = Bottom Right
			total += checkDiagonal2(b, column, row, true, keepMove); 	// True = Top Right
			total += checkDiagonal2(b, column, row, false, keepMove);  // False = Bottom Left

			if (total >= 1) { // => Si se ha formado al menos una check, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
				valid = true;	
				for (int i = 0; i < listCoordinates.size(); i++) {
					swapCells(b, column, row, listCoordinates.get(i)); // setear el tablero
				}
			}	
		}	 
		return valid;
	}
	
	// Comprobar también si no se puede hacer algún movimiento en cualquier celda vacía

	public boolean setAvailableEmpty(Board b) {
		int c = 1, r = 1, total = 0;
		boolean valid = false; 
		
		while(r <= b.getHeight() && !valid) {
			c = 1;
			while(c <= b.getWidth() && !valid) {
				if (b.getPosition(c, r) == Counter.EMPTY) {

					total = 0;
					boolean keepMove = false; // Aquí no queremos guardar las direcciones (porque solo comprobamos si es posible los movimientos)
					
					total += checkHorizontal(b, c, r, true, keepMove);	// True  = Left
					total += checkHorizontal(b, c, r, false, keepMove);	// False = Right
					total += checkVertical(b, c, r, true, keepMove); 	// True  = UP	
					total += checkVertical(b, c, r, false, keepMove); 	// False = Down	
					total += checkDiagonal1(b, c, r, true, keepMove); 	// True = Top Left
					total += checkDiagonal1(b, c, r, false, keepMove);  // False = Bottom Right
					total += checkDiagonal2(b, c, r, true, keepMove); 	// True = Top Right
					total += checkDiagonal2(b, c, r, false, keepMove);  // False = Bottom Left
					
					if (total >= 1) { // => Si se ha formado al menos una check, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
						valid = true;		
					}	
				}
				c++;
			}
			r++;
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
			else if (moveRow > row && column < moveColumn) {	// TopLeft to BottomRight // El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
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
	 
	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public int checkHorizontal(Board b, int x, int y, boolean left, boolean keepMove) {
		boolean isFormed = false;
		int accumulateTiles = 0, auxColumn = x;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (left) {
			while((auxColumn >= 1) && (b.getPosition(auxColumn - 1, y) == nextColor)) {
				auxColumn--; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(auxColumn - 1, y) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, y, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}
			}
		}
		else {
			while((auxColumn <= b.getWidth()) && (b.getPosition(auxColumn + 1, y) == nextColor)) {
				auxColumn++; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(auxColumn + 1, y) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, y, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}									
			}
		}
		
		if (isFormed) 
			return 1; // Sumar uno, porque se ha formado celdas
		else 		  
			return 0;	
	}

	// CheckVertical: Function to Check top and bottom colors given a fixed position
	
	public int checkVertical(Board b, int x, int y, boolean up, boolean keepMove) {
		boolean isFormed = false;
		int accumulateTiles = 0, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (up) {
			while((auxRow >= 1) && (b.getPosition(x, auxRow - 1) == nextColor)) {
				auxRow--; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(x, auxRow - 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(x, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}								
			}
		}
		else {
			while((auxRow <= b.getHeight()) && (b.getPosition(x, auxRow + 1) == nextColor)) {
				auxRow++; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(x, auxRow + 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(x, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}								
			}
		}
		
		if (isFormed) 
			return 1; // Sumar uno, porque se ha formado celdas
		else 		  
			return 0;
	}

	// CheckDiagonal1: Function to Check diagonals given a fixed position
	
	public int checkDiagonal1(Board b, int x, int y, boolean upLeft, boolean keepMove) {
		boolean isFormed = false;
		int accumulateTiles = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upLeft) {
			while((auxColumn >= 1) && (auxRow >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == nextColor)) {
				auxColumn--; auxRow--; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}										
			}
		}
		else { // Down Right
			while((auxColumn <= b.getWidth()) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) == nextColor)) {
				auxColumn++; auxRow++; accumulateTiles++;
			}
			if (accumulateTiles >= 1 && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}										
			}
		}
		
		if (isFormed) 
			return 1; // Sumar uno, porque se ha formado celdas
		else 		  
			return 0;		
	}

	// CheckDiagonal2: Function to Check diagonals given a fixed position
	
	public int checkDiagonal2(Board b, int x, int y, boolean upRight, boolean keepMove) {
		boolean isFormed = false;
		int accumulateTitles = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upRight) {
			while((auxColumn <= b.getWidth()) && (auxRow >= 1) && (b.getPosition(auxColumn + 1, auxRow - 1) == nextColor)) {
				auxColumn++; auxRow--; accumulateTitles++;
			}
			if (accumulateTitles >= 1 && (b.getPosition(auxColumn + 1, auxRow - 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}									
			}
		}
		else { // Bottom Left
			while((auxColumn >= 1) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn - 1, auxRow + 1) == nextColor)) {
				auxColumn--; auxRow++; accumulateTitles++;
			}
			if (accumulateTitles >= 1 && (b.getPosition(auxColumn - 1, auxRow + 1) == color)) {
				isFormed = true;
				SwappedMove s = new SwappedMove(auxColumn, auxRow, x, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				if(keepMove) {
					listCoordinates.add(s);		// Guardar movimiento swappeado en la lista de coordenadas
				}								
			}
		}
		
		if (isFormed) 
			return 1; // Sumar uno, porque se ha formado celdas
		else 		  
			return 0;	
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
	
	public int getListLength(){
		return listCoordinates.size();
	}

	@Override
	public int getColumn() {
		return this.column;
	}
	public int getRow() {
		return this.row;
	}
	
	public Counter changeColor(Counter c) {
		if (c == Counter.WHITE) return Counter.BLACK;
		else if(c == Counter.BLACK) return Counter.WHITE;
		else return Counter.EMPTY;
	}
	 
	
}
