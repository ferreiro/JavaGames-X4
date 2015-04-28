package tp.pr4.logic;

public enum GameType {
	connect4("Connect4"), complica("Complica"), Gravity("Gravity"), reversi("Reversi");
	@SuppressWarnings("unused")
	private String name;
	
	private GameType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name();
	}
}
