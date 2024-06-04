/**
 * This class is a subclass of Card class and is used for representing a card in big two card games
 * 
 * @author qcokk
 * 
 */
public class BigTwoCard extends Card {
	/**
	 * This constructor creates and return an instance of BigTwoCard class
	 * 
	 * @param suit an int value between 0 and 3 representing the suit of a card: 0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
	 * 
	 * @param rank an int value between 0 and 12 representing the rank of a card:0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 ='Q', 12 = 'K'
	 */
	public BigTwoCard(int suit, int rank) {
		super(suit,rank);
	}
	
	/**
	 * This method is used for comparing the order of this card with the specified card
	 * 
	 * @return a negative integer, zero, or a positive integer when this card is less than, equal to, or greater than the specified card.
	 */
	public int compareTo(Card card) {
		int newThisRank = 0;
		if (this.rank == 0) newThisRank = 13;
		else if (this.rank == 1) newThisRank = 14;
		else newThisRank = this.rank;
		
		int newCardRank = 0;
		if (card.rank == 0) newCardRank = 13;
		else if (card.rank == 1) newCardRank = 14;
		else newCardRank = card.rank;
		
		if (newThisRank > newCardRank) return 1;
		else if (newThisRank < newCardRank) return -1;
		else if (this.suit > card.suit) return 1;
		else if (this.suit < card.suit) return -1;
		else return 0;
		
	}
}
