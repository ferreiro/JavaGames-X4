package tp.pr5.logic;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import tp.pr5.control.WindowController;

public class PlayersModel implements ComboBoxModel<PlayerType> {
	
	private Counter player;
	private PlayerType selected;
	private WindowController ctrl;
	
	public PlayersModel(Counter player, WindowController wCont){
		this.player = player;
		this.selected = player.getMode();
		this.ctrl = wCont;
	}

	public int getSize() {
		return 2;
	}

	@Override
	public PlayerType getElementAt(int index) {
		if (index == 0)
			return PlayerType.HUMAN;
		else
			return PlayerType.AUTO;
	}

	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	public void setSelectedItem(Object anItem) {
		this.selected = (PlayerType) anItem;
		ctrl.setPlayerMode(player, this.selected);
	}

	public Object getSelectedItem() {
		return this.selected;
	}
	
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

}
