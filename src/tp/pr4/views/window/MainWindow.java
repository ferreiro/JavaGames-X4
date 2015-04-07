package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.*;

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	private int dimX = Resources.DIMX_CONNECT4;
	private int dimY = Resources.DIMY_CONNECT4;
	
	//all the panels
	private JPanel mainPanel = null;
	
	
	public MainWindow(){
		super();
		initGUI();
	}
	
	private void initGUI(){
		JComboBox<String> Cbox;
		String names[] = {"Connect4", "Complica", "Gravity"}; 
			
		mainPanel = new JPanel(new BorderLayout());
		
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
		JPanel right = new JPanel(new GridBagLayout());
		right.setBackground(new Color(146,146,146));
		right.setPreferredSize(new Dimension(60,60));
		
		//the top panel inside dark one
		JPanel UndoAndReset = new JPanel(new GridBagLayout());
		UndoAndReset.setBackground(Color.blue);
		UndoAndReset.setPreferredSize(new Dimension(10,10));
		
		// UNDO BUTTON
		JButton undoButton = new JButton("Undo");
		undoButton.setIcon(new ImageIcon("src/icons/undo.png"));
		c = configureConstraint(GridBagConstraints.NONE, 0, 0, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		UndoAndReset.add(undoButton,c);
		
		// RESET BUTTON
		JButton resetButton = new JButton("Reset");
		resetButton.setIcon(new ImageIcon("src/icons/reset.png"));
		c = configureConstraint(GridBagConstraints.NONE, 1, 0, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		UndoAndReset.add(resetButton,c);
		
		//characteristics of the topDark part
		c = configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1.5); // gridX, gridY, weightX, weightY 
		right.add(UndoAndReset,c);
		
		//the bottom panel inside dark one
		JPanel ComboAndChangeButton = new JPanel(new GridBagLayout());
		ComboAndChangeButton.setBackground(Color.red);
		ComboAndChangeButton.setPreferredSize(new Dimension(50,50));
		
		//COMBOBOX
		Cbox = new JComboBox<String>(names);
		Cbox.setSelectedIndex(0);
		c = configureConstraint(GridBagConstraints.NONE, 0,1, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		ComboAndChangeButton.add(Cbox,c);
		
		//Space in between
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(Color.red);
		blankPanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .70;
		c.weighty = .70;
		ComboAndChangeButton.add(blankPanel,c);
		
		//BUTTON FOR CHANGING
		JButton changeButton = new JButton("Change");
		changeButton.setIcon(new ImageIcon("src/icons/check.png"));
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.1;
		c.weighty = 0.1;
		ComboAndChangeButton.add(changeButton,c);
		
		//for the bottom of the dark side panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		right.add(ComboAndChangeButton,c);
		
		
		//the characteristics of dark in general
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .75;
		c.weighty = .75;
		centrePanel.add(right, c);
		
		
		//creating the tablePane one hay que cambiar el hecho de que conjja c4, complica o gravity
		JPanel tablePane = new JPanel(new GridLayout(dimX, dimY, 2, 2));
		tablePane.setBackground(Color.LIGHT_GRAY);
		tablePane.setSize(120,120);
		//lo que es dibujar los circulos no es aqui en la inicialización
		
		//table part
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		centrePanel.add(tablePane, c);
		
		mainPanel.add(centrePanel, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setMinimumSize(new Dimension(400, 400));
		this.setVisible(true);
		
	}
	
	private GridBagConstraints configureConstraint(int fill, int gridX, int gridY, double weightX, double weightY) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.fill = fill;
		c.gridx = gridX;
		c.gridy = gridY;
		c.weightx = weightX;
		c.weighty = weightY;
		return c;
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
		
		JFrame msgFrame = new JFrame();
		JOptionPane.showMessageDialog(msgFrame, "The Winner is" + winner + ".");//TODO method toString and finish the function
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
		// NOTHING TO UNDO
		
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {//, Boolean undoPossible
		// TODO Auto-generated method stub
	}

}
