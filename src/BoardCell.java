
public class BoardCell {
	private int row;
	private int col;
	
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
}
