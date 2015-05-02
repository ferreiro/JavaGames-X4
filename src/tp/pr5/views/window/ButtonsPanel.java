package tp.pr5.views.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr5.Resources.Resources;
import tp.pr5.control.WindowController;

public class ButtonsPanel  extends JFrame{

	JButton undoButton, resetButton, randomButton;
	GridBagConstraints c = new GridBagConstraints();
	private WindowController wController;
	
	public ButtonsPanel(WindowController ctr){
		super();
		wController = ctr;
		initGUI();
	}
	
	public void initGUI(){
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
	}
}
