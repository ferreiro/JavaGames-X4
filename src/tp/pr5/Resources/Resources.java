 package tp.pr5.Resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game; 
import tp.pr5.logic.GravityMove;

public class Resources {
	
	// Global Variables and Constants 
	
	public static int 
		DIMX_GRAVITY = 10, DIMY_GRAVITY = 10; // Is not final, cause we can modify it on the program
	
	public static final String 
		RESOURCES_URL = "src/icons/";
	
	public static final int 
		MAX_STACK = 100,
		TILES_TO_WIN = 4,
		DIMX_CONNECT4 = 7, DIMY_CONNECT4 = 6,
		DIMX_COMPLICA = 4, DIMY_COMPLICA = 7,
		DIMX_REVERSI = 8, DIMY_REVERSI = 8;
	
	
	// Comprueba si alguna de las celdas empty, con el color del jugador actual, forman un posible movimiento

	public static boolean canMakeMove(Board b, Counter color) { // como se usa desde main y se usa una read only board no usar ningun metodo que sea para cambiar la tabla
		int column = 1, row = 1, total = 0;
		boolean valid = false; 
		
		while(column <= b.getWidth() && !valid) {
			row = 1;
			while(row <= b.getHeight() && !valid) {
				if (b.getPosition(column, row) == Counter.EMPTY) {
					total = 0;
					total += checkHorizontal(b, color, column, row);	// True  = Left
					total += checkVertical(b, color, column, row); 	// False = Down	
					total += checkDiagonal1(b, color, column, row);  // False = Bottom Right
					total += checkDiagonal2(b, color, column, row);  // False = Bottom Left

					if (total >= 1) valid = true;				
				}
				row++;
			}
			column++;
		}
		return valid;		
	}

	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public static int checkHorizontal(Board b, Counter color, int x, int y) {
		int total = 0, accumulateTiles = 0, auxColumn = x;
		Counter nextColor = changeColor(color);
		 
		// Check Left tiles
		while((auxColumn >= 1) && (b.getPosition(auxColumn - 1, y) == nextColor)) {
			auxColumn--; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(auxColumn - 1, y) == color)) {
			total++;
		} 
		// Check Right tiles
		auxColumn = x;
		while((auxColumn <= b.getWidth()) && (b.getPosition(auxColumn + 1, y) == nextColor)) {
			auxColumn++; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(auxColumn + 1, y) == color)) {
			total++;
		} 
		
		return total; // Sumar el número total de celdas válidas (formadas) 	
	}
 
	// CheckVertical: Function to Check top and bottom colors given a fixed position
	
	public static int checkVertical(Board b, Counter color, int x, int y) {
		int total = 0, accumulateTiles = 0, auxRow = y;
		Counter nextColor = changeColor(color);
		 
		// Check up Tiles
		while((auxRow >= 1) && (b.getPosition(x, auxRow - 1) == nextColor)) {
			auxRow--; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(x, auxRow - 1) == color)) {
			total++;
		} 
		// Check Down tiles
		auxRow = y;
		while((auxRow <= b.getHeight()) && (b.getPosition(x, auxRow + 1) == nextColor)) {
			auxRow++; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(x, auxRow + 1) == color)) {
			total++;
		} 
		
		return total;
	}

	// CheckDiagonal1: Function to Check diagonals given a fixed position
	
	public static int checkDiagonal1(Board b, Counter color, int x, int y) {
		int total = 0, accumulateTiles = 0, auxColumn = x, auxRow = y;
		Counter nextColor = changeColor(color);
		
		// Check UpLeft  
		while((auxColumn >= 1) && (auxRow >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == nextColor)) {
			auxColumn--; auxRow--; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
			total++;
		}

		// Check DownRight
		auxColumn = x; auxRow = y;
		while((auxColumn <= b.getWidth()) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) == nextColor)) {
			auxColumn++; auxRow++; accumulateTiles++;
		}
		if (accumulateTiles >= 1 && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
			total++;
		} 
		
		return total;		
	}

	// CheckDiagonal2: Function to Check diagonals given a fixed position
	
	public static int checkDiagonal2(Board b, Counter color, int x, int y) {
		int total = 0, accumulateTitles = 0, auxColumn = x, auxRow = y;
		Counter nextColor = changeColor(color);
		
		// Check Up Right
		while((auxColumn <= b.getWidth()) && (auxRow >= 1) && (b.getPosition(auxColumn + 1, auxRow - 1) == nextColor)) {
			auxColumn++; auxRow--; accumulateTitles++;
		}
		if (accumulateTitles >= 1 && (b.getPosition(auxColumn + 1, auxRow - 1) == color)) {
			total++;
		}
		
		// Check Bottom Left 
		auxColumn = x; auxRow = y;
		while((auxColumn >= 1) && (auxRow <= b.getHeight()) && (b.getPosition(auxColumn - 1, auxRow + 1) == nextColor)) {
			auxColumn--; auxRow++; accumulateTitles++;
		}
		if (accumulateTitles >= 1 && (b.getPosition(auxColumn - 1, auxRow + 1) == color)) {
			total++;
		} 
		
		return total;	
	}

	public static Counter changeColor(Counter c) {
		if (c == Counter.WHITE) return Counter.BLACK;
		else if(c == Counter.BLACK) return Counter.WHITE;
		else return Counter.EMPTY;
	}
	
	// Checks if there's an empty cell given a column
	
	public static int freeRowPosition(int col, Board board) {
		int row = -1,
			y 	=  board.getHeight();
		boolean empty = false;
		
		while ((!empty) && (y >= 1)) {
			if (board.getPosition(col,y) == Counter.EMPTY) {
				empty = true;
				row = y;
			}
			else {
				y--;
			}			
		} 
		return row;
	}

	// This functions is used for the Undo Function
	// Which returns the first row occupied (means that is the last movement of the user in that colum)
	
	public static int occupiedRowPosition(Board board, int col) {
		int row, height, y = 1;
		boolean occupied = false;
		
		height = row = board.getHeight();
		
		while (!occupied && y <= height) {
			if (board.getPosition(col, y) != Counter.EMPTY)
				occupied = true;
				row = y;
			y++;
		}
		return row;
	}
	
	public static void whoMoves(Game game) {
		if (game.getTurn() == Counter.WHITE)
			System.out.println("White to move");
		else if (game.getTurn() == Counter.BLACK)
			System.out.println("Black to move");
	}
	
	public static void moveColumnDown(Board board, int column) {
		for (int i = board.getHeight(); i > 1; i--){
			board.setPosition(column, i, board.getPosition(column , i - 1));
		}
	}
	public static void moveColumnUp(Board board, int column) {
		for (int i = 1 ; i < board.getHeight(); i++){
			board.setPosition(column, i, board.getPosition(column, i + 1));
		}
	} 
	
	// He tenido que escribir de nuevo la funciÃƒÂ³n de pedro para 
	// que funcione el conecta 4.
	
	public static boolean fullColumn(int column, Board b) {
		boolean isFull = true;
		int row = b.getHeight();
		
		while ((isFull) && (row >= 1)) {
			if (b.getPosition(column, row) == Counter.EMPTY) 
				isFull = false;
			row--;
		}
		
		return isFull;
	}
	
	public static void setGravityDimX(int x) {
		DIMX_GRAVITY = x;
	}
	public static void setGravityDimY(int y){
		DIMY_GRAVITY = y;
	}
	
	public static GravityMove applyGravity(Board board, int column, int row, Counter counter){
		int distRight, distLeft, distUp, distBottom;
		distLeft = column - 1;
		distRight = DIMX_GRAVITY - column;
		distUp = row-1;
		distBottom = DIMY_GRAVITY - row;
		double minDIM = 0;
		int minDimInt = 0;
		GravityMove movement = null;
		
		minDIM = Math.min(DIMX_GRAVITY, DIMY_GRAVITY);
		minDimInt = (int) Math.ceil(minDIM/2);
		
		if((distLeft == distRight) && (distUp == distBottom)) {
			movement = displaceCounter(board, column, row, 0, 0, counter);
		}
		else if (distLeft == distRight) {
			if (distUp < distBottom) {
				movement = displaceCounter(board, column, row, 0, -1, counter);
			}
			else if (distBottom < distUp) {
				movement = displaceCounter(board, column, row, 0, +1, counter);
			}
		}
		else if (distUp == distBottom) {
			if (distLeft < distRight) {
				movement = displaceCounter(board, column, row, -1, 0, counter);
			}
			else if (distLeft > distRight) {
				movement = displaceCounter(board, column, row, +1, 0, counter);
			}
		}
		else if ((distUp == distRight) && (distUp < minDimInt)) {
			movement = displaceCounter(board, column, row, +1, -1, counter);
		}
		else if ((distUp == distLeft) && (distUp < minDimInt)) {
			movement = displaceCounter(board, column, row, -1, -1, counter);	
		}
		else if ((distBottom == distRight) && (distBottom < minDimInt)) {
			movement = displaceCounter(board, column, row, +1, +1, counter);
		}
		else if ((distBottom == distLeft) && (distBottom < minDimInt)){
			movement = displaceCounter(board, column, row, -1, +1, counter);
		}
		else if ((distUp < distRight) && (distUp < distLeft) && (distUp < distBottom)) {
			movement = displaceCounter(board, column, row, 0, -1, counter);
		}
		else if ((distRight < distUp) && (distRight < distLeft) && (distRight < distBottom)) {
			movement = displaceCounter(board, column, row, +1, 0, counter);	
		}
		else if ((distLeft < distRight) && (distLeft < distUp) && (distLeft < distBottom)) {
			movement = displaceCounter(board, column, row, -1, 0, counter);
		}
		else if ((distBottom < distRight) && (distBottom < distLeft) && (distBottom < distUp)) {
			movement = displaceCounter(board, column, row, 0, +1, counter);
		}
		return movement;
	}
	
	public static GravityMove displaceCounter(Board board, int posCol, int posRow, int movCol, int movRow, Counter counter) {
		boolean ocuppy = false;
		int actualRow = posRow, actualColumn = posCol;
		
		if ((movCol == 0) && (movRow == 0))
			board.setPosition(actualColumn, actualRow, counter);
		
		while (!ocuppy){
			if (actualColumn > 1 && actualColumn < DIMX_GRAVITY && actualRow > 1 && actualRow < DIMY_GRAVITY) {
				if (board.getPosition(actualColumn + movCol, actualRow + movRow) != Counter.EMPTY) {
					ocuppy = true;
				}
				else{
					actualColumn += movCol;
					actualRow += movRow;
				}
			}
			else {
				ocuppy = true;
			}
		}
		return new GravityMove(actualColumn, actualRow, counter);	
	}
	
	public static void help() {
		System.out.println("The available commands are:");
		System.out.println("");
		System.out.println("MAKE A MOVE: place a counter on the board.");
		System.out.println("UNDO: undo the last move of the game.");
		System.out.println("RESTART: restart the game.");
		System.out.println("PLAY [c4|co|gr] [dimX dimY]: change the type of game.");
		System.out.println("PLAYER [white|black] [human|random]: change the type of player.");
		System.out.println("EXIT: exit the application.");
		System.out.println("HELP: show this help.");
	}
	
	public static void helpInit() {
		System.out.println("usage: tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]");
		System.out.println(" -g,--game <game>           Type of game (c4, co, gr, rv). By default, c4.");
		System.out.println(" -h,--help                  Displays this help.");
		System.out.println(" -u,--ui<tipo>              Type of interface (console,window).");
		System.out.println("                            by default console.");
		System.out.println(" -x,--dimX <columnNumber>   Number of columns on the board (Gravity only).");
		System.out.println("                            By default, 10.");
		System.out.println(" -y,--dimY <rowNumber>      Number of rows on the board (Gravity only). By");
		System.out.println("                            default, 10.");
	}
	
	
	
	//METHODS FOR THE VIEW
	public static GridBagConstraints configureConstraint(int fill, int gridX, int gridY, double weightX, double weightY) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.fill = fill;
		c.gridx = gridX;
		c.gridy = gridY;
		c.weightx = weightX;
		c.weighty = weightY;
		return c;
	}
	
	public static JPanel createPanel(Color color, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x,y));
		panel.setVisible(true); 
		return panel;
	}
	
	public static JButton createAuxButton(int w, int h, String name, String fileName, Color c, boolean border) {
		JButton b = new JButton();
		b.setBackground(c);
		b.setPreferredSize(new Dimension(w, h)); 
		if (fileName != "") b.setIcon(new ImageIcon(fileName));
		if (name != "") b.setText(name);
		if (!border) 	b.setBorder(null);
		return b; 
	}
	

/*
	public static int menu(Game game, Scanner input) {
		int option = - 1;
		boolean valid = false;
		String optionString = "", lowerCaseStr;
		
		while (!valid) {
			
			 game.getBoard().printBoard();
			
			whoMoves(game);
			System.out.print ("Please enter a command: ");

			optionString = input.nextLine().toUpperCase();
			lowerCaseStr = optionString.toLowerCase();
			String[] words = optionString.split(" ");

			if (words.length == 1) {
				if (words[0].equals("UNDO")) {
					option = 1;
				}
				else if (words[0].equals("RESTART")) {
					option = 2;
				}
				else if (words[0].equals("EXIT")) {
					option = 3;
				}
				else if (words[0].equals("HELP")){
					option = 7;
				}
				else {
					System.err.println(lowerCaseStr + ": command not understood, please try again");
				}
			}
			else if (words.length == 2) {
				if (words[0].equals("PLAY")) {
					if (words[1].equals("C4")) {
						option = 4;
					}
					else if (words[1].equals("CO")) {
						option = 5;
					}
					else if (words[1].equals("RV")) {
						option = 12;
					}
					else {
						System.err.println(lowerCaseStr + ": command not understood, please try again");
					}
				}
				else {
					System.err.println(lowerCaseStr + ": command not understood, please try again");
				}
			}
			else if (words.length == 3) {
				if (words[0].equals("MAKE")) {
					if (words[1].equals("A")) {
						if (words[2].equals("MOVE")) {
							option = 0;
						}
						else {
							System.err.println(lowerCaseStr + ": command not understood, please try again");
						}	
					}
					else {
						System.err.println(lowerCaseStr + ": command not understood, please try again");
					}
					
				}
				else if (words[0].equals("PLAYER")) {
					if (words[1].equals("WHITE")) {
						if(words[2].equals("HUMAN")) {
							option = 8; // WHITE HUMAN
						}
						else {
							option = 9; // WHITE RANDOM
						}
					} 
					else if (words[1].equals("BLACK")) {
						if(words[2].equals("HUMAN")) {
							option = 10; // BLACK HUMAN
						}
						else {
							option = 11; // BLACK RANDOM
						}
					} 
				}
				else {
					System.err.println(lowerCaseStr + ": command not understood, please try again");
				}
				
			}
			else if (words.length == 4){
				if (words[0].equals("PLAY")) {
					if (words[1].equals("GR")) {
						try {
							   Integer.parseInt(words[2]);
							   Integer.parseInt(words[3]);
							   if (Integer.parseInt(words[2]) < 1){
								   setGravityDimX(1);
							   }
							   else{
								   setGravityDimX(Integer.parseInt(words[2]));
							   }
							   if (Integer.parseInt(words[3]) < 1){
								   setGravityDimY(1);
							   }
							   else{
								   setGravityDimY(Integer.parseInt(words[3]));
							   }
							   option = 6;
							} catch (NumberFormatException e) {
								System.err.println(lowerCaseStr + ": command not understood, please try again");
							}
					}
				}
			}
			else {
				System.err.println(lowerCaseStr + ": command not understood, please try again");
			}
			
			if ((option >= 0) && (option <= 12)) {
				valid = true;
			}
		}
		return option;
	}
*/
		
}
