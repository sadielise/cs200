
public class BoardSpace {

	private boolean visible;
	private boolean mined;
	private boolean flagged;
	private int adjacentMines;
	
	
	public BoardSpace()
	{
		visible = true;
	}
	
	public void reset()
	{
		visible = false;
		mined = false;
		flagged = false;
		adjacentMines = -1;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisibility(boolean show)
	{
		visible = show;
	}
	
	public boolean isMined()
	{
		return mined;
	}
	
	public void setMined(boolean hasMine)
	{
		mined = hasMine;
	}
	
	public int adjacentMineCount()
	{
		return adjacentMines;
	}
	
	public void setAdjacentMineCount(int count)
	{
		adjacentMines = count;
	}
	
	public boolean isFlagged()
	{
		return flagged;
	}
	
	public void setFlagged(boolean wasFlagged)
	{
		flagged = wasFlagged;
	}
}
