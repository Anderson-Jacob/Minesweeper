package minesweeper;

import java.util.Random;
import minesweeper.SweeperButton.CellType;
/**
 * Represents the minesweeper game board
 */
public class Game {
	private int mineCount;
	private SweeperButton[][] board;
	private int squaresLeft;
	private boolean lost;
	private int turns;
	private boolean generated;
	
	/**
	 * Creates the board for the minesweeper game
	 * @param mines - the number of mines
	 * @param rows
	 * @param columns
	 */
	public Game(int mines, int rows, int columns) {
		turns = 0;
		mineCount = mines;
		board = new SweeperButton[rows][columns];
		squaresLeft = (rows*columns)-mineCount;
		lost = false;
		generated = false;
		for(int i = 0; i<board.length; i++)
			for(int j = 0; j<board[0].length; j++)
				board[i][j] = new SweeperButton(CellType.ZERO, i, j);
	}
	private boolean isBanned(int row, int col, int bannedRow, int bannedCol) {
		return Math.abs(bannedRow-row)<=1 && Math.abs(bannedCol-col)<=1;
	}
	/**
	 * Places all of the mines on this board and adjusts counts accordingly.
	 * Ensures that the first square that was clicked is a 0 square
	 * 
	 * @param rowClicked - the row that was clicked first
	 * @param colClicked - the column that was clicked first
	 */
	public void generate(int rowClicked, int colClicked) {
		if(generated)
			return;
		int minesLeft = mineCount;
		Random rng = new Random();
		while(minesLeft!=0) {
			int row = rng.nextInt(board.length);
			int col = rng.nextInt(board[0].length);
			if(board[row][col].isMine()||isBanned(row,col,rowClicked,colClicked))
				continue;
			board[row][col].setMine();
			minesLeft--;
			for(int i = -1; i<2; i++) {
				for(int j = -1; j<2; j++) {
					if(valid(row+i, col+j)) {
						board[row+i][col+j].incrementMineCount();
					}
				}
			}
		}
		generated = true;
	}
	/**
	 * Reveals this square, showing the mine count underneath
	 * @param row - the row of the cell to reveal
	 * @param col - the column of the cell to reveal
	 */
	public void revealSquare(int row, int col){
		turns++;
		if(valid(row,col) && !board[row][col].revealed() && !board[row][col].flagged()) {
			if(turns==1 && board[row][col].isMine()) {
				
			}
			CellType value = board[row][col].reveal();
			squaresLeft--;
			if(value == CellType.MINE)
				lost = true;
			if(value == CellType.ZERO) {
				revealSquare(row, col-1);
				revealSquare(row, col+1);
				revealSquare(row+1, col);
				revealSquare(row-1, col);
				revealSquare(row-1, col-1);
				revealSquare(row-1, col+1);
				revealSquare(row+1, col-1);
				revealSquare(row+1, col+1);
			}
		}
	}
	/**
	 * @return true if the game has been won otherwise false
	 */
	public boolean win() {
		return !lost && squaresLeft==0;
	}
	/**
	 * @return true if the game has been lost otherwise false
	 */
	public boolean lose() {
		return lost;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @return true if this is a valid cell in the grid, otherwise false
	 */
	private boolean valid(int row, int col) {
		if(row<0 || row==board.length || col<0 || col==board[row].length)
			return false;
		return true;
	}
	/**
	 * @return the length of the board
	 */
	public int getLength() {
		return board.length;
	}
	/**
	 * @return the width of the board
	 */
	public int getWidth() {
		return board[0].length;
	}
	/**
	 * Returns the SweeperButton at the given coordinates in the grid
	 * @param row
	 * @param col
	 * @return the SweeperButton at the given coordinates in the grid
	 */
	public SweeperButton getButton(int row, int col) {
		return board[row][col];
	}
}
