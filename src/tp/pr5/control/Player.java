package tp.pr5.control;

import tp.pr5.logic.Move;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Board;


public interface Player {
	public Move getMove(Board board, Counter counter);
}
