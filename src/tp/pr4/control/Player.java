package tp.pr4.control;

import tp.pr4.logic.Move;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Board;


public interface Player {
	public Move getMove(Board board, Counter counter);
}
