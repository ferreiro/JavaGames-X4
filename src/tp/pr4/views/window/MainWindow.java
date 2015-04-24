package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;

//import javafx.scene.image.Image;






import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import com.sun.glass.ui.Size;






import tp.pr4.Resources.Resources;
import tp.pr4.control.GameTypeFactory;
import tp.pr4.control.WindowController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.GameType;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	private JPanel mainPanel, topPanel, bottomPanel, leftMargin, rightMargin, 
				   middlePanelLeft, middlePanelRight, middlePanel, changeDimensions,
				   bottomInfoPanel;
	private JTextField txtFieldRow, txtFieldColumn;
	private JComboBox<GameType> Cbox;
	private WindowController wController;
	private boolean active = false;
	private JButton[][] buttons;
	private int numberColums;
	private int numberRows;
	private JLabel currentColor;
	private JButton undoButton;
	
	public MainWindow(GameTypeFactory gType, Game game) {
		super(); 
		this.wController = new WindowController(gType, game); 
		initGUI(); 
	}
	
	private void initGUI() { 
		GameType names[] = { GameType.connect4, GameType.complica, GameType.Gravity }; 
		mainPanel = new JPanel(new BorderLayout()); 
		
		/////////////////// HEADER AND BOTTOM //////////////////
		
		topPanel = createPanel(new Color(255, 255, 255), 10, 70);
		mainPanel.add(topPanel, BorderLayout.PAGE_START); 

		JButton logoHeader = new JButton(); // Logo
		logoHeader = createAuxButton(300,  60, "", Resources.RESOURCES_URL+"/logo.png", new Color(255,255,255,1), false );
		topPanel.add(logoHeader, configureConstraint(GridBagConstraints.BOTH, 1, 2, 0.1, 0.1)); // gridX, gridY, weightX, weightY );
		
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
		c = configureConstraint(GridBagConstraints.BOTH, 0, 0, 1.5, 1); // gridX, gridY, weightX, weightY 
		middlePanel.add(middlePanelLeft, c);
		
		
		// MIDDLE PANEL RIGHT
		middlePanelRight = new JPanel(new GridBagLayout());
		middlePanelRight.setBackground(new Color(222, 222, 222));
		middlePanelRight.setPreferredSize(new Dimension(20,30));
		 
		////////////// MIDDEL PANEL RIGHT TOP ////////////
		
		JPanel middlePanelRightTop = new JPanel(new GridBagLayout());
		middlePanelRightTop.setBackground(new Color(0,0,0,0));
		middlePanelRightTop.setPreferredSize(new Dimension(10,10));

		// RANDOM USER
		JButton randomButton = new JButton();
		randomButton = createAuxButton(120,  100, "Random", Resources.RESOURCES_URL + "random.png", new Color(255,255,0), true); 
		c = configureConstraint(GridBagConstraints.CENTER, 0, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(randomButton,c);
		
		randomButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				wController.randomMove();
			}
		});
		
		// UNDO BUTTON
		undoButton = new JButton("Undo");
		undoButton = createAuxButton(230,  100, "Undo", Resources.RESOURCES_URL + "undo.png", new Color(255,255,0), true);  
		c = configureConstraint(GridBagConstraints.CENTER, 1, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(undoButton,c);
		undoButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				wController.undo();
			}
		});

		// RESET BUTTON
		JButton resetButton = new JButton();
		resetButton = createAuxButton(120,  100, "Restart", Resources.RESOURCES_URL + "reset.png", new Color(255,255,0), true); 
		c = configureConstraint(GridBagConstraints.NONE, 2, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(resetButton,c);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wController.reset();
 			}
		});
		
		c = configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1.5);  // characteristics of the topDark part
		middlePanelRight.add(middlePanelRightTop,c);
		
		////////////// MIDDEL PANEL RIGHT BOTTOM ////////////
		
		JPanel middlePannelRightBottom = new JPanel(new GridBagLayout());
		middlePannelRightBottom.setBackground(new Color(0,0,0,30));
		middlePannelRightBottom.setPreferredSize(new Dimension(50,50));	
		
		JPanel anotherPanel = new JPanel(); 
		anotherPanel.setVisible(true);
		anotherPanel.setBackground( new Color(0, 0, 0, 30));
		anotherPanel.setPreferredSize(new Dimension(200, 100));
		c = configureConstraint(GridBagConstraints.BOTH, 0, 1, .70, 0); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(anotherPanel,c);
		
		// COMBOBOX
		Cbox = new JComboBox<GameType>(names);
		Cbox.setSelectedIndex(0); 
		Cbox.setFont(new Font("Arial", Font.BOLD, 24)); 
		Cbox.setBorder(new EmptyBorder(10, 10, 10, 10));
		Cbox.setLocation(0, 40);
		c = configureConstraint(GridBagConstraints.NONE, 0, 1, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		anotherPanel.add(Cbox,c);

		Cbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameType name = (GameType) Cbox.getSelectedItem();
				changeDimensions.setVisible(false);
				if (name.equals(GameType.Gravity)) changeDimensions.setVisible(true);
			}
		});
		
		// INPUTS
		
		changeDimensions = new JPanel();
		changeDimensions.setVisible(false);
		c = configureConstraint(GridBagConstraints.NONE, 0, 1, 1, 0.3); // gridX, gridY, weightX, weightY 
		anotherPanel.add(changeDimensions,c);

		txtFieldColumn = new JTextField();
		txtFieldColumn.setVisible(true);
		c = configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.3);
		txtFieldColumn.setPreferredSize(new Dimension(40, 40));
		changeDimensions.add(txtFieldColumn,c);	
		
		txtFieldRow = new JTextField();
		txtFieldRow.setVisible(true);
		c = configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.3);
		txtFieldRow.setPreferredSize(new Dimension(40, 40));
		changeDimensions.add(txtFieldRow,c);
		
		JPanel blankPanel = new JPanel();
		blankPanel.setVisible(true);
		c = configureConstraint(GridBagConstraints.BOTH, 0, 1, .70, 0); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(blankPanel,c);
		
		//BUTTON FOR CHANGING
		JButton changeButton = createAuxButton(230,  100, "Change", Resources.RESOURCES_URL + "check.png", new Color(62,218,103), false);
		changeButton.setForeground(Color.WHITE);
		changeButton.setFont(new Font("Arial", Font.BOLD, 24));
		c = configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(changeButton,c);
				
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameType type = (GameType)Cbox.getSelectedItem();
				
				if (type.equals(GameType.Gravity)) {
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
		
		c = configureConstraint(GridBagConstraints.BOTH, 0, 1, 1, 1); // For the bottom of the dark side panel 
		middlePanelRight.add(middlePannelRightBottom,c);
		
		c = configureConstraint(GridBagConstraints.BOTH, 1, 0, .75, .75); //the characteristics of dark in general
		
		middlePanel.add(middlePanelRight, c);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
		// Panel for showing color of the current Player
		
		bottomInfoPanel = createPanel(new Color(255, 255, 255), 100, 50);
		mainPanel.add(bottomInfoPanel, BorderLayout.PAGE_END);
		
		String colorStr = "" + wController.getGame().getTurn(); 
		currentColor = new JLabel(colorStr + " to Move");
		currentColor.setBackground(new Color(0,0,0,0)); 
		currentColor.setPreferredSize(new Dimension(200, 45));
		currentColor.setFont(new Font("Arial", Font.BOLD, 20));
		bottomInfoPanel.add(currentColor);
		
		this.setVisible(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600)); 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
	
	/**
	 * Auxiliary Functions 
	 */

	private JPanel createPanel(Color color, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x,y));
		panel.setVisible(true); 
		return panel;
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
	
	private JButton createAuxButton(int w, int h, String name, String fileName, Color c, boolean border) {
		JButton b = new JButton();
		b.setBackground(c);
		b.setPreferredSize(new Dimension(w, h)); 
		if (fileName != "") b.setIcon(new ImageIcon(fileName));
		if (name != "") b.setText(name);
		if (!border) 	b.setBorder(null);
		return b; 
	}
	
	private JButton createButton(final int i, final int j) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(40, 40));
		button.setBackground(new Color(207,207,207));
		button.setIcon(new ImageIcon(Resources.RESOURCES_URL + "empty.png"));
		
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (active) {
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
		// Update player color on the label
		String colorStr = "" + wController.getGame().getTurn(); 
		currentColor.setText(colorStr + " to Move");
	}

	@Override
	public void moveExecStart(Counter player) {

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		JFrame msgFrame = new JFrame();
		JOptionPane.showMessageDialog(msgFrame, "The Winner is" + winner + ".");//TODO method toString and finish the function
	}

	@Override
	public void onMoveError(String msg) {
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		refresh(board);
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
		JButton b;
		
		middlePanelLeft.removeAll(); // remove previous buttons from grid layout

		// Update player color on the label
		String colorStr = "" + wController.getGame().getTurn(); 
		currentColor.setText(colorStr + " to Move");
		
		
		for(int i = 0; i < numberColums; i++) {
			for (int j = 0; j < numberRows; j++) {
				c = configureConstraint(GridBagConstraints.BOTH, i, j, 1, 1); // gridX, gridY, weightX, weightY 
				buttons[i][j] = createButton(i, j); 
				middlePanelLeft.add(buttons[i][j],c); // All to empty
			}
		}
		this.active = true;
		middlePanelLeft.revalidate();
	}
	
}
