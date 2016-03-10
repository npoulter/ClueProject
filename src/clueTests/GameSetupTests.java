package clueTests;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;

public class GameSetupTests {
	private static Board board;
	
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt");
		board.initialize();
	}
	
	@Test
	// Checks for the correct number of people in player's list
	public void testNumPeople() {
		
	}
	
	@Test
	// Checks players have correct name, color, and starting location
	public void testCorrectData() {
		
	}
	
	@Test
	// Checks deck has correct total number of cards
	public void testCorrectNumCards() {
		
	}
	
	@Test
	// Checks deck has correct number of cards of each type
	public void testCorrectTypes() {
		
	}
	
	@Test
	// Checks names were loaded correctly by ensuring deck has specific room, weapon, and person
	public void testCardNames() {
		
	}
	
	@Test
	// Checks all cards were dealt
	public void testAllCardsDealt() {
		
	}
	
	@Test
	// Checks each player has about the same number of cards
	public void testEqualNumCards() {
		
	}
	
	@Test
	// Checks multiple players don't have the same card
	public void testNoDuplicates() {
		
	}
}
