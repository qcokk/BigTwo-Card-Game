import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
/**
 * This class implements the CardGame interface and is used to model a Big Two card game
 * @author qcokk
 * @param numOfPlayers integer specifying the number of players
 * @param deck deck of cards
 * @param playerList list of players
 * @param handsOnTable list of hands played on the table
 * @param currentPlayerIdx integer specifying index of the current player
 * @param gui BigTwoGUI object for providing the graphical user interface
 * @param testingHand a valid hand made by a player which is used to check if the hand beats the last hand on table
 * @param valid boolean value indicating whether the move is valid or not
 *
 */
public class BigTwo implements CardGame{
	
	private int numOfPlayers;
	private Deck deck;
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	private int currentPlayerIdx;
	private BigTwoGUI gui;
	private Hand testingHand;
	private boolean valid = true;
	
	/**
	 * It is a constructor for creating a Big Two card game
	 */
	public BigTwo() {
		this.numOfPlayers = 4;
		this.playerList = new ArrayList<CardGamePlayer>();
		CardGamePlayer p1 = new CardGamePlayer("Player 0");
		CardGamePlayer p2 = new CardGamePlayer("Player 1");
		CardGamePlayer p3 = new CardGamePlayer("Player 2");
		CardGamePlayer p4 = new CardGamePlayer("Player 3");
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		handsOnTable = new ArrayList<Hand>();
		this.gui = new BigTwoGUI(this);
	}
	
	/**
	 * this is a method for getting the number of players
	 */
	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}
	
	/**
	 * this is a methods for retrieving the deck of cards being used
	 */
	public Deck getDeck() {
		return this.deck;
	}
	
	/**
	 * this is a methods for retrieving the list of players
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return this.playerList;
	}
	
	/**
	 * this is a method for retrieving the list of hands played on the table
	 */
	public ArrayList<Hand> getHandsOnTable(){
		return this.handsOnTable;
	}
	
	/**
	 * this is a method for retrieving the index of current player
	 */
	public int getCurrentPlayerIdx() {
		return this.currentPlayerIdx;
	}
	
	/**
	 * this is a method for starting/restarting the game with a given shuffled deck of cards
	 */
	public void start(Deck deck) {
		for (CardGamePlayer p:playerList) {
			p.removeAllCards();
		}
		if (!handsOnTable.isEmpty()) handsOnTable.clear();
		int pidx = 0;
		for (CardGamePlayer p: playerList) {
			for (int i=0;i<13;++i) {
				p.addCard(deck.getCard(pidx*13+i));
				
				if (deck.getCard(pidx*13+i).suit == 0 && deck.getCard(pidx*13+i).rank == 2) {
					currentPlayerIdx = playerList.indexOf(p);
					gui.setActivePlayer(currentPlayerIdx);
				}
			}
			p.getCardsInHand().sort();
			pidx++;
		}
		gui.repaint();
		gui.promptActivePlayer();
		return;
		
	}
	
	/**
	 * this is a method for making a move by a player with the specified index using cards specified by the list of indices
	 * @param playerIdx index of player to make a move
	 * @param cardIdx list of cards specified by the int list of indices
	 */
	public void makeMove(int playerIdx, int[] cardIdx) {
		this.valid = true;
		if (cardIdx == null) {
			this.checkMove(playerIdx, cardIdx);
			if (this.valid == false) return;
			this.testingHand = null;
			gui.printMsg("{Pass}\n");
			if (playerIdx != 3) gui.setActivePlayer(playerIdx+1);
			else gui.setActivePlayer(0);
			gui.promptActivePlayer();
			return;
		}
		CardList c = new CardList();
		for (int i=0;i<cardIdx.length;++i) {
			/*if (playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]) == null) {
				gui.printMsg("Not a legal move!!!\n");
				ui.promptActivePlayer();
				return;
			}*/
			c.addCard(playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]));
		}
		this.testingHand = composeHand(playerList.get(playerIdx),c);
		if (this.testingHand == null) {
			gui.printMsg("Not a legal move!!!\n");
			gui.promptActivePlayer();
			return;
		}
		else {
			this.checkMove(playerIdx,cardIdx);
			if (this.valid == false) return;
			gui.printMsg("{"+this.testingHand.getType()+"} ");
			String output = this.testingHand.printHand(true,false);
			gui.printMsg(output+"\n");
			Arrays.sort(cardIdx);
			for (int i=(cardIdx.length-1);i>=0;--i) {
				this.playerList.get(playerIdx).getCardsInHand().removeCard(cardIdx[i]);
			}
			this.handsOnTable.add(this.testingHand);
			if (endOfGame()) {
				gui.printMsg("Game ends\n");
				for (CardGamePlayer p: playerList) {
					if (this.playerList.indexOf(p) == playerIdx) gui.printMsg("Player "+(playerIdx)+" wins the game.\n");
					else gui.printMsg("Player "+(this.playerList.indexOf(p))+ " has "+ p.getNumOfCards() + " cards in hand.\n");
				}
				gui.disable();
				return;
			}
			else {
				if (playerIdx != 3) gui.setActivePlayer(playerIdx+1);
				else gui.setActivePlayer(0);
				gui.promptActivePlayer();
			}
			return;
		}
	}
	
	/**
	 * this is a method for checking a move made by a player
	 * @param playerIdx index of player to make a move
	 * @param cardIdx list of cards specified by the int list of indices
	 */
	public void checkMove(int playerIdx, int[] cardIdx) {
		Card c = new Card(0,2);
		if(playerList.get(playerIdx).getCardsInHand().contains(c)) {
			if (this.testingHand ==null) {
				gui.printMsg("Not a legal move!!!\n");
				gui.promptActivePlayer();
				this.valid = false;
				return;
			}
			if (!testingHand.contains(c)) {
				gui.printMsg("Not a legal move!!!\n");
				gui.promptActivePlayer();
				this.valid = false;
				return;
			}
		}
		
		if (this.handsOnTable.size() != 0) {
		if (this.handsOnTable.get(this.handsOnTable.size()-1).getPlayer() == playerList.get(playerIdx)) {
				if (cardIdx == null) {
					gui.printMsg("Not a legal move!!!\n");
					gui.promptActivePlayer();
					this.valid = false;
					return;
				}
				else {
					return;
				}
			}
		}
		
		if (cardIdx == null) {
			return;
		}
		if (this.handsOnTable.size() != 0) {
			Hand previousHand = null;
			for (int i=(this.handsOnTable.size()-1);i>=0;--i) {
				if (this.handsOnTable.get(i) != null) {
					previousHand = this.handsOnTable.get(i);
					break;
				}
				if (i == 0) return;
			}
			if (this.testingHand.beats(previousHand)) return;
			else {
				gui.printMsg("Not a legal move!!!\n");
				gui.repaint();
				gui.promptActivePlayer();
				this.valid = false;
				return;
			}
		}
		return;
		
	}
	
	/**
	 * this is a method to check if the game ends
	 */
	public boolean endOfGame() {
		for (CardGamePlayer p: playerList) {
			if (p.getCardsInHand().isEmpty()) return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		BigTwo game = new BigTwo();
		BigTwoDeck deck = new BigTwoDeck();
		deck.shuffle();
		game.start(deck);
		
	}
	
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		Hand hand;
		
		if (cards.size() == 1) {
			hand = new Single(player,cards);
			if (hand.isValid()) return hand;
		}
		else if (cards.size() == 2) {
			hand = new Pair(player,cards);
			if (hand.isValid()) return hand;
		}
		else if (cards.size() == 3) {
			hand = new Triple(player,cards);
			if (hand.isValid()) return hand;
		}
		else if (cards.size() == 5) {
			hand = new StraightFlush(player,cards);
			if (hand.isValid()) return hand;
			hand = new Quad(player,cards);
			if (hand.isValid()) return hand;
			hand = new FullHouse(player, cards);
			if (hand.isValid()) return hand;
			hand = new Flush(player,cards);
			if (hand.isValid()) return hand;
			hand = new Straight(player, cards);
			if (hand.isValid()) return hand;
			
			
		}
		
		
		return null;
	}
	
	
	
	
	

	
	
	
}
