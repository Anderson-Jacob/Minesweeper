package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Represents a cell in the minesweeper grid
 */
public class SweeperButton extends JButton {
	private static final long serialVersionUID = 1L;
	private static ImageIcon scaledDefault;
	private static ImageIcon scaledFlag;
	private static ImageIcon scaledMine;
	private static ImageIcon scaledZero;
	private static ImageIcon scaledOne;
	private static ImageIcon scaledTwo;
	private static ImageIcon scaledThree;
	private static ImageIcon scaledFour;
	private static ImageIcon scaledFive;
	private static ImageIcon scaledSix;
	private static ImageIcon scaledSeven;
	private static ImageIcon scaledEight;
	private boolean flagged;
	private boolean revealed;
	public final int row;
	public final int col;
	private CellType value;
	/**
	 * Creates a new SweeperButton
	 * @param value the value of the cell (number of mines nearby, or MINE if it is a mine itself)
	 * @param row the row it is located in the grid
	 * @param col the column it is located in the grid
	 */
	public SweeperButton(CellType value, int row, int col) {
		super();
		flagged = false;
		revealed = false;
		this.row = row;
		this.col = col;
		this.value = value;
		this.setBackground(Color.BLACK);
		this.setEnabled(true);
	}
	/**
	 * Sets the icon of the button to it's default icon
	 * Ensure that createScaledIcons has been called before calling this method
	 */
	public void setDefaultIcon() {
		this.setIcon(scaledDefault);
	}
	/**
	 * Toggles whether or not the cell is flagged,
	 * unless the cell has already been revealed
	 * 
	 * @return false if revealed, otherwise true
	 */
	public boolean toggleFlag() {
		if(revealed)
			return false;
		flagged = !flagged;
		if(flagged) {
			this.setIcon(scaledFlag);

		}else{
			this.setIcon(scaledDefault);
		}
		return true;
	}
	/**
	 * 
	 * @return true if there is a flag on this cell, otherwise false
	 */
	public boolean flagged() {
		return flagged;
	}
	/**
	 *
	 * @return true if this cell has been revealed, otherwise false
	 */
	public boolean revealed() {
		return revealed;
	}
	/**
	 * Reveals what is under the cell, unless it is currently flagged
	 * @return the value of the cell, or CellType.FLAGGED if it is flagged
	 */
	public CellType reveal() {
		if(flagged)
			return CellType.FLAGGED;
		revealed = true;
		switch(value) {
			case MINE:{
				this.setIcon(scaledMine);
				break;
			}
			case ZERO:{
				this.setIcon(scaledZero);
				break;
			}
			case ONE:{
				this.setIcon(scaledOne);
				break;
			}
			case TWO:{
				this.setIcon(scaledTwo);
				break;
			}
			case THREE:{
				this.setIcon(scaledThree);
				break;
			}
			case FOUR:{
				this.setIcon(scaledFour);
				break;
			}
			case FIVE:{
				this.setIcon(scaledFive);
				break;
			}
			case SIX:{
				this.setIcon(scaledSix);
				break;
			}
			case SEVEN:{
				this.setIcon(scaledSeven);
				break;
			}
			case EIGHT:{
				this.setIcon(scaledEight);
			}
			default:
		}
		return value;
	}
	/**
	 * Increases the value (number of mines nearby this cell) by one
	 */
	public void incrementMineCount() {
		switch(value) {
		case ZERO:{ 
			value = CellType.ONE;
			break;
			}
		case ONE:{ 
			value = CellType.TWO;
			break;
			}
		case TWO:{ 
			value = CellType.THREE;
			break;
			}
		case THREE:{ 
			value = CellType.FOUR;
			break;
			}
		case FOUR:{ 
			value = CellType.FIVE;
			break;
			}
		case FIVE:{ 
			value = CellType.SIX;
			break;
			}
		case SIX:{ 
			value = CellType.SEVEN;
			break;
			}
		case SEVEN:{ 
			value = CellType.EIGHT;
			break;
			}
		default:
			break;
		}
	}
	/**
	 * Turns this cell into a mine
	 */
	public void setMine() {
		value = CellType.MINE;
	}
	/**
	 * 
	 * @return true if this cell is a mine, otherwise false
	 */
	public boolean isMine() {
		return value == CellType.MINE;
	}
	public static enum CellType{
		MINE, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, FLAGGED
	}
	/**
	 * Creates scaled versions of all of the SweeperButton's image icons to fit a given dimension
	 * **Must be called before any icons are put on a button**
	 * @param size
	 */
	public static void createScaledIcons(Dimension size) {
		ImageIcon regular = new ImageIcon("src/minesweeper/IconsForCells/default.png");
		ImageIcon flag = new ImageIcon("src/minesweeper/IconsForCells/flag.png");
		ImageIcon mine = new ImageIcon("src/minesweeper/IconsForCells/mine.png");
		ImageIcon zero = new ImageIcon("src/minesweeper/IconsForCells/zero.png");
		ImageIcon one = new ImageIcon("src/minesweeper/IconsForCells/one.png");
		ImageIcon two = new ImageIcon("src/minesweeper/IconsForCells/two.png");
		ImageIcon three = new ImageIcon("src/minesweeper/IconsForCells/three.png");
		ImageIcon four = new ImageIcon("src/minesweeper/IconsForCells/four.png");
		ImageIcon five = new ImageIcon("src/minesweeper/IconsForCells/five.png");
		ImageIcon six = new ImageIcon("src/minesweeper/IconsForCells/six.png");
		ImageIcon seven = new ImageIcon("src/minesweeper/IconsForCells/seven.png");
		ImageIcon eight = new ImageIcon("src/minesweeper/IconsForCells/eight.png");
		
		scaledDefault = scaleImage(regular, size);
		scaledFlag = scaleImage(flag, size);
		scaledMine = scaleImage(mine, size);
		scaledZero = scaleImage(zero, size);
		scaledOne = scaleImage(one, size);
		scaledTwo = scaleImage(two, size);
		scaledThree = scaleImage(three, size);
		scaledFour = scaleImage(four, size);
		scaledFive = scaleImage(five, size);
		scaledSix = scaleImage(six, size);
		scaledSeven = scaleImage(seven, size);
		scaledEight = scaleImage(eight, size);
		
	}
	private static ImageIcon scaleImage(ImageIcon icon, Dimension size) {
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(size.width, size.height,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(30,30);
	}
}
