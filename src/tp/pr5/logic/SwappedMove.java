package tp.pr5.logic;

public class SwappedMove {
	private int x;
	private int y;
	private int origenX;
	private int origenY;
	private Counter color;

	public SwappedMove(int x, int y, int origenX, int origenY, Counter c) {
		this.x = x;
		this.y = y;
		this.origenX = origenX;
		this.origenY = origenY;
		this.color = c;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getOrigenX() {
		return origenX;
	}
	public void setOrigenX(int orX) {
		this.origenX = orX;
	}
	
	public int getOrigenY() {
		return origenY;
	}
	public void setOrigenY(int orY) {
		this.origenY = y;
	}

	public void setColor(Counter c) {
		this.color = c;
	}

	public Counter getColor() { 
		return this.color;
	}
	
}
