package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public enum Rules {
	C4 (new Connect4Rules()) ,
	CO (new ComplicaRules()) ,
	GR (new GravityRules(Resources.DIMX_GRAVITY, Resources.DIMY_GRAVITY));
		
	private final GameRules gameRules;
	
	Rules(GameRules gameRules){
		this.gameRules = gameRules;
	}
	
	public GameRules getRules (){
		return this.gameRules;
	}
};

//https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html

