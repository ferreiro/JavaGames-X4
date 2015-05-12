package tp.pr5.command;

import java.util.Scanner;

import tp.pr5.control.ComplicaFactory;
import tp.pr5.control.Connect4Factory;
import tp.pr5.control.ConsoleController;
import tp.pr5.control.GameTypeFactory;
import tp.pr5.control.GravityFactory;
import tp.pr5.control.ReversiFactory;
import tp.pr5.logic.Game;

public class ChangeGameCmd implements Command {
	
	private GameTypeFactory f;
	
	private ChangeGameCmd(GameTypeFactory f){
		this.f = f;
	}
	
	public ChangeGameCmd(){}

	@Override
	public void execute(Game g, ConsoleController c, Scanner in) {
		c.setGameType(f);
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( (tokens.length != 2 && tokens.length != 4) || !tokens[0].equalsIgnoreCase("play")) return null;
		else if(tokens.length == 2){
			if(tokens[1].equalsIgnoreCase("c4"))
				return new ChangeGameCmd(new Connect4Factory());
			else if(tokens[1].equalsIgnoreCase("co"))
				return new ChangeGameCmd(new ComplicaFactory());
			else if(tokens[1].equalsIgnoreCase("rv"))
				return new ChangeGameCmd(new ReversiFactory());
			else
				return null;
		}
		else if(tokens.length == 4){
			if(tokens[1].equalsIgnoreCase("gr")){
				try{
					int x = Integer.parseInt(tokens[2]);
					int y = Integer.parseInt(tokens[3]);
					return new ChangeGameCmd(new GravityFactory(x, y));
				} catch ( NumberFormatException e) {
					return null;
				}
			}
		}
		return null;
	}
}
