package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Solution;

public class GameActionTests {
private static Board board;
private Solution localSolution;
	
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt", "Players.txt", "Weapons.txt");
		board.initialize();
		board.dealCards();
		localSolution = board.getSolution();
	}
	
	// Checks accusation is correct if person, weapon, and room are correct
	@Test
	public void testCorrectAccusation() {
		//Retrieve correct solution
		String suspect = board.getSolution().getPerson();
		String weapon = board.getSolution().getWeapon();
		String room = board.getSolution().getRoom();
		// If the guess is correct check that the solution is correct
		assertEquals(localSolution.getPerson(), suspect);
		assertEquals(localSolution.getWeapon(), weapon);
		assertEquals(localSolution.getRoom(), room);
	}
	
	// Checks accusation is wrong if person, weapon, and/or room are wrong
	@Test
	public void testWrongAccusationS() {
		
		String suspect = board.getCardsList().get(0).getCardName();
		String weapon = board.getCardsList().get(1).getCardName();
		String room = board.getCardsList().get(2).getCardName();
		
		// If the guess is wrong check that the solution does not match
		if (localSolution.getPerson().equals(suspect)) {
			fail();
		}
		
		if (localSolution.getRoom().equals(room)) {
			fail();
		}
		
		if (localSolution.getWeapon().equals(weapon)) {
			fail();
		}
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
