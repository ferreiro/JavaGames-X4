package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.*;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	
	public MainWindow(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		JComboBox<String> Cbox;
		String names[] = {"Connect4", "Complica", "Gravity"}; 
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//bottom of the borderLayout
		JPanel bottomPanel = createPanel(new Color(200,230, 150), 70,70);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//en este el top podriamos añadir un logo y hacerlo un poquico mas grande
		JPanel topPanel = createPanel(new Color(200,230, 150), 10,10);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		
		//centre of the borderLayout
		JPanel centrePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		//dark inside centre panel
		JPanel dark = new JPanel(new GridBagLayout());
		dark.setBackground(new Color(146,146,146));
		dark.setPreferredSize(new Dimension(50,50));
		
		//the top panel inside dark one
		JPanel topDark = createPanel(Color.blue, 10,10);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		dark.add(topDark,c);
		
		//the bottom panel inside dark one
		JPanel botDark = new JPanel(new GridBagLayout());
		botDark.setBackground(Color.red);
		botDark.setPreferredSize(new Dimension(50,50));
		
		//COMBOBOX
		Cbox = new JComboBox<String>(names);
		Cbox.setSelectedIndex(0);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.weighty = 0.1;
		botDark.add(Cbox,c);
		
		//Space in between
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(Color.red);
		blankPanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .70;
		c.weighty = .70;
		botDark.add(blankPanel,c);
		
		//BUTTON FOR CHANGING
		JButton changeButton = new JButton("Change");
		changeButton.setIcon(new ImageIcon("src/icons/check.png"));
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.1;
		c.weighty = 0.1;
		botDark.add(changeButton,c);
		
		//for the bottom of the dark side panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		dark.add(botDark,c);
		
		
		//the characteristics of dark in general
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .75;
		c.weighty = .75;
		centrePanel.add(dark, c);
		
		
		//creating the light one
		JPanel light = createPanel(Color.LIGHT_GRAY, 170,170);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		centrePanel.add(light, c);
		
		mainPanel.add(centrePanel, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setMinimumSize(new Dimension(400, 400));
		this.setVisible(true);
		
	}
	
	private JPanel createPanel(Color color, int x, int y){
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x,y));
		return panel;
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub
	}

}
