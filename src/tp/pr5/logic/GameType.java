package tp.pr5.logic;

public enum GameType {
	connect4("Connect4"), complica("Complica"), gravity("Gravity"), reversi("Reversi");
	@SuppressWarnings("unused")
	private String name;
	
	private GameType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name();
	}
}
