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
	private int dimX = Resources.DIMX_CONNECT4; // TODO: Hay que hacer que cambia las dimensiones...
	private int dimY = Resources.DIMY_CONNECT4;
	private JPanel mainPanel, topPanel, bottomPanel, leftMargin, rightMargin, middlePanelLeft, middlePanelRight, middlePanel;
	private JTextArea inputTxt;
	private JComboBox<GameType> Cbox;
	private WindowController wController;
	private boolean active = false;
	private JButton[][] buttons;
	private int numberColums;
	private int numberRows;
	
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
		JButton undoButton = new JButton("Undo");
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
		
		// Dimension gravity Change
		JPanel gravityDimensionsPanel = new JPanel();
		gravityDimensionsPanel.setBackground(new Color(0,0,0,1));
		gravityDimensionsPanel.setPreferredSize(new Dimension(10,10));
		middlePanelRightTop.add(gravityDimensionsPanel, c);
		
		c = configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1.5);  // characteristics of the topDark part
		middlePanelRight.add(middlePanelRightTop,c);
		
		////////////// MIDDEL PANEL RIGHT BOTTOM ////////////
		
		JPanel middlePannelRightBottom = new JPanel(new GridBagLayout());
		middlePannelRightBottom.setBackground(new Color(0,0,0,30));
		middlePannelRightBottom.setPreferredSize(new Dimension(50,50));
		
		//COMBOBOX
		Cbox = new JComboBox<GameType>(names);
		Cbox.setSelectedIndex(0); 
		Cbox.setFont(new Font("Arial", Font.BOLD, 24)); 
		Cbox.setBorder(new EmptyBorder(10, 10, 10, 10));
		c = configureConstraint(GridBagConstraints.NONE, 0, 1, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(Cbox,c);
		
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(new Color(238, 238, 238, 80)); 
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
				GameType name = (GameType)Cbox.getSelectedItem();
				//estos valores de 8, 8 deben ser tomados del usuario
				wController.changeGame(name, 8, 8);
			}
		});
		
		c = configureConstraint(GridBagConstraints.BOTH, 0, 1, 1, 1); // For the bottom of the dark side panel 
		middlePanelRight.add(middlePannelRightBottom,c);
		
		c = configureConstraint(GridBagConstraints.BOTH, 1, 0, .75, .75); //the characteristics of dark in general
		middlePanel.add(middlePanelRight, c);
		
		
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600)); 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
	
	/**
	 * Auxiliary Functions 
	 */

	private JPanel createPanel(Color color, int x, int y){
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
		// Counter colour = wController.getGame().getBoard().getPosition(i, j);
 /*
		System.out.println("I " + i);
		System.out.println("J " + j);
		System.out.println("Counter " + colour);
		System.out.println("=========");
*/
		//if (this.wController.get  .getClass() == ComplicaRules.class || colour == Counter.EMPTY) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (active) {
						wController.makeMove(i + 1, j + 1, wController.getGame().getTurn());
						wController.getGame().getBoard().printBoard(); 
						middlePanelLeft.revalidate();
					}	
				}
			});
		// }
		return button;	
		
		/*
		JButton x = new JButton(v == 0 ? "" : v + ""); 
		x.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (active) wController.makeMove(i + 1, j + 1, wController.getGame().getTurn()); 
			} 
		});
		return x; 
		*/
	}


	
	/*
	 * Callback functions
	 * (non-Javadoc)
	 * @see tp.pr4.logic.GameObserver#moveExecFinished(tp.pr4.logic.ReadOnlyBoard, tp.pr4.logic.Counter, tp.pr4.logic.Counter)
	 */
	
	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		refresh();
	}

	@Override
	public void moveExecStart(Counter player) {
		// System.out.println("Hola, cómo estás?");
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
		refresh();
	}

	@Override
	public void onUndoNotPossible() {
		// NOTHING TO UNDO
		
	}
	
	
	public void refresh() {
		for(int i = 0; i < numberColums; i++) {
			for (int j = 0; j < numberRows; j++) {
				Counter colour = wController.getGame().getBoard().getPosition(i+1, j+1);
				
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
 
		for(int i = 0; i < numberColums; i++) {
			for (int j = 0; j < numberRows; j++) {
 
				c = configureConstraint(GridBagConstraints.BOTH, i, j, 1, 1); // gridX, gridY, weightX, weightY 
				buttons[i][j] = createButton(i, j); 
				middlePanelLeft.add(buttons[i][j],c); // All to empty
				
				/*
				if (board.getPosition(i, j) == Counter.EMPTY) {
					b.setText("EMPTY");
					b.setBackground(new Color(207,207,207)); 
					middlePanelLeft.add(b,c); // All to empty
				
				} else if (board.getPosition(i, j) == Counter.WHITE) {
					b.setText("WHITE");
					b.setBackground(new Color(255,255,255));
					middlePanelLeft.add(b,c); // All to empty
				} else if (board.getPosition(i, j) == Counter.BLACK) {
					b.setText("BLACK");
					b.setBackground(new Color(0,0,0));
					middlePanelLeft.add(b,c); // All to empty
				}
				*/
			}
		}
		this.active = true;
		middlePanelLeft.revalidate();
	}
	
	/*

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) { 
		int rows = board.getWidth(), cols = board.getHeight();
		GridBagConstraints c = new GridBagConstraints();
		buttons = new JButton[rows][cols];
		
		this.removeAll();
		middlePanelLeft.removeAll(); // remove previous buttons from grid layout
 
		for(int i = 0; i < rows; i++) {
			// c.gridx = i;
			for (int j = 0; j < cols; j++) {
				c = configureConstraint(GridBagConstraints.BOTH, i, j, 1, 1); // gridX, gridY, weightX, weightY 
				buttons[i][j] = createButton(i, j);
				// c.gridy = j;
				this.add(buttons[i][j], c);
				
				
				if (board.getPosition(i, j) == Counter.EMPTY) {
					buttons[i][j].setText("EMPTY");
					buttons[i][j].setBackground(new Color(207,207,207)); 			
				} else if (board.getPosition(i, j) == Counter.WHITE) {
					buttons[i][j].setText("WHITE");
					buttons[i][j].setBackground(new Color(255,255,255));
				} else if (board.getPosition(i, j) == Counter.BLACK) {
					buttons[i][j].setText("BLACK");
					buttons[i][j].setBackground(new Color(0,0,0));
				}
				middlePanelLeft.add(buttons[i][j],c); // All to empty
				 
				 
				 
				 //// NOT USED
				c = configureConstraint(GridBagConstraints.BOTH, i, j, 1, 1); // gridX, gridY, weightX, weightY 
				
				Counter v = board.getPosition(i + 1, j + 1); 
				b = createButton(i, j, v);
								
				if (board.getPosition(i, j) == Counter.EMPTY) {
					b.setText("EMPTY");
					b.setBackground(new Color(207,207,207)); 
					middlePanelLeft.add(b,c); // All to empty
				
				} else if (board.getPosition(i, j) == Counter.WHITE) {
					b.setText("WHITE");
					b.setBackground(new Color(255,255,255));
					middlePanelLeft.add(b,c); // All to empty
				} else if (board.getPosition(i, j) == Counter.BLACK) {
					b.setText("BLACK");
					b.setBackground(new Color(0,0,0));
					middlePanelLeft.add(b,c); // All to empty
				} 
				
			}
		}
		this.revalidate();
		active = true;
	}
	*/

}
