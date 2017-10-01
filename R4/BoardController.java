import java.util.Scanner;

public class BoardController {

	private BoardModel model;
	

	
	public BoardController(int rows, int cols, int difficulty)
	{
		model = new BoardModel(rows, cols, difficulty);
	}
	
	public BoardController(int rows, int cols)
	{
		this(rows, cols, BoardModel.EASY);
	}
	
	public BoardController()
	{
		this(10, 10);
	}
	
	public void displayVisible()
	{
		int numRows = model.rows();
		int numCols = model.columns();
		
		System.out.print("  ");
		for (int x = 0; x < numCols; x++) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		for (int i = 0; i < numRows; i++) {
			System.out.print(i);
			for (int j = 0; j < numCols; j++) {
				System.out.print("|");
				
				if (model.isFlagged(i, j)) {
					System.out.print("F");
				} else if (model.isUnknown(i,j)) {
					System.out.print("?");
				} else if (model.isCleared(i, j)) {
					int count = model.adjacentMineCount(i, j);
					if (count == 0) {
						System.out.print(" ");
					} else {
						System.out.print(count);
					}
				}
			}
			System.out.println("|");
		}
	}
	
	public void displayAll()
	{
		int numRows = model.rows();
		int numCols = model.columns();
		
		System.out.print("  ");
		for (int x = 0; x < numCols; x++) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		for (int i = 0; i < numRows; i++) {
			System.out.print(i);
			for (int j = 0; j < numCols; j++) {
				System.out.print("|");

				if (model.isMined(i, j) && model.isFlagged(i, j)) {
					System.out.print("X");
				} else if (model.isMined(i, j)) {
					System.out.print("M");
				} else if (!model.isMined(i, j)) {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
	}
	
	public static void main(String[] args)
	{

		BoardController game = new BoardController(10,10,BoardModel.EASY);
		
		Scanner userInput = new Scanner(System.in);
		do {
			int row, col;
			String cmd;
			
			game.displayVisible();
//			System.out.println();
//			game.displayAll();
			System.out.println("Flags Remaining: " + game.model.flagsRemaining());
			
			System.out.print("> ");
			
			cmd = userInput.next();
			if (cmd.equals("q") || cmd.equals("quit") || cmd.equals("exit")) {
				System.out.println("Thanks for playing!");
				break;
			} else if (cmd.equals("r") || cmd.equals("f")) {

				row = userInput.nextInt();
				col = userInput.nextInt();
				
				if (cmd.equals("r")) {
					game.model.reveal(row, col);	
				} else { 
					game.model.flag(row, col);
				}
				
				if (game.model.won()) {
					game.displayAll();
					System.out.println("YOU WIN!");
					break;
				} else if (game.model.lost()) {
					game.displayAll();
					System.out.println("GAME OVER");
					break;
				}
				
			} else {
				System.out.println("unrecognized command: \"" + cmd + "\"");
			}
		} while (true);
	}



}
