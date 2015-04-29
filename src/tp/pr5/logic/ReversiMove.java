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

				// mirar si hay algÃºn elemento en el vector swappedcoorxinstes
				// => si hay alguna coordenada, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
				//     Si es valido, recorrer cada coordenada, restarla a la coordenada de origen y estese el tBlero del color contrario
				//     Si no es valido, devolver el movimiento como no valido
				
				if (listCoordinates.size() >= 1) { // Algún movimiento se ha podido hacer y guardar
					valid = true;
					System.out.println("Yeah! Some tiles are moved");
					
					for (int i = 0; i < listCoordinates.size(); i++) {
						swapCells(b, column, row, listCoordinates.get(i)); // setear el tablero
					}
				}
				else {
					System.out.println("NO! There are no tiles formed");
					valid = false;
				}
				
		}	 		
		return valid;
	}
	
	public void swapCells(Board b, int column, int row, SwappedMove m) {
		int moveColumn = m.getX(), moveRow = m.getY(), iterations = 0;
		Counter color = changeColor(m.getColor()); // El color de origen es el de los counters que envolvían a las fichas
		
		if (row == moveRow) {	// Horizontal
			iterations = absoluteValue(column, moveColumn);
			if (moveColumn > column) {	// El número está por debajo de la ficha del movimiento
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column + i, row, color);
				}
			}
			else if (moveColumn < column) {
				for (int i = 0; i <= iterations; i++) {
					b.setPosition(column - i, row, color);
				}
			}
		}
		else {
			
		}
		
	}
	
	public int absoluteValue(int a, int b) {
		int total = a - b;
		if (total < 0) total *= -1; // Change to positive value
		return total;
	}
	
	/*
	public void swapCells(Board b, int x, int y, SwappedMove m) {
		// TODO: Swap the cells into the color given

		int originX = x,
			originY = y,
			destinyX = m.getX(),
			destinyY = m.getY();
		Counter c = m.getColor();
		
		if (c != Counter.EMPTY) {

			// Checks if the move is horizontal, vertical o diagonal.

			if (((originY - destinyY) == 0) && (true)) {
				// Horizontal
				int columnGaps = destinyX - originX; // ¿Cuantos bloques hay desde el contador a la ficha?
				
				if (columnGaps >= 0) {
					// Mover a la derecha
					b.setPosition(x, y, changeColor(c)); // Poner la celda del movimiento
					for (int i = 1; i <= columnGaps; i++) {
						b.setPosition(x + i, y, changeColor(c)); // Cambiar color de la celda de dentro
					}
				}
				else {
					// Mover a la izquierda
					for (int i = 1; i <= columnGaps; i++) {
						b.setPosition(x - i, y, changeColor(c)); 
					}
				}
				System.out.println("Horizontal");
				
			}
			else if ((originX - destinyX) == 0 && (true)) {
				// Vertical
				System.out.println("Vertical");
			}
			else {
				// Diagonal
				System.out.println("Diagonal");
			}
		}
		
			
	}
	*/
	
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
	public void undo(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getColumn() {
		return this.column;
	}
	public int getRow() {
		return this.row;
	}

}
