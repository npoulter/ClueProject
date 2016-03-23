package clueTests;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;

public class GameActionTests {
private static Board board;
	
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt", "Players.txt", "Weapons.txt");
		board.initialize();
	}
	
	// Checks accusation is correct if person, weapon, and room are correct
	@Test
	public void testCorrectAccusation() {
		
	}
	
	// Checks accusation is wrong if person, weapon, and/or room are wrong
	@Test
	public void testWrongAccusation() {
			
	}
}
