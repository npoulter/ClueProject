package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private int row_pixels;
	private int col_pixels;
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
		row_pixels = i * (900/22);
		col_pixels = j * (900/23);
	}
	
	public void draw(BoardCell cell, Graphics g) {
		if(cell.isWalkway()) {
			g.setColor(Color.YELLOW);
			g.fillRect(row_pixels, col_pixels, 1000/23, 1000/22);
		}
		if(Dd == DoorDirection.DOWN){
			g.setColor(Color.BLUE);
			g.fillRect(row_pixels - 5, col_pixels, 1000/23, 5);
		}
		else if(Dd == DoorDirection.UP){
			g.setColor(Color.BLUE);
			g.fillRect(row_pixels + (1000/22), col_pixels, 1000/23, 5);
		}
		else if(Dd == DoorDirection.LEFT){
			g.setColor(Color.BLUE);
			g.fillRect(row_pixels, col_pixels + (1000/23), 5, 1000/22);
		}
		else if(Dd == DoorDirection.RIGHT){
			g.setColor(Color.BLUE);
			g.fillRect(row_pixels - 1000/22, col_pixels - 5, 5, 1000/22);
		}
		if(isRoom()) {
			g.setColor(Color.GRAY);
			g.fillRect(row_pixels, col_pixels, 1000/23, 1000/22);
		}
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
