package tp.pr5.Resources;

public enum PlayerType {
	HUMAN("Human"), AUTO("Automatic");
	
	private String name;
	
	PlayerType(String name){
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}
};
