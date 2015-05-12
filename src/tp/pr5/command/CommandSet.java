package tp.pr5.command;

public class CommandSet {
	static Command[] cmds = {new UndoCmd(), new RestartCmd(), new ExitCmd(), new HelpCmd(), new MoveCmd(), new ChangeGameCmd(), new ChangePlayerCmd()};
	
	public static Command parse(String line){
		for( Command c : cmds) {
			Command cmd = c.parse(line);
			if ( cmd != null ) return cmd;
		}
		return null;
	}
}
