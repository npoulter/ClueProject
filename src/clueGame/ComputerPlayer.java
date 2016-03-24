package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player {
	public ComputerPlayer(String playerName, int row, int column, Color color) {
		super(playerName, row, column, color);
		// TODO Auto-generated constructor stub
	}

	// TODO: Actually write function
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return new BoardCell(0,0);
	}
	
	// TODO: Actually write function
	public void makeAccusation() {
		
	}
	
	// TODO: Actually write function
	public void makeSuggestion(Board board, BoardCell location) {
		
	}
}
