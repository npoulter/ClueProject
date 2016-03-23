package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private String playerName;
	private int row;
	private int size;
	private int column;
	private Color color;
	private ArrayList<Card> cardList;
	
	public Player(String playerName, int row, int column, Color color) {
		super();
		cardList = new ArrayList<Card>();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}
	
	public void givePlayerCard(Card thisCard){
		cardList.add(thisCard);
		size = cardList.size();
	}

	public Card getOneCard(){
		return cardList.get(0);
	}
	
	public ArrayList<Card> getCardList(){
		return cardList;
	}
	
	public int getSize(){
		return size;
	}
	
	public String getName(){
		return playerName;
	}
	
	public Color getColor(){
		return color;
	}

	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}

	// TODO: Actually write function
	/*public Card disproveSuggestion(Solution suggestion) {
		return new Card();
	}*/
}
