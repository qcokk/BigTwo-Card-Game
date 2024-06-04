/**
 * This is a subclass of Hand class and are used to model a hand of triple in a Big Two Card game
 * @author qcokk
 *
 */
public class Triple extends Hand {
	
	/**
	 * It is a constructor for building a triple hand with the specified player and list of cards
	 * @param player player of the triple hand
	 * @param card list of cards
	 */
	public Triple (CardGamePlayer player, CardList card) {
		super(player, card);
	}
	
	/**
	 * this is a method for checking if this is a valid triple hand
	 * @return true if the triple hand is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.size() != 3) return false;
		else if (this.getCard(0).rank == this.getCard(1).rank && this.getCard(1).rank == this.getCard(2).rank) return true;
		else return false;
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "Triple";
	}

}
