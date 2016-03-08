package clueGame;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private DoorDirection Dd;
	
	public DoorDirection getDd() {
		return Dd;
	}

	public void setDd(DoorDirection dd) {
		Dd = dd;
	}

	public BoardCell(int i, int j){
		this.row = i;
		this.col = j;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public boolean isWalkway(){
		if (initial == 'W')
			return true;
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		if(Dd != DoorDirection.NONE)
			return true;
		else
			return false;
	}

	public char getInitial() {
		return initial;
	}
	
	public void setInitial(char initial) {
		this.initial = initial;
	}

	public Object getDoorDirection() {
		return Dd;
	}
}
