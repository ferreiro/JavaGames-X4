package tp.pr5.control;

import tp.pr5.logic.Board;
import tp.pr5.logic.ComplicaMove;
import tp.pr5.logic.Move;
import tp.pr5.Resources.Counter;
import tp.pr5.Resources.Resources;


public class RandomComplicaPlayer implements Player{

	public Move getMove(Board board, Counter counter) {
		int column = (int) ((Math.random() * Resources.DIMX_COMPLICA) + 1);
		return new ComplicaMove(column, counter);
	}

}
