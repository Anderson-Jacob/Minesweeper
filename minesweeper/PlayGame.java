package minesweeper;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Sets up and runs the minesweeper game
 */
public class PlayGame {
	private static SweeperFrame game;
	private static int mines;
	private static int rows;
	private static int cols;
	private static int defaultOption;
	public static void main(String[] args) {
		defaultOption = 0;
		setEasy();
		reset();
	}
	public static void reset() {
		String[] options = {"Easy", "Medium", "Hard", "Custom"};
		Object initialValue = options[defaultOption];
		String result = (String)JOptionPane.showInputDialog(null, "Create Game", "Select Difficulty", JOptionPane.QUESTION_MESSAGE, new ImageIcon(""), options, initialValue);
		if(result == null)
			return;
		switch(result) {
		case "Easy":{
			defaultOption = 0;
			setEasy();
			break;
		}
		case "Medium":{
			defaultOption = 1;
			setMedium();
			break;
		}
		case "Hard":{
			defaultOption = 2;
			setHard();
			break;
		}
		case "Custom":{
			defaultOption = 3;
			if(!setCustom())
				return;
			break;
		}
		}
		game = new SweeperFrame(mines, rows, cols);
		game.setVisible(true);
	}
	private static boolean valid(int mineCount, int rowCount, int colCount){
		return mineCount>0 && rowCount>2 && colCount>2 && mineCount<rowCount*colCount;
	}
	private static void setEasy() {
		mines = 10;
		rows = 8;
		cols = 10;
	}
	private static void setMedium() {
		mines = 40;
		rows = 14;
		cols = 18;
	}
	private static void setHard() {
		mines = 99;
		rows = 20;
		cols = 24;
	}
	/**
	 * Allows the user to create a custom sized minesweeper game
	 * @return false if the user exits early, otherwise true
	 */
	private static boolean setCustom() {
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(1);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    JFormattedTextField mineField = new JFormattedTextField(formatter);
	    JFormattedTextField rowField = new JFormattedTextField(formatter);
	    JFormattedTextField colField = new JFormattedTextField(formatter);	
		Object[] inputFields = {"Enter Mine Count (minimum: 1)", mineField,
				 				"Enter Row Count (minimum: 3)", rowField,
				 				"Enter Column Count (minimum: 3)", colField};
	    int option = JOptionPane.showConfirmDialog(null, inputFields, "Set Custom Inputs:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if(option!=JOptionPane.OK_OPTION) {
	    	return false;
	    }
	    int inputMines = Integer.parseInt(mineField.getText());
	    int inputRows = Integer.parseInt(rowField.getText());
	    int inputColumns = Integer.parseInt(colField.getText());
	    
	    //continue requesting until user enters a valid input
	    while(!valid(inputMines, inputRows, inputColumns)) {
	    	option = JOptionPane.showConfirmDialog(null, inputFields, "Invalid input(s), please retry:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    if(option!=JOptionPane.OK_OPTION) {
		    	return false;
		    }
		    inputMines = Integer.parseInt(mineField.getText());
		    inputRows = Integer.parseInt(rowField.getText());
		    inputColumns = Integer.parseInt(colField.getText());
	    }
	    
	    mines = inputMines;
	    rows = inputRows;
	    cols = inputColumns;
	    return true;
	}
}
