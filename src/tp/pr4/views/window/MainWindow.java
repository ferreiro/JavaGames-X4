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
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver, KeyListener {
	private int dimX = Resources.DIMX_CONNECT4; // TODO: Hay que hacer que cambia las dimensiones...
	private int dimY = Resources.DIMY_CONNECT4;
	private JPanel mainPanel, topPanel, bottomPanel, leftMargin, rightMargin, middlePanelLeft, middlePanelRight, middlePanel;
	private JTextArea inputTxt;
	
	public MainWindow() {
		super(); 
		initGUI();
	}
	
	private void initGUI() {
		JComboBox<String> Cbox;
		String names[] = { "Connect4", "Complica", "Gravity" }; 
		mainPanel = new JPanel(new BorderLayout()); 
		
		/////////////////// HEADER AND BOTTOM //////////////////
		
		topPanel = createPanel(new Color(255, 255, 255), 10, 70);
		mainPanel.add(topPanel, BorderLayout.PAGE_START); 

		JButton logoHeader = new JButton(); // Logo
		logoHeader = createButton(200,  50, "Logotipo", "", new Color(0,0,0,0), false );  
		topPanel.add(logoHeader, configureConstraint(GridBagConstraints.BOTH, 1, 2, 0.1, 0.1)); // gridX, gridY, weightX, weightY );

		bottomPanel = createPanel(new Color(230, 230, 230), 70, 130);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		inputTxt = new JTextArea("Dude, write something here...");
		inputTxt.setLayout(null);
		Font bigFont = inputTxt.getFont().deriveFont(Font.PLAIN, 40);
		inputTxt.setFont(bigFont);
		inputTxt.setSize(300, 200);
		inputTxt.setBackground(null); 
		inputTxt.setMargin( new Insets(35,10,10,10) );
		bottomPanel.add(inputTxt);
		
		inputTxt.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {

				if ((e.getKeyChar()) == 10) {
					String text = inputTxt.getText().trim();
					text.toLowerCase(new Locale(text));

					if (text.equals("make a move")) { 
						JCheckBox check = new JCheckBox();
						JOptionPane.showMessageDialog(check, "PUTAA");//TODO method toString and finish the function
						JCheckBox check1 = new JCheckBox();
						JOptionPane.showMessageDialog(check1, "Pegartillo la chupa");//TODO method toString and finish the function
						JCheckBox check2 = new JCheckBox();
						JOptionPane.showMessageDialog(check2, "Nah, es coña. Te quierohh");//TODO method toString and finish the function

						JCheckBox check3 = new JCheckBox();
						JOptionPane.showMessageDialog(check3, "JORGE ES DIOS POR CREAR ESTO TAN AGUESOME");//TODO method toString and finish the function
					}			
					
					inputTxt.setText(null);
							
				} 
			} 
			public void keyPressed(KeyEvent e) {
			}
		});
		inputTxt.addMouseListener(new MouseListener() {
			 
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				inputTxt.setText(null);
			} 
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				inputTxt.setText(null);
			}
		});

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
		middlePanelLeft = new JPanel(new GridLayout(dimX, dimY, 2, 2));
		middlePanelLeft.setBackground(Color.LIGHT_GRAY);
		middlePanelLeft.setSize(120,120);
		//lo que es dibujar los circulos no es aqui en la inicialización
		
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
		randomButton = createButton(120,  100, "Random", Resources.RESOURCES_URL + "random.png", new Color(255,255,0), true); 
		c = configureConstraint(GridBagConstraints.CENTER, 0, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(randomButton,c);
		
		// UNDO BUTTON
		JButton undoButton = new JButton("Undo");
		undoButton = createButton(230,  100, "Undo", Resources.RESOURCES_URL + "undo.png", new Color(255,255,0), true);  
		c = configureConstraint(GridBagConstraints.CENTER, 1, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(undoButton,c);

		// RESET BUTTON
		JButton resetButton = new JButton();
		resetButton = createButton(120,  100, "Restart", Resources.RESOURCES_URL + "reset.png", new Color(255,255,0), true); 
		c = configureConstraint(GridBagConstraints.NONE, 2, 0, 0.1, 0.3); // gridX, gridY, weightX, weightY 
		middlePanelRightTop.add(resetButton,c);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO Hacer que aparezca una popup con el mensaje: Estás seguro de que quieres resetear?
				JCheckBox check = new JCheckBox();
				JOptionPane.showMessageDialog(check, "Are you sure you want to close");//TODO method toString and finish the function
 			}
		});
		
		// CHANGE COLOR // TODO : IDEA
		JButton changeColor = new JButton();
		changeColor = createButton(100, 55, "Change Color", Resources.RESOURCES_URL + "undo.png", new Color(255,255,0), true);   
		c = configureConstraint(GridBagConstraints.NONE, 3, 0, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		// middlePanelRightTop.add(changeColor,c);

		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser spinner = new JColorChooser();
				topPanel.add(spinner);  
			}
		}); 
		
		c = configureConstraint(GridBagConstraints.BOTH, 0, 0, 1, 1.5);  // characteristics of the topDark part
		middlePanelRight.add(middlePanelRightTop,c);
		
		////////////// MIDDEL PANEL RIGHT BOTTOM ////////////
		
		JPanel middlePannelRightBottom = new JPanel(new GridBagLayout());
		middlePannelRightBottom.setBackground(new Color(0,0,0,30));
		middlePannelRightBottom.setPreferredSize(new Dimension(50,50));
		
		//COMBOBOX
		Cbox = new JComboBox<String>(names);
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
		JButton changeButton = createButton(230,  100, "Change", Resources.RESOURCES_URL + "check.png", new Color(62,218,103), false);
		changeButton.setForeground(Color.WHITE);
		changeButton.setFont(new Font("Arial", Font.BOLD, 24));
		c = configureConstraint(GridBagConstraints.NONE, 0, 2, 0.1, 0.1); // gridX, gridY, weightX, weightY 
		middlePannelRightBottom.add(changeButton,c);
		
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
	
	public JButton createButton(int w, int h, String name, String fileName, Color c, boolean border) {
		JButton b = new JButton();
		b.setBackground(c);
		b.setPreferredSize(new Dimension(200, 50)); 
		if (fileName != "") b.setIcon(new ImageIcon(fileName));
		if (name != "") b.setText(name);
		if (!border) 	b.setBorder(null);
		return b; 
	}


	
	/*
	 * Callback functions
	 * (non-Javadoc)
	 * @see tp.pr4.logic.GameObserver#moveExecFinished(tp.pr4.logic.ReadOnlyBoard, tp.pr4.logic.Counter, tp.pr4.logic.Counter)
	 */
	
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
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) { 
		int x = board.getWidth(), y = board.getHeight();
		
		middlePanelLeft.removeAll(); // remove previous buttons from grid layout
		middlePanelLeft.setLayout(new GridLayout(x, y, 2, 2));
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		
		for(int i = 0; i < x; i++) 
			for (int j = 0; j < y; j++)
				middlePanelLeft.add(new JButton("X"));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
