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
				// Y además las posiciones están dentro del tablero y es una celda vacía
				
				checkHorizontal(b, column, row, true); // True = left Part
				checkHorizontal(b, column, row, false); // False = right part
				checkVertical(b, column, row, true); // True = Up part
				checkVertical(b, column, row, false); // True = Up part	
				checkDiagonal(b, column, row, true); // True = topLeft
				checkDiagonal(b, column, row, false); // False = Bottom Right

				// mirar si hay algún elemento en el vector swappedcoorxinstes
				// => si hay alguna coordenada, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
				//     Si es valido, recorrer cada coordenada, restarla a la coordenada de origen y estese el tBlero del color contrario
				//     Si no es valido, devolver el movimiento como no valido
				
				if (listCoordinates.size() >= 1) {
					valid = true;
					System.out.println("Yeah! Some tiles are moved");
					
					for (int i = 0; i < listCoordinates.size(); i++) {
						swapCells(b, column, row, currentPlayer, listCoordinates.get(i));
					}
					
					
					
					/*
					b.setPosition(x, y, color); // poner la celda con el color del jugador
					for (int i = 0; i < total; i++) {
						b.setPosition(auxColumn + i, y, color); // swap color on cells
					}
					*/
					// TODO: SETEAR EL TABLERO
					
				}
				else {
					System.out.println("NO! There are no tiles formed");
					valid = false;
				}
				
		}	 		
		return valid;
	}
	
	public void swapCells(Board b, int x, int y, Counter c, SwappedMove destiny) {
		// TODO: Swap the cells into the color given

		int originX = x,
			originY = y,
			destinyX = destiny.getX(),
			destinyY = destiny.getY();
		Counter newColor = changeColor(c);
		
		if (newColor != Counter.EMPTY) {

			// Checks if the move is horizontal, vertical o diagonal.

			if (((originY - destinyY) == 0) && (true)) {
				// Horizontal
				float columnGaps = destinyX - originX; // �Cuantos bloques hay desde el contador a la ficha?
				
				if (columnGaps >= 0) {
					// Mover a la derecha
					for (int i = 0; i < columnGaps; i++) {
						b.setPosition(x + i, y, newColor);
					}
				}
				else {
					// Mover a la izquierda
					for (int i = 0; i < columnGaps; i++) {
						b.setPosition(x - i, y, newColor);
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
	public Counter changeColor(Counter c) {
		if (c == Counter.WHITE) return Counter.BLACK;
		else if(c == Counter.BLACK) return Counter.WHITE;
		else return Counter.EMPTY;
	}
	

	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public void checkHorizontal(Board b, int x, int y, boolean left) {
		int auxColumn = x, total = 0;
		Counter color = b.getPosition(x, y);
		boolean valid = false;
 
		if (left) {
			while((auxColumn > 1) && (b.getPosition(auxColumn - 1, y) != color)) {
				total += 1; auxColumn -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn - 1, y) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (b.getPosition(auxColumn + 1, y) != color)) {
				total += 1; auxColumn += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, y) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.
			}			
		} 
		
		if (valid) {
			// Crear movimiento swappeado y guardarlo en la lista de coordenadas
			SwappedMove s = new SwappedMove();
			s.setX(auxColumn);
			s.setY(y);
			listCoordinates.add(s);				
		}
	}

	// CheckVertical: Function to Check top and bottom colors given a fixed position
	
	public void checkVertical(Board b, int x, int y, boolean up) {
 		int auxRow = y, total = 0;
		Counter color = b.getPosition(x, y);
		boolean valid = false;
		 
		if (up) {
			while((auxRow > 1) && (b.getPosition(x, auxRow - 1) != color)) {
				total += 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow - 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.
			}
		} 
		else {
			while((auxRow < b.getHeight()) && (b.getPosition(x, auxRow + 1) != color)) {
				total += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow + 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.
			}			
		}  
		
		if (valid) {
			// Crear movimiento swappeado y guardarlo en la lista de coordenadas
			SwappedMove s = new SwappedMove();
			s.setX(x);
			s.setY(auxRow);
			listCoordinates.add(s);				
		}
	}
	
	// CheckDiagonal: Function to Check diagonals given a fixed position
	
	public void checkDiagonal(Board b, int x, int y, boolean topLeft) {
 		int auxColumn = x, auxRow = x, total = 0;
		Counter color = b.getPosition(x, y);
		boolean valid = false;
 
		if (topLeft) {
			while((auxColumn > 1) && (auxRow > 1) && (b.getPosition(auxColumn - 1, auxRow - 1) != color)) {
				total += 1; auxColumn -= 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.				
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (auxRow < b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) != color)) {
				total += 1; auxColumn += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.			
			}			
		} 
		
		if (valid) {
			// Crear movimiento swappeado y guardarlo en la lista de coordenadas
			SwappedMove s = new SwappedMove();
			s.setX(auxColumn);
			s.setY(auxRow);
			listCoordinates.add(s);				
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
