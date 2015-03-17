package tp.pr4.control;

import tp.pr4.logic.Board;
import tp.pr4.logic.ComplicaMove;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;
import tp.pr4.Resources.Resources;


public class RandomComplicaPlayer implements Player{

	public Move getMove(Board board, Counter counter) {
		int column = (int) ((Math.random() * Resources.DIMX_COMPLICA) + 1);
		return new ComplicaMove(column, counter);
	}

}
