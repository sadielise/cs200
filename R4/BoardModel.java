import java.util.ArrayList;
import java.util.Random;

public class BoardModel {

	private BoardSpace[][] board;
	private ArrayList<BoardSpace> remainingMines;
	
	private int numMines;
	
	private boolean isFirstTurn;
	private boolean won;
	private boolean lost;
	
	//difficulty values to pass to constructor
	public static final int EASY = 0;
	public static final int NORMAL = 1;
	public static final int HARD = 2;

	public BoardModel(int rows, int cols, int difficulty)
	{	
		
		//Fix having less than 3 rows
		if(rows < 3) {
			rows = 3;
		}
		
		//Fix having less than 3 columns
		if(cols < 3){
			cols = 3;
		}
		
		//Smallest board allowed is a 3x3
		assert rows >= 3 : "Expected rows >= 3, got " + rows;
		assert cols >= 3 : "Expected cols >= 3, got " + cols;
		
		//Fix non-square board
		if(rows != cols){
			cols = rows;
		}
		
		//We only use a square board (rows == cols)
		assert rows == cols: "Expected rows == cols, got rows = " + rows + " cols = " + cols;
		
		board = new BoardSpace[rows][cols];
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				board[r][c] = new BoardSpace();
			}
		}
		
		switch (difficulty) {
		case HARD:
			numMines = rows + (rows/2);
			break;
			
		case NORMAL:
			numMines = rows;
			break;
			
		case EASY:
		default:
			numMines = rows/2;
			break;
		}
		
		if(numMines == 0){
			numMines = 1;
		}
		
		//Assert that there is at least 1 mine, otherwise it's not much of a game
		assert numMines >= 1: "Expected numMines >= 1, got " + numMines;

		resetBoard();
	}
	
	public BoardModel()
	{
		this(3, 3, EASY);
	}
	
	private void resetBoard()
	{
		for (int i = 0; i < board.length-1; i++) {
			for (int j = 0; j < board[0].length-1; j++) {
				board[i][j].reset();
			}
		}
		
		isFirstTurn = true;
		won = false;
		lost = false;
	}

	private boolean unflag(int row, int col)
	{
		if (isValidMove(row, col)) {
			board[row][col].setFlagged(true);

			return true;
		}
		return false;
	}
	
	public boolean flag(int row, int col)
	{
		if (!isValidMove(row, col)) {
			return false;
		}

		if(board[row][col].isFlagged()){
			return unflag(row, col);
		}
		
		board[row][col].setFlagged(true);
		if (board[row][col].isMined()) {
			int index = remainingMines.indexOf(board[row][col]);
			remainingMines.remove(index);
		}
		
		if (remainingMines.isEmpty()) {
			won = true;
		}
		return true;
	}
	
	public int flagsRemaining()
	{
		return remainingMines.size();
	}
	
	public boolean reveal(int row, int col)
	{
		if (!isValidMove(row, col)) {
			return false;
		} else if (isFirstTurn) {
			layMines(row, col);
			isFirstTurn = false;
		}

		explore(row, col);
		
		return true;
	}
	
	private boolean onlyMinesRemain()
	{
		//check if only hidden spaces are mines
		boolean onlyMinesRemain = true;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (!board[r][c].isVisible() && !board[r][c].isMined()) {
					onlyMinesRemain = false;
					break;
				}
			}
		}
		return onlyMinesRemain;
	}
	
	private void explore(int row, int col)
	{
		if (board[row][col].isVisible()) {
			return;
		}
		
		board[row][col].setVisibility(true);
		
		if (board[row][col].adjacentMineCount() > 0) {
			return;
		}
		
		for (int r = row-1; r <= row+1; r++) {		//LINE NOT BROKEN!
			for (int c = col-1; c <= col+1; c++) {	//LINE NOT BROKEN!
				
				if (r >= 0 && c >= 0){
					int mineCount = adjacentMineCount(r, c);

					if (mineCount == 0) {
						explore(r, c);
					} else if (mineCount > 0) {
						board[r][c].setVisibility(true);
					}
				}
			}
		}
	}
	
	private void layMines(int row, int col)
	{
		//Lay mines except for position (row, col) to enhance
		//playability. Number of mines based on difficulty setting
		
		Random mineLayer = new Random();
		
		for (int i = 0; i < numMines; i++) {
			int r, c;
			do { 
				r = mineLayer.nextInt(board.length);
				c = mineLayer.nextInt(board[0].length);
			} while (r == row && c == col);
			board[r][c].setMined(true);
			remainingMines.add(board[r][c]);
		}
		
		for (int r = 0; r < board.length-1; r++) {
			for (int c = 0; c < board[0].length-1; c++) {
				
				if (!board[r][c].isMined()) {
					int count = calculateAdjacentMineCount(r, c);
					board[r][c].setAdjacentMineCount(count);
				}
			}
		}
	}


	private int calculateAdjacentMineCount(int row, int col)
	{
		int mineCount = 0;
		for (int r = row-1; r <= row+1; r++) {    //LINE NOT BROKEN!
			for (int c = col-1; c <= col+1; c++) {	//LINE NOT BROKEN!
				
				if (r >= 0 && r < board.length &&
						c >= 0 && c < board[0].length &&
						isMined(r, c)) {
					mineCount++;
				}
			}
		}
		return mineCount;
	}
	
	public int adjacentMineCount(int row, int col)
	{
		return board[row][col].adjacentMineCount();
	}

	private boolean isValidMove(int row, int col)
	{
		return row >= 0 && row < board.length && col < board[0].length;
	}

	public int rows()
	{
		return board.length;
	}
	
	public int columns()
	{
		return board[0].length;
	}

	public boolean isUnknown(int row, int col)
	{
		return !board[row][col].isVisible();
	}

	public boolean isCleared(int row, int col)
	{
		return board[row][col].isVisible();
	}
	
	public boolean isFlagged(int row, int col)
	{
		return board[row][col].isFlagged();
	}
	
	public boolean isMined(int row, int col)
	{
		return board[row][col].isMined();
	}
	
	public boolean won()
	{
		return won;
	}
	
	public boolean lost()
	{
		return lost;
	}

}
