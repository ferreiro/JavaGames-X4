package tp.pr5.views.window;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import tp.pr5.logic.Counter;
import tp.pr5.logic.PlayerType;

public class PlayersModel implements ComboBoxModel<PlayerType> {
	
	private Counter player;
	private PlayerType selected;
	
	public PlayersModel(Counter player){
		this.player = player;
		this.selected = player.getMode();
	}

	public int getSize() {
		return 2;
	}

	@Override
	public PlayerType getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	public void setSelectedItem(Object anItem) {
		this.selected = (PlayerType) anItem;
		//ctrl.setPlayerMode(player, this.selected);
		
		//that upper line is supposed to belike that and call the controller but i cannot see how by now.
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}
	
	public String toString(){
		return null;//TODO: this function
	}

}
