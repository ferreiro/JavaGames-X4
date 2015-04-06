package tp.pr4.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import tp.pr4.logic.Counter;
import tp.pr4.logic.Board;
import tp.pr4.logic.Move;

public class Game implements Observable<GameObserver>{

	private Board board;
	private Counter turn;
	private Counter winner;
	private boolean finished;
	
	protected GameRules rules;
	private Deque<Move> stack = new ArrayDeque<>();
	
	public Game(GameRules rules) { 
		this.rules = rules; 
		reset(rules);
	}

	public void reset(GameRules rules) { // Reset all the Game Rules
		this.rules = rules;
		board = rules.newBoard();
		turn = rules.initialPlayer();
		winner = Counter.EMPTY;
		finished = false;
		stack.clear();
	}
	
	public boolean executeMove(Move mov) throws InvalidMove {  
		boolean valid = false, draw; 
		Counter wonColor;
		
		if ((mov.getPlayer() == turn) && (!finished)) { // No puede permitir hacer movimientos fuera de turno o se ha terminado el juego
			
			finished = false;
			winner = Counter.EMPTY;
			valid = mov.executeMove(board);
			
			if (valid) { 
 
				wonColor = rules.winningMove(mov, board); // Checks if there's a Counter Winner or not
				draw = rules.isDraw(mov.getPlayer(), board);
				
				if (draw) {
					finished = true; // hay empate, terminar
					this.winner = Counter.EMPTY;
				}
				else {
					if (wonColor == Counter.EMPTY) {
						increaseStack(mov); // Nobody wins, increase stack
						turn = rules.nextTurn(mov.getPlayer(), board); // Change turn
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

	@Override
	public void addObserver(GameObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(GameObserver o) {
		// TODO Auto-generated method stub
		
	}
	
}
