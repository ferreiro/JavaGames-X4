package tp.pr5.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// Import javafx.scene.image.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tp.pr5.Resources.Resources;
import tp.pr5.control.GameTypeFactory;
import tp.pr5.control.WindowController;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.GameType;
import tp.pr5.logic.PlayerType;
import tp.pr5.logic.PlayersModel;
import tp.pr5.logic.ReadOnlyBoard;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements GameObserver {
	private JPanel mainPanel, topPanel, leftMargin, rightMargin, 
				   middlePanelLeft, middlePanelRight, middlePanel, changeDimensions,
				   bottomInfoPanel;
	private JTextField txtFieldRow, txtFieldColumn;
	private JComboBox<GameType> Cbox;
	private JComboBox<PlayerType> whitePlayerList, blackPlayerList;
	private WindowController wController;
	private boolean active = false;
	private JButton[][] buttons;
	private int numberColums;
	private int numberRows;
	private JLabel currentColor;
	private JButton undoButton, resetButton, randomButton;
	
	public MainWindow(GameTypeFactory gType, Game game) {
		super(); 
		this.wController = new WindowController(gType, game); 
		initGUI(); 
	}
	
	private void initGUI() { 
		GameType names[] = { GameType.connect4, GameType.complica, GameType.gravity, GameType.reversi  };
		mainPanel = new JPanel(new BorderLayout()); 
		
		/////////////////// HEADER AND BOTTOM //////////////////
		
		topPanel = Resources.createPanel(new Color(255, 255, 255), 10, 70);
		mainPanel.add(topPanel, BorderLayout.PAGE_START); 

		JButton logoHeader = new JButton(); // Logo
		logoHeader = Resources.createAuxButton(300,  60, "", Resources.RESOURCES_URL+"/logo.png", new Color(255,255,255,1), false );
		topPanel.add(logoHeader, Resources.configureConstraint(GridBagConstraints.BOTH, 1, 2, 0.1, 0.1)); // gridX, gridY, weightX, weightY );
		
		//////////////////////// MARGINS ///////////////////////
		// LEFT MARGIN PANEL
		leftMargin = new JPanel();
		leftMargin.setSize(100, 10);
		mainPanel.add(leftMargin, BorderLayout.LINE_START);
		
		// RIGHT MARGIN PANEL
		rightMargin = new JPanel();
		rightMargin.setSize(100, 10);
		mainPanel.add(rightMargin, BorderLayout.LINE_END);
		
		////////////// MIDDLE PANEL OF BORDERLAYOUT ////////////
		
		middlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		 
		// Creating the tablePane one hay que cambiar el hecho de que conjja c4, complica o gravity
		middlePanelLeft = new JPanel(new GridBagLayout());
		middlePanelLeft.setBackground(Color.LIGHT_GRAY);
		middlePanelLeft.setSize(120,120);

		//table part
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 0, 1.5, 1); // gridX, gridY, weightX, weightY 
		middlePanel.add(middlePanelLeft, c);
		
		
		// MIDDLE PANEL RIGHT
		middlePanelRight = new JPanel(new GridBagLayout());
		middlePanelRight.setBackground(new Color(222, 222, 222));
		middlePanelRight.setPreferredSize(new Dimension(20,30));
		 
		////////////// MIDDEL PANEL RIGHT TOP ////////////
		
		JPanel middlePanelRightTop = new JPanel(new GridBagLayout());
		middlePanelRightTop.setBackground(new Color(0,0,0,0));
		middlePanelRightTop.setPreferredSize(new Dimension(10,10));
		
//		ButtonsPanel bPanel = new ButtonsPanel(wController);
		
		//The panel that contains all three buttons 
		JPanel buttonsPannel = new JPanel(new GridBagLayout());
		buttonsPannel.setBackground(new Color(0,0,0,0));
		buttonsPannel.setPreferredSize(new Dimension(10,10));

		// RANDOM USER
		randomButton = new JButton();
		randomButton = Resources.createAuxButton(120,  100, "Random", Resources.RESOURCES_URL + "random.png", new Color(255,255,0), true); 
		c = Resources.configureConstraint(GridBagConstraints.CENTER, 0, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		buttonsPannel.add(randomButton,c);
		
		randomButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				wController.randomMove();
			}
		});
		
		// UNDO BUTTON
		undoButton = new JButton("Undo");
		undoButton = Resources.createAuxButton(230,  100, "Undo", Resources.RESOURCES_URL + "undo.png", new Color(255,255,0), true);  
		c = Resources.configureConstraint(GridBagConstraints.CENTER, 1, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		buttonsPannel.add(undoButton,c);
		undoButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				wController.undo();
			}
		});

		// RESET BUTTON
		resetButton = new JButton();
		resetButton = Resources.createAuxButton(120,  100, "Restart", Resources.RESOURCES_URL + "reset.png", new Color(255,255,0), true); 
		c = Resources.configureConstraint(GridBagConstraints.NONE, 2, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		buttonsPannel.add(resetButton,c);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wController.reset();
 			}
		});
		
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1);
		middlePanelRightTop.add(buttonsPannel, c);
		
		
		//The panel where are both combo box for selecting the player type
		JPanel playersTypePanel = new JPanel(new GridBagLayout());
		playersTypePanel.setBackground(new Color(0,0,0,0));
		playersTypePanel.setPreferredSize(new Dimension(400,400));
		
		
		//Combo box for the white player mode
		whitePlayerList = new JComboBox<PlayerType>(new PlayersModel(Counter.WHITE, wController));
		whitePlayerList.setSelectedIndex(0);
		whitePlayerList.setPreferredSize(new Dimension(200, 100));
		whitePlayerList.setFont(new Font("Arial", Font.BOLD, 24)); 
		whitePlayerList.setBorder(new EmptyBorder(10, 10, 10, 10));
		whitePlayerList.setLocation(0, 40);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		playersTypePanel.add(whitePlayerList, c);
		
		//Combo box for the black player mode
		blackPlayerList = new JComboBox<PlayerType>(new PlayersModel(Counter.BLACK, wController));
		blackPlayerList.setSelectedIndex(0);
		blackPlayerList.setPreferredSize(new Dimension(200, 100));
		blackPlayerList.setFont(new Font("Arial", Font.BOLD, 24)); 
		blackPlayerList.setBorder(new EmptyBorder(10, 10, 10, 10));
		blackPlayerList.setLocation(0, 40);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 1, 0.1, 0.3); // gridX, gridY, weightX, weightY
		playersTypePanel.add(blackPlayerList, c);
		
		
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 1, 0.1, 0.3); // gridX, gridY, weightX, weightY
		middlePanelRightTop.add(playersTypePanel, c);
		
		
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1.5);  // characteristics of the topDark part
		middlePanelRight.add(middlePanelRightTop,c);
		
		////////////// MIDDEL PANEL RIGHT BOTTOM ////////////
		
		JPanel middlePannelRightBottom = new JPanel(new GridBagLayout());
		middlePannelRightBottom.setBackground(new Color(0,0,0,30));
		middlePannelRightBottom.setPreferredSize(new Dimension(50,50));	
		
		JPanel anotherPanel = new JPanel(); 
		anotherPanel.setVisible(true);
		anotherPanel.setBackground( new Color(0, 0, 0, 30));
		anotherPanel.setPreferredSize(new Dimension(200, 100));
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 1, .70, 0); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(anotherPanel,c);
		
		// COMBOBOX
		Cbox = new JComboBox<GameType>(names);
		Cbox.setSelectedIndex(wController.getGame().getRules().intRules()); 
		Cbox.setFont(new Font("Arial", Font.BOLD, 24)); 
		Cbox.setBorder(new EmptyBorder(10, 10, 10, 10));
		Cbox.setLocation(0, 40);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 1, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		anotherPanel.add(Cbox,c);

		Cbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cbox.repaint();
				Cbox.revalidate();
				GameType name = (GameType) Cbox.getSelectedItem();
				changeDimensions.setVisible(false);
				if (name.equals(GameType.gravity)) changeDimensions.setVisible(true);
				repaint();
			}
		});
		
		// INPUTS
		
		changeDimensions = new JPanel();
		changeDimensions.setVisible(false);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 1, 1, 0.3); // gridX, gridY, weightX, weightY 
		anotherPanel.add(changeDimensions,c);

		txtFieldColumn = new JTextField();
		txtFieldColumn.setVisible(true);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.3);
		txtFieldColumn.setPreferredSize(new Dimension(40, 40));
		changeDimensions.add(txtFieldColumn,c);	
		
		txtFieldRow = new JTextField();
		txtFieldRow.setVisible(true);
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.3);
		txtFieldRow.setPreferredSize(new Dimension(40, 40));
		changeDimensions.add(txtFieldRow,c);
		
		JPanel blankPanel = new JPanel();
		blankPanel.setVisible(true);
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 1, .70, 0); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(blankPanel,c);

		// BUTTON FOR CHANGING
		JButton changeButton = Resources.createAuxButton(230,  100, "Change", Resources.RESOURCES_URL + "check.png", new Color(62,218,103), false);
		changeButton.setForeground(Color.WHITE);
		changeButton.setFont(new Font("Arial", Font.BOLD, 24));
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(changeButton,c);
				
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameType type = (GameType)Cbox.getSelectedItem();
				
				if (type.equals(GameType.gravity)) {
					try {
						int column = Integer.parseInt(txtFieldColumn.getText());
						int row = Integer.parseInt(txtFieldRow.getText());
						wController.changeGame(type, column, row);
					}
					catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(new JFrame(), "Your input is not a number", "Wrong input", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					wController.changeGame(type, 0, 0);
				}
			
			}
		});
		
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 0, 1, 1, 1); // For the bottom of the dark side panel 
		middlePanelRight.add(middlePannelRightBottom,c);		
		
		c = Resources.configureConstraint(GridBagConstraints.BOTH, 1, 0, .75, .75); //the characteristics of dark in general
		
		middlePanel.add(middlePanelRight, c);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
		// Panel for showing color of the current Player
		
		bottomInfoPanel = Resources.createPanel(new Color(255, 255, 255), 100, 50);
		mainPanel.add(bottomInfoPanel, BorderLayout.PAGE_END);
		
		String colorStr = "" + wController.getGame().getTurn(); 
		currentColor = new JLabel(colorStr + " to Move");
		currentColor.setBackground(new Color(0,0,0,0)); 
		currentColor.setPreferredSize(new Dimension(200, 45));
		currentColor.setFont(new Font("Arial", Font.BOLD, 20));
		bottomInfoPanel.add(currentColor);
		
		// BUTTON FOR EXIT
		JButton exitButton = Resources.createAuxButton(150,  40, "Close", Resources.RESOURCES_URL + "exit.png", new Color(62,218,103), false);
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Arial", Font.BOLD, 24));
		c = Resources.configureConstraint(GridBagConstraints.NONE, 0, 4, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		bottomInfoPanel.add(exitButton,c);
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
			}
		});
		 
		this.setVisible(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600)); 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	final private void closeWindow() {
		this.setVisible(false);
	}
	
	
	private JButton createButton(final int i, final int j) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(40, 40));
		button.setBackground(new Color(207,207,207));
		button.setIcon(new ImageIcon(Resources.RESOURCES_URL + "empty.png"));
		
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (active && wController.getGame().getTurn().getMode() == PlayerType.HUMAN) {
						wController.makeMove(i + 1, j + 1, wController.getGame().getTurn());
					}	
				}
			});
		// }
		return button;	 
	}
 
	
	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		refresh(board);	
		setCurrentPlayerLabel();
		if(wController.getGame().getNextPlayer().getMode() == PlayerType.HUMAN){
			undoButton.setEnabled(true);
			randomButton.setEnabled(true);
		}
	}
	
	@Override
	public void moveExecStart(Counter player) {
		undoButton.setEnabled(false);
		randomButton.setEnabled(false);
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		JFrame msgFrame = new JFrame();
		JOptionPane.showMessageDialog(msgFrame, "The Winner is" + winner + ".");
	}

	@Override
	public void onMoveError(String msg) {
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		refresh(board);
		setCurrentPlayerLabel();
	}

	@Override 
	public void onUndoNotPossible() {
		JFrame msgFrame = new JFrame();
		JOptionPane.showMessageDialog(msgFrame, "Nothing to undo"); 
	}
	 
	public void refresh(ReadOnlyBoard board) {	
		for(int i = 0; i < numberColums; i++) {
			for (int j = 0; j < numberRows; j++) {
				Counter colour = board.getPosition(i+1, j+1);
				
				switch(colour) {
				case BLACK:
					buttons[i][j].setBackground(new Color(0,0,0));
					buttons[i][j].setIcon(new ImageIcon(Resources.RESOURCES_URL + "black.png"));
					break;
				case WHITE:
					buttons[i][j].setBackground(new Color(255,255,255));
					buttons[i][j].setIcon(new ImageIcon(Resources.RESOURCES_URL + "white.png"));
					break;
				default:
					buttons[i][j].setBackground(new Color(207,207,207));
					buttons[i][j].setIcon(new ImageIcon(Resources.RESOURCES_URL + "empty.png"));
					break;
				}
			}
		}
	}
	
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) { 
		numberRows = board.getHeight();
		numberColums = board.getWidth();
		buttons = new JButton[numberColums][numberRows];
		GridBagConstraints c = new GridBagConstraints(); 
		
		middlePanelLeft.removeAll(); // remove previous buttons from grid layout

		setCurrentPlayerLabel(); // Update player color on the label
		
		for(int i = 0; i < numberColums; i++) {
			for (int j = 0; j < numberRows; j++) {
				c = Resources.configureConstraint(GridBagConstraints.BOTH, i, j, 1, 1); // gridX, gridY, weightX, weightY 
				buttons[i][j] = createButton(i, j); 
				middlePanelLeft.add(buttons[i][j],c); // All to empty
			}
		}
		this.active = true;
		middlePanelLeft.revalidate();
		
		refresh(board);
	}

	// Setea el color de la label
	public void setCurrentPlayerLabel() {
		String colorStr = "" + wController.getGame().getTurn(); 
		currentColor.setText(colorStr + " to Move");
	}
	
	
}
