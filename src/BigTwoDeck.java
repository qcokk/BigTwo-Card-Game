
/**
 * This class is a subclass of the Deck class and is used to model a deck of cards used in a Big Two card game
 * 
 * @author qcokk
 *
 */
public class BigTwoDeck extends Deck{
	
	/**
	 * Initializes the deck of big two cards (called implicitly inside the constructor).
	 */
	public void initialize() {
		removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard bigtwocard = new BigTwoCard(i, j);
				addCard(bigtwocard);
			}
		}
	}
}
