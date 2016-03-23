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
}
