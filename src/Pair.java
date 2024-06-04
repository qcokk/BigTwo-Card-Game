/**
 * This is a subclass of Hand class and are used to model a hand of pair in a Big Two Card game
 * @author qcokk
 *
 */
public class Pair extends Hand {
	
	/**
	 * It is a constructor for building a pair hand with the specified player and list of cards
	 * @param player player of the pair hand
	 * @param card list of cards
	 */
	public Pair (CardGamePlayer player, CardList card) {
		super(player, card);
	}
	
	/**
	 * this is a method for checking if this is a valid pair hand
	 * @return true if the pair hand is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.size() != 2) return false;
		else if (this.getCard(0).rank == this.getCard(1).rank) return true;
		else return false;
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "Pair";
	}
}
