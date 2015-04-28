package tp.pr5.logic;

public enum Counter {
	EMPTY("Empty"), 
	WHITE("White"), 
	BLACK("Black");
	
	String name;
	PlayerType mode = PlayerType.HUMAN;
	
	Counter(String name){
		this.name = name;
	}

	public PlayerType getMode(){
		return mode;
	}
	
	public void SetMode(PlayerType mode){
		this.mode = mode;
	}
	
	public String toString(){
		return name;
	}	
};
