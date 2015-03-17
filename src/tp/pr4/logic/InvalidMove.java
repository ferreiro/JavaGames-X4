package tp.pr4.logic;

import java.lang.String;
import java.lang.Throwable;

public class InvalidMove extends Exception {

	public InvalidMove() {
		super();
	}
	
	public InvalidMove(String msg) {
		super(msg);
	}
	
	public InvalidMove(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public InvalidMove(Throwable cause){
		super(cause);
	}
}
