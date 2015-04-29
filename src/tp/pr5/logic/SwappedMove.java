package tp.pr5.logic;

public class SwappedMove {
	private int x;
	private int y;
	private Counter color;

	public SwappedMove(int x, int y, Counter c) {
		this.x = x;
		this.y = y;
		this.color = c;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void setColor(Counter c) {
		this.color = c;
	}

	public Counter getColor() { 
		return this.color;
	}
	
}
