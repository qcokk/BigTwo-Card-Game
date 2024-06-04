/**
 * This is a subclass of Hand class and are used to model a hand of single in a Big Two Card game
 * @author qcokk
 *
 */
public class Single extends Hand {
	
	/**
	 * It is a constructor for building a single hand with the specified player and list of cards
	 * @param player player of the single hand
	 * @param card list of cards
	 */
	public Single(CardGamePlayer player, CardList card) {
		super(player, card);
	}
	
	/**
	 * this is a method for checking if this is a valid single hand
	 * @return true if the single hand is valid, false otherwise
	 */
	public boolean isValid() {
		if(this.size() == 1) return true;
		else return false;
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "Single";
	}
}
