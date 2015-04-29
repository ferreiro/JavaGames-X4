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
	public boolean executeMove(Board b) throws InvalidMove {
		boolean valid = false;
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && 
			(row >= 1 && row <= Resources.DIMY_REVERSI) 	  && 
			(b.getPosition(column, row) == Counter.EMPTY)) { 
				
				// Un movimiento es válido si puede revertir alguna celda formada
				// Y además las posiciones estÃ¡n dentro del tablero y es una celda vacÃ­a
				
				checkHorizontal(b, column, row, true); 	// True  = Left
				checkHorizontal(b, column, row, false); // False = Right
				checkVertical(b, column, row, true); 	// True  = UP	
				checkVertical(b, column, row, false); 	// False = Down	
				checkDiagonal(b, column, row, true); 	// True = Top Left
				checkDiagonal(b, column, row, false); 	// False = Bottom Right

				if (listCoordinates.size() >= 1) { // Algún movimiento se ha podido hacer y guardar
					valid = true;
					System.out.println("Yeah! Some tiles are moved");
					
					for (int i = 0; i < listCoordinates.size(); i++) {
						swapCells(b, column, row, listCoordinates.get(i)); // setear el tablero
					}
				}
				else {
					System.out.println("NO! There are no tiles formed");
				}
				
				// mirar si hay algÃºn elemento en el vector swappedcoorxinstes
				// => si hay alguna coordenada, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
				//     Si es valido, recorrer cada coordenada, restarla a la coordenada de origen y estese el tBlero del color contrario
				//     Si no es valido, devolver el movimiento como no valido			
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
			System.out.println("It's a Diagonal move");
			iterations = diagonalIterations(column, row, moveColumn, moveRow);

			if (moveRow < row) { // El movimiento está a la derecha de las columnas a mover ([][]][] <= Movimiento)
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column - i, row - i, color);
				}
			}
			else if (moveRow > row) {	// El movimiento está a la izquierda de las columnas a mover (Movimiento => [][]][])
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column + i, row + i, color);
				}
			} 
		}
		
	}
	
	public int absoluteValue(int a, int b) {
		int total = a - b;
		if (total < 0) total *= -1; // Change to positive value
		return total;
	}
	
	public int diagonalIterations(int column, int row, int moveColumn, int moveRow) {
		int total = 0;
		
		/*
		if (column > moveColumn) {
			// El movimiento que vamos a poner está más a la derecha (es más grande) que el movinento de destino
			
			for (int c = moveColumn; c <= column; c++) {
				if (row > moveRow) {
					for (int r = moveRow; r <= row; r++) {
						total++;					
					}
				}
				else {
					for (int r = row; r <= moveRow; moveRow++) {
						total++;					
					}
				}
			}
			
		}
		else {
			// El movimiento de destino está más a la derecha que el movinento que vamos a poner
			for (int c = column; c <= moveColumn; c++) {
				if (row > moveRow) {
					for (int r = moveRow; r <= row; r++) {
						total++;					
					}
				}
				else {
					for (int r = row; r <= moveRow; moveRow++) {
						total++;					
					}
				}
			}
		}
		*/
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
				SwappedMove s = new SwappedMove(auxColumn, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else {
			while((auxColumn <= b.getWidth()) && (b.getPosition(auxColumn + 1, y) == nextColor)) {
				auxColumn++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, y) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, y, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
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
				SwappedMove s = new SwappedMove(x, auxRow, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else {
			while((auxRow <= b.getHeight()) && (b.getPosition(x, auxRow + 1) == nextColor)) {
				auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(x, auxRow + 1) == color)) {
				SwappedMove s = new SwappedMove(x, auxRow, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}
	
	// CheckDiagonal: Function to Check diagonals given a fixed position
	
	public void checkDiagonal(Board b, int x, int y, boolean upLeft) {
		int total = 0, auxColumn = x, auxRow = y;
		Counter color = currentPlayer, nextColor = changeColor(color);
		
		if (upLeft) {
			while((auxColumn >= 1) && (auxRow >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == nextColor)) {
				auxColumn--; auxRow--; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
		else { // Down Right
			while((auxColumn <= b.getWidth()) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) == nextColor)) {
				auxColumn++; auxRow++; total++;
			}
			if (total >= 1 && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				SwappedMove s = new SwappedMove(auxColumn, auxRow, nextColor ); 	// Crea movimiento con la posición de la última ficha que vamos a swappear
				listCoordinates.add(s);									// Guardar movimiento swappeado en la lista de coordenadas
			}
		}
	}
	
	@Override
	public void undo(Board b) {
		// TODO UNDO
	}

	@Override
	public int getColumn() {
		return this.column;
	}
	public int getRow() {
		return this.row;
	}

}
