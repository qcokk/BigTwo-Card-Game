/**
 * This class is a subclass of the CardList class and is used to model a hand of cards
 * 
 * @author qcokk
 * @param player the player who plays this hand
 *
 */
public abstract class Hand extends CardList{
	private CardGamePlayer player;
	
	/**
	 * It is a constructor for building a hand with the specified player and list of cards
	 * @param player player of the hand
	 * @param cards list of cards
	 */
	public Hand(CardGamePlayer player, CardList cards) {
		this.player = player;
		for (int i=0;i<cards.size();++i) {
			this.addCard(cards.getCard(i));
		}
	}
	
	/**
	 * This method is used to get the player of this hand
	 * @return player of this hand
	 */
	public CardGamePlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * This is a method used for retrieving the top card of this hand
	 * @return top card of this hand
	 */
	public Card getTopCard() {
		Card topCard = this.getCard(0);
		for (int i=1;i<this.size();++i) {
			if (this.getCard(i).compareTo(topCard) == 1) topCard = this.getCard(i);
		}
		return topCard;
	}
	
	/**
	 * This is a method for checking if this hand beats a specified hand
	 * @param hand the specified hand which is used to check whether the hand can beat this specified hand
	 * @return true if this hand can beat the specified hand, and false otherwise
	 */
	public boolean beats(Hand hand) {
		if (this.size() != hand.size()) return false;
		else if (this.size() == 5 && hand.size() == 5) {
			int thisCombination = 0;
			if (this.getType() == "Straight") thisCombination = 1;
			else if (this.getType() == "Flush") thisCombination = 2;
			else if (this.getType() == "FullHouse") thisCombination = 3;
			else if (this.getType() == "Quad") thisCombination = 4;
			else if (this.getType() == "StraightFlush") thisCombination = 5;
			
			int handCombination = 0;
			if (hand.getType() == "Straight") handCombination = 1;
			else if (hand.getType() == "Flush") handCombination = 2;
			else if (hand.getType() == "FullHouse") handCombination = 3;
			else if (hand.getType() == "Quad") handCombination = 4;
			else if (hand.getType() == "StraightFlush") handCombination = 5;
			
			if (thisCombination > handCombination) return true;
			else if (handCombination > thisCombination) return false;
			else {
				if(thisCombination == 2) {
					if (this.getCard(0).suit > hand.getCard(0).suit) return true;
					else if (hand.getCard(0).suit > this.getCard(0).suit) return false;
					else if (this.getTopCard().compareTo(hand.getTopCard()) == 1) return true;
					else return false;
				}
				else if (this.getTopCard().compareTo(hand.getTopCard()) == 1) return true;
				else return false;
			}
		}
		else if (this.getTopCard().compareTo(hand.getTopCard()) == 1) return true;
		else return false;
		
	}
	
	/**
	 * this is a method for checking if this is a valid hand
	 * @return true if the hand is valid, false otherwise
	 */
	public abstract boolean isValid();
	/**
	 * this is a method returning the type of hand
	 * @return a string specifying the type of this hand
	 */
	public abstract String getType();
	
	/**
	 * This method is used to create a string containing the hand which will be print in the game message area in GUI
	 * @param printFront boolean value indicating if print the front value or not
	 * @param printIndex boolean value indicating if print the index value or not
	 * @return hands to output
	 */
	public String printHand(boolean printFront, boolean printIndex) {
		String output = "";
		if (this.size() > 0) {
			for (int i = 0; i < this.size(); i++) {
				if (printIndex) {
					output = output + i + " ";
				}
				if (printFront) {
					output = output + "[" + this.getCard(i) + "]";
				} else {
					output = output + "[  ]";
				}
				if (i % 13 != 0) {
					output = " " + output;
				}
				if (i % 13 == 12 || i == this.size() - 1) {
				}
			}
		} else {
			output = "[Empty]";
		}
		return output;
	}
	
}
