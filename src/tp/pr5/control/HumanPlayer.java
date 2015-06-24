package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.Resources.Counter;
import tp.pr5.logic.Board;
import tp.pr5.logic.Move;

public class HumanPlayer implements Player {
	private boolean withRow;
	private Scanner in;
	private GameTypeFactory factory;
	
	public HumanPlayer( boolean withRow, java.util.Scanner in, GameTypeFactory factory ) {
		this.withRow = withRow;
		this.in = in;
		this.factory = factory;
	}
	
	public Move getMove(Board board, Counter counter) {
		int col, row = -1;
		@SuppressWarnings("unused")
		String auxStr;
		
		System.out.print("Please provide the column number: ");

		col = this.in.nextInt();
		auxStr = this.in.nextLine();
		
		if (withRow) {
			System.out.print("Please provide the row number: ");
			row = this.in.nextInt();
			auxStr = this.in.nextLine();				
		}
		
		return factory.createMove(col, row, counter);
	}
	
}
