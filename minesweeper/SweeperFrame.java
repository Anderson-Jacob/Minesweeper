package minesweeper;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Frame for the Minesweeper game.
 * 
 * @author  Jacob Anderson
 * @version February 18th 2024
 */
public class SweeperFrame extends JFrame  implements MouseListener{
	private static final long serialVersionUID = 1L;
	private Game board;
	/**
	 * Creates a MineSweeper GUI.
	 */
	public SweeperFrame(int mines, int rows, int cols) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//initialize the board and fill it with SweeperButtons
		board = new Game(mines, rows, cols);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j<cols; j++) {
				gbc.gridx = j;
				gbc.gridy = i;
				SweeperButton button = board.getButton(i,j);
				panel.add(button, gbc);
			}
			
		}
		this.setTitle("Minesweeper");
		this.setContentPane(topPanel);
		this.pack();
		//add icons and mouse listeners to all of the cells
		SweeperButton.createScaledIcons(board.getButton(0, 0).getSize());
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j<cols; j++) {
				SweeperButton button = board.getButton(i,j);
				button.setDefaultIcon();
				button.addMouseListener(this);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int modifiers = e.getButton();
		SweeperButton button = (SweeperButton)e.getSource();
	    if (modifiers == 1) {
	    	//left mouse button pressed
	        if(!button.revealed()) {
	        	board.revealSquare(button.row, button.col);
	        	if(board.lose()) {
	        		loseGame();
	        		return;
	        	}
	        	if(board.win()) {
	        		winGame();
	        	}
	        }
	    }
	    else{
	        //right/middle mouse button pressed
	        button.toggleFlag();
	    }
		
	}
	/**
	 * Handles events for when the user wins the game
	 */
	private void winGame() {
		String[] options = {"Quit", "Restart"};
		Object initialValue = options[0];
		int result = JOptionPane.showOptionDialog(this, null, "You Win!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, initialValue);
		if(result==1) {
			PlayGame.reset();
		}
		this.dispose();
		}
	/**
	 * Handles events for when the user loses the game
	 */
	private void loseGame() {
		String[] options = {"Quit", "Restart"};
		Object initialValue = options[0];
		int result = JOptionPane.showOptionDialog(this, null, "You Lost!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, initialValue);
		if(result==1) {
			PlayGame.reset();
		}
		this.dispose();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
}