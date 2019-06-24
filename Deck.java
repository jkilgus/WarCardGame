package edu.vtc.cis2260;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import edu.vtc.cis2260.Card.Suit;
	
/**
 * A deck of cards represented as a List of Cards
 * @author think
 *
 */
public class Deck{
	private List<Card> deck;
	
	/**
	 * A deck of cards represented as a LinkedList. Every deck has a name.
	 * @param name - The name of the deck
	 */
	public Deck(String name) {
		this.name = name;
		deck = new LinkedList<>();
		repOK();
	}
	
	/**
	 * Fills the deck with 52 cards of random Suite and Rank
	 */
	public void fillRandom() {
		for (int i = 0; i < 52; i++) {
			deck.add(generateRandomCard());
		}
		repOK();
	}
	
	/**
	 * Adds a card s to the bottom of the deck
	 * @param s
	 */
	public void addToBottom(Card s) {
		deck.add(s);
	}
	
	/**
	 * Fills the deck with the standard 52 playing cards
	 */
	public void fillStandard() {
		for (int i = 1; i < 14; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					Card.Suit s = Card.Suit.Club;
					deck.add(new Card(s, i));
				} else if (j == 1) {
					Card.Suit s = Card.Suit.Diamond;
					deck.add(new Card(s, i));
				} else if (j == 2) {
					Card.Suit s = Card.Suit.Heart;
					deck.add(new Card(s, i));
				} else {
					Card.Suit s = Card.Suit.Spade;
					deck.add(new Card(s, i));
				}
			}
		}
		repOK();
	}
	
	/**
	 * Adds a Card s to the deck.
	 * @param s - the card being added. Must have a Suite and Rank already assigned.
	 */
	public void addCard(Card s) {
		deck.add(s);
		repOK();
	}
	
	/**
	 * Adds a specific card to the deck
	 * @param s - The Suite of the card to be added. Format is: Card.Suite.Diamond, .Club, .Spade, .Heart
	 * @param r - Rank of the card from 1 to 14. 1 is Ace, 2 is 2, etc.
	 */
	public void addSpecificCard(Suit s, int r) {
		Card c = new Card(s, r);
		deck.add(c);
		repOK();
	}
	
	/**
	 * Draws a card from the top of the deck given that there are cards in the deck to draw from.
	 * Card is removed from the deck.
	 */
	public void drawCard() {
		if (deck.size() > 0) {
		deck.remove(0);
		} else {
			System.out.println("No cards left.");
		}
		repOK();
	}
	
	/**
	 * Returns the top card if there is one
	 */
	public Card returnTopCard() {
		if (deck.size() > 0 ) {
			// System.out.println(deck.get(0));
			Card x = deck.get(0);
			deck.remove(0);
			return x;
		} else {
			System.out.println("Deck is empty.");
			return null;
		}
	}
	
	/**
	 * Checks to see if the deck is empty
	 * @return
	 */
	public boolean hasNextCard() {
		if (deck.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Reveals the top card of the deck without removing it.
	 */
	public Card revealTopCard() {
		if (deck.size() > 0 ) {
			return deck.get(0);
		} else {
			System.out.println("Deck is empty.");
			return null;
		}
	}
	
	/**
	 * Adds a new random card to the top of the deck.
	 */
	public void addCardToTop(Card s) {
		deck.add(0, s);
		repOK();
	}
	
	/**
	 * Removes a card s from the deck
	 * @param s - the card to remove
	 */
	public void removeCard(Card s) {
		if (getSize() > 0) {
			deck.remove(s);
		}
		repOK();
	}
	
	/**
	 * Prints the current size of the deck
	 */
	public int getSize() {
		//System.out.println(deck.size() + " Cards in deck " + name);
		return deck.size();
	}
	
	/**
	 * Shuffles the deck
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
		repOK();
	}
	
	/**
	 * Creates and adds a card of random rank and class to the deck
	 */
	public void addRandomCard() {
		deck.add(generateRandomCard());
		repOK();
	}
	
	/**
	 * Creates a card of random rank and class
	 */
	public Card generateRandomCard() {
		// Gives a random Suite
		double t = Math.random();
		Card.Suit s;
		if (t < 0.25) {
			s = Card.Suit.Club;
		} else if (t >= 0.25 && t < 0.5) {
			s = Card.Suit.Diamond;
		} else if (t >= 0.5 && t < 0.75) {
			s = Card.Suit.Spade;
		} else {
			s = Card.Suit.Heart;
		}
		
		// Gives a random int between 1 and 14 to choose a rank
		int randomNum = ThreadLocalRandom.current().nextInt(1, 14);
		
		// Creates the new card and assigns the random rank and suite
		Card randomCard = new Card(s, randomNum);
		return randomCard;
	}
	
	
	// Verifies the representation invariants of the deck
	private void repOK() {
		assert this.deck != null;
		assert this.name != null;
		assert this.deck.size() >= 0;
	}
	
	/**
	 * Displays the full contents of the deck
	 */
	public void dumpCards() {
		System.out.println(deck);
	}
	
	/**
	 * Checks do see if the deck is empty and displays a message accordingly
	 */
	public void isEmpty() {
		if (deck.size() == 0) {
			System.out.println("The deck is empty.");
		} else {
			System.out.println("Deck is not empty.");
		}
	}
	
	// The name of the deck. This way you can keep track of multiple decks.
	private String name;
}