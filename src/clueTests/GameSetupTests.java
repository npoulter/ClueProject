package clueTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;

public class GameSetupTests {
	private static Board board;
	
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt", "Players.txt", "Weapons.txt");
		board.initialize();
	}
	
	@Test
	// Checks for the correct number of people in player's list
	public void testNumPeople() {
		int arraySize = board.getArraySize();
		assertEquals(arraySize, 6);
	}
	
	@Test
	// Checks players have correct name, color, and starting location
	public void testCorrectData() {
		// Tests the attributes of the human player
		Player humanPlayer = board.getPlayer(0);
		assertEquals("Mr. Robot",humanPlayer.getName());
		assertEquals(Color.gray, humanPlayer.getColor());
		assertEquals(humanPlayer.getRow(), 15);
		assertEquals(humanPlayer.getColumn(), 11);
		// Tests the attributes of the middle computer player
		Player middleComputer = board.getPlayer(3);
		assertEquals("John Cena",middleComputer.getName());
		assertEquals(Color.yellow, middleComputer.getColor());
		assertEquals(middleComputer.getRow(), 15);
		assertEquals(middleComputer.getColumn(), 11);
		// Tests the attributes of the final computer player
		Player finalComputer = board.getPlayer(5);
		assertEquals("Gilbert Grape", finalComputer.getName());
		assertEquals(Color.blue, finalComputer.getColor());
		assertEquals(finalComputer.getRow(), 15);
		assertEquals(finalComputer.getColumn(), 11);
	}
	
	@Test
	// Checks deck has correct total number of cards
	public void testCorrectNumCards() {
		int deckSize = board.getCardDeckSize();
		assertEquals(deckSize, 21);
	}
	
	@Test
	// Checks deck has correct number of cards of each type
	public void testCorrectTypes() {
		Card[] cardArray = board.getCardArray();
		int personCount = 0;
		int weaponCount = 0;
		int roomCount = 0;
		for(int i = 0; i < cardArray.length; i++){
		Card nextCard = cardArray[i];
			switch(nextCard.getCardType()){
			case PERSON:
				personCount++;
				break;
			case WEAPON:
				weaponCount++;
				break;
			case ROOM:
				roomCount++;
			}
		}
		assertEquals(personCount, 6);
		assertEquals(weaponCount, 6);
		assertEquals(roomCount, 9);
	}
	
	@Test
	// Checks names were loaded correctly by ensuring deck has specific room, weapon, and person
	public void testCardNames() {
		ArrayList<Card> cardList = board.getCardList();
		Card[] cardArray = board.getCardArray();
		//Test that the player is in the deck of cards
		Card playerCard = cardArray[0];
		assertTrue(cardList.contains(playerCard));
		//Test that the first weapon is in the deck of cards		
		Card firstWeapon = cardArray[6];
		assertTrue(cardList.contains(firstWeapon));
		//Test that the first room is in the deck of cards
		Card firstRoom = cardArray[12];
		assertTrue(cardList.contains(firstRoom));
	}
	
	@Test
	// Checks all cards were dealt
	public void testAllCardsDealt() {
		int cardCount = 0;
		Card oneCard;
		Card[] cardArray = board.getCardArray();
		for (int i = 0; i < cardArray.length; i++){
			oneCard = cardArray[i];
			//Ensure that the card is a valid card
			if(oneCard == null){
				//Do Nothing
			}
			else {
				cardCount++;
			}
		}
		assertEquals(cardCount, 21);
	}
	
	@Test
	// Checks each player has about the same number of cards
	public void testEqualNumCards() {
		// Use getter functions to deal the cards
		ArrayList<Player> localPlayerList = new ArrayList<Player>();
		localPlayerList = board.getPlayerList();
		ArrayList<Card> localCardsList = new ArrayList<Card>();
		localCardsList = board.getCardList();
		for(int i = 0; i < localCardsList.size(); i++){
			localPlayerList.get(i%localPlayerList.size()).givePlayerCard(localCardsList.get(i)); 
		}
		// Test that the players have equal amounts of cards
		assertEquals(localPlayerList.get(0).getSize(), localPlayerList.get(1).getSize());
		assertEquals(localPlayerList.get(4).getSize(), localPlayerList.get(5).getSize());
	}
	
	@Test
	// Checks multiple players don't have the same card
	public void testNoDuplicates() {
		ArrayList<Player> localPlayerList = new ArrayList<Player>();
		localPlayerList = board.getPlayerList();
		ArrayList<Card> localCardsList = new ArrayList<Card>();
		localCardsList = board.getCardList();
		for(int i = 0; i < localCardsList.size(); i++){
			localPlayerList.get(i%localPlayerList.size()).givePlayerCard(localCardsList.get(i)); 
		}
	}
}
