package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private int row_pixels;
	private int col_pixels;
	private String name = ""; 
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
		row_pixels = i * (946/32);
		col_pixels = j * (989/33);
	}
	
	public void draw(BoardCell cell, Graphics g, ArrayList<Player> playerList) {
		if(cell.isWalkway()) {
			g.setColor(Color.YELLOW);
			g.fillRect(col_pixels, row_pixels, 989/33, 946/32);
			g.setColor(Color.BLACK);
			g.drawRect(col_pixels, row_pixels, 989/33, 946/32);
		}
		if(Dd == DoorDirection.DOWN){
			g.setColor(Color.BLUE);
			g.fillRect(col_pixels, row_pixels + (946/32) - 5, 989/33, 5);
		}
		else if(Dd == DoorDirection.UP){
			g.setColor(Color.BLUE);
			g.fillRect(col_pixels, row_pixels, 989/33, 5);
		}
		else if(Dd == DoorDirection.LEFT){
			g.setColor(Color.BLUE);
			g.fillRect(col_pixels, row_pixels, 5, 946/32);
		}
		else if(Dd == DoorDirection.RIGHT){
			g.setColor(Color.BLUE);
			g.fillRect(col_pixels + 989/33 - 5, row_pixels, 5, 946/32);
		}
		if(cell.isRoom()) {
			g.setColor(Color.GRAY);
			g.fillRect(col_pixels, row_pixels, 989/33, 946/32);
		}
		if(!cell.name.equals("")) {
			g.setColor(Color.BLACK);
			g.drawString(name, col_pixels, row_pixels);
		}
		for(int i = 0; i < playerList.size(); i++){
			if (cell.getRow() == playerList.get(i).getRow() && cell.getCol() == playerList.get(i).getColumn()){
				g.setColor(playerList.get(i).getColor());
				g.fillOval(col_pixels, row_pixels, 989/33, 946/32);
				g.setColor(playerList.get(i).getColor());
				g.drawOval(col_pixels, row_pixels, 989/33, 946/32);
			}
		}
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}

	public int getRow() {
		return row;
	}
	
	public void setName(String name) {
		this.name = name;
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
