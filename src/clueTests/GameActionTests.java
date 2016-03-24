package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;

public class GameActionTests {
private static Board board;
private ArrayList<Card> localSolution;
	
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt", "Players.txt", "Weapons.txt");
		board.initialize();
		localSolution = new ArrayList<Card>();
		localSolution = board.getSolution();
	}
	
	// Checks accusation is correct if person, weapon, and room are correct
	@Test
	public void testCorrectAccusation() {
		//Retrieve correct solution
		Card playerCard = board.getSolution().get(0);
		Card weaponCard = board.getSolution().get(1);
		Card roomCard = board.getSolution().get(2);
		// If the guess is correct check that the solution is correct
		assertTrue(localSolution.contains(playerCard));
		assertTrue(localSolution.contains(weaponCard));
		assertTrue(localSolution.contains(roomCard));
	}
	
	// Checks accusation is wrong if person, weapon, and/or room are wrong
	@Test
	public void testWrongAccusation() {
		Card playerCard = board.getCardList().get(2);
		Card weaponCard = board.getCardList().get(3);
		Card roomCard = board.getCardList().get(4);
		// If the guess is wrong check that the solution does not match
		assertFalse(localSolution.contains(playerCard));
		assertFalse(localSolution.contains(weaponCard));
		assertFalse(localSolution.contains(roomCard));
	}
	
	// Test disproving a suggestion when one player has only one possible card
	@Test
	public void testDisproveSuggestionOnePossibleCard() {
		
	}
	
	// Test disproving a suggestion when one player has multiple possible cards
	@Test
	public void testDisproveSuggestionMultiplePossibleCards() {
		
	}
	
	// Test the correct order of players when disproving a suggestion
	@Test
	public void testDisproveSuggestionOrder() {
		
	}
	
	// Test disproving a suggestion when the human player has card to show
	@Test
	public void testDisproveSuggestionHuman() {
		
	}
	
	// Test that player whose turn it is doesn't show card to disprove suggestion
	@Test
	public void testDisproveSuggestionCurrentPlayer() {
		
	}
	
	// Test choosing a target with a set that includes a room
	@Test
	public void testTargetsWithRoom() {
			
	}
	
	// Test choosing random target with a set that doesn't include a room
	@Test
	public void testTargetsWithoutRoom() {
				
	}
	
	// Test choosing a target with a set that includes a room that was just visited
	@Test
	public void testTargetsWithVistedRoom() {
				
	}
	
	// Test computer suggestion with only one possible suggestion
	@Test
	public void testComputerSuggestionOneOption() {
					
	}
	
	// Test computer suggestion with only multiple suggestions
	@Test
	public void testComputerSuggestionRandom() {
						
	}
}
