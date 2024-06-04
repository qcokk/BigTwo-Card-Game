/**
 * This is a subclass of Hand class and are used to model a hand of full house in a Big Two Card game
 * @author qcokk
 *
 */
public class FullHouse extends Hand {
	/**
	 * It is a constructor for building a full house hand with the specified player and list of cards
	 * @param player player of the full house hand
	 * @param card list of cards
	 */
	public FullHouse(CardGamePlayer player, CardList card) {
		super(player,card);
	}
	
	/**
	 * This is a method used for retrieving the top card of this hand
	 * @return top card of this hand
	 */
	public Card getTopCard() {
		int tripletRank;
		this.sort();
		tripletRank = this.getCard(2).rank;
		
		if (this.getCard(0).rank == tripletRank) return this.getCard(2);
		else return this.getCard(4);
	}
	
	/**
	 * this is a method for checking if this is a valid full house hand
	 * @return true if the full house hand is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.size() != 5) return false;
		else {
			this.sort();
			if (this.getCard(0).rank == this.getCard(1).rank && this.getCard(1).rank == this.getCard(2).rank) {
				if (this.getCard(3).rank == this.getCard(4).rank) return true;
				else return false;
			}
			else if (this.getCard(0).rank == this.getCard(1).rank) {
				if (this.getCard(2).rank == this.getCard(3).rank && this.getCard(3).rank == this.getCard(4).rank) return true;
				else return false;
			}
			else return false;
		}
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "FullHouse";
	}
}
