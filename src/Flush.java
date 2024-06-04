/**
 * This is a subclass of Hand class and are used to model a hand of flush in a Big Two Card game
 * @author qcokk
 *
 */
public class Flush extends Hand {
	
	/**
	 * It is a constructor for building a flush hand with the specified player and list of cards
	 * @param player player of the flush hand
	 * @param card list of cards
	 */
	public Flush (CardGamePlayer player, CardList card) {
		super(player,card);
	}
	
	/**
	 * this is a method for checking if this is a valid flush hand
	 * @return true if the flush hand is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.size() != 5) return false;
		else {
			for (int i=0;i<(this.size()-1);++i) {
				if (this.getCard(i).suit != this.getCard(i+1).suit) return false;
			}
			return true;
		}
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "Flush";
	}
}
