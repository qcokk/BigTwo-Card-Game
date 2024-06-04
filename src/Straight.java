/**
 * This is a subclass of Hand class and are used to model a hand of straight in a Big Two Card game
 * @author qcokk
 *
 */
public class Straight extends Hand {
	
	/**
	 * It is a constructor for building a straight hand with the specified player and list of cards
	 * @param player player of the straight hand
	 * @param card list of cards
	 */
	public Straight(CardGamePlayer player, CardList card) {
		super(player,card);
	}
	
	/**
	 * this is a method for checking if this is a valid straight hand
	 * @return true if the straight hand is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.size() != 5) return false;
		else {
			this.sort();
			int firstRank, secRank;
			for(int i=0;i<(this.size()-1);++i) {
				if (this.getCard(i+1).rank == 0) firstRank = 13;
				else if (this.getCard(i+1).rank == 1) firstRank = 14;
				else firstRank = this.getCard(i+1).rank;
				
				if(this.getCard(i).rank == 0) secRank = 13;
				else if (this.getCard(i).rank == 1) secRank = 14;
				else secRank = this.getCard(i).rank;
				
				if (firstRank != (secRank+1)) return false;
			}
			return true;
		}
	}
	
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public String getType() {
		return "Straight";
	}

}
