package tp.pr4.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import tp.pr4.logic.Counter;
import tp.pr4.logic.Board;
import tp.pr4.logic.Move;

public class Game {

	private Board board;
	private Counter winner;
	private Counter turn;
	private boolean finished;
	private Deque<Move> stack = new ArrayDeque<>();
	protected GameRules rules;
	
	public Game(GameRules rules) { 
		this.rules = rules; 
		reset(rules); // Crea un primer juego del tipo conecta4. 
	}

	public boolean executeMove(Move mov) throws InvalidMove {  
		Counter wonColor;
		boolean valid = false, draw; 
		
		if ((mov.getPlayer() == turn) && (!finished)) { // No puede permitir hacer movimientos fuera de turno o se ha terminado el juego
			
			winner = Counter.EMPTY;
			finished = false;
			valid = mov.executeMove(board);
			
			if (valid) { 
 
				wonColor = rules.winningMove(mov, board); // Importante! Primero hay que llamar a esta, para
														  // que actualice el color del ganador!
				draw = rules.isDraw(mov.getPlayer(), board); // Hay empate?
				
				if (draw) {
					finished = true; // hay empate, terminar
					this.winner = Counter.EMPTY;
				}
				else {
					if (wonColor == Counter.EMPTY) {
						increaseStack(mov); // Si no gana nadie, guardar movimiento
						turn = rules.nextTurn(mov.getPlayer(), board); // Cambiar el turno.
					}
					else {
						this.winner = wonColor;
						finished = true;
					} 
				}
			}
		}
		else{
			throw new InvalidMove("Invalid turn");
		}
		
		return valid;
	}
	
	public void reset(GameRules rules) { // Reinicia las reglas del juego (los atributos de Game)
		this.rules = rules;
		board = rules.newBoard();
		turn = rules.initialPlayer();
		winner = Counter.EMPTY;
		finished = false;
		stack.clear();
	}
	
	//  Undo and stack 
	
	public boolean undo() {
		boolean success = false;
		Move previousMove;
		
		if (!stack.isEmpty()){
			previousMove = stack.getLast();
			stack.removeLast();		
			success = true;
			previousMove.undo(board); 
			turn = previousMove.getPlayer(); // Bug fixed!!! Actualizar el color del jugador!
		}
		else {
			System.err.println("Nothing to undo.");
		}
		
		return success;
	}
	
	public void increaseStack(Move movement) {
			stack.addLast(movement);
	}
	
	// Getters and setters 
	
	public boolean isFinished() {
		return this.finished;
	}
	
	public Counter getWinner(){
		return this.winner;
	}
	
	public Board getBoard(){
		return this.board;
	}

	public Counter getTurn(){
		return this.turn;
	}
	
	public GameRules getRules(){
		return rules;
	}
	
}
