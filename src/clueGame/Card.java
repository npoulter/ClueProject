package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card (String cardName, String cardType){
		this.cardName = cardName;
		this.cardType = CardType.valueOf(cardType);
	}

	// TODO: Actually write function
	public boolean equals() {
		return true;
	}
}
