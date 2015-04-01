package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel bottomPanel = createPanel(new Color(200,255, 150), 70,70);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//en este el top podriamos añadir un logo y hacerlo un poquico mas grande
		JPanel topPanel = createPanel(Color.BLUE, 10,10);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);
		
		JPanel centrePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel greenRight = createPanel(Color.GRAY, 50,50);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		centrePanel.add(greenRight, c);
		
		JPanel pinkLeft = createPanel(Color.LIGHT_GRAY, 150,150);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		centrePanel.add(pinkLeft, c);
		
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
