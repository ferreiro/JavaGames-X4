package tp.pr4.logic;

public class ReversiMove extends Move {
	
	private int row;

	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		super(moveColour, moveColumn);
		this.row = moveRow;
	}

	@Override
	public boolean executeMove(Board board) throws InvalidMove {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getColumn() {return this.column;}
	public int getRow() {return this.row;}

}
