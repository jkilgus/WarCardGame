package edu.vtc.cis2260;

import java.util.Scanner;


public class warTester3 {
	// Scanner so that users can press c to continue
	Scanner scan = new Scanner(System.in);
	// In war players have 2 decks. Each player flips cards from the deck to the playing field.
	// These playing field cards are represented by the aw and bw decks below.
	// Instead of a discard pile cards are sent back to the bottom of the victor's hand
	// since that is where they would end up anyway and it makes for easier coding
	// and doesn't affect the outcome of the game in any way.
	Deck a; Deck aw; Deck b; Deck bw;
	
	public warTester3() {
		a = new Deck("Deck 1");
		b = new Deck("Deck 2");
		aw = new Deck("Deck 1 cards in play");
		bw = new Deck("Deck 2 cards in play");
	}
	
	public static void main(String[] args) {
		warTester3 w = new warTester3();
		w.WarTester3();
	}
	
	// Ensures that both players have cards
	// so that the game can continue
	private boolean playersHaveCards() {
		if (a.getSize() > 0 && b.getSize() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// This method draws a card from the top of each players' deck
	// and puts it in the play area
	private void playersDrawCards() {
		if (playersHaveCards()) {
			aw.addCardToTop(a.returnTopCard());
			bw.addCardToTop(b.returnTopCard());
		} else {
			System.out.println("Somebody ran out of cards, game over");
			System.out.println("Player 1 cards: " + a.getSize());
			System.out.println("PLayer 2 cards: " + b.getSize());
		}
	}
	
	// Condenses the very long aceHighRankCompare method into playersCompare()
	// it compares the top card of each of the in play piles aw and bw
	private int playersCompare() {
		if (Card.aceHighRankCompare.compare(aw.revealTopCard(), bw.revealTopCard()) == 1) {
			return 1;
		} else if (Card.aceHighRankCompare.compare(aw.revealTopCard(), bw.revealTopCard()) == -1) {
			return -1;
		} else if (Card.aceHighRankCompare.compare(aw.revealTopCard(), bw.revealTopCard()) == 0) {
			return 0;
		} else {
			return 7;
		}
	}
	
	// If a tie occurs, each player draws two more cards if they are available
	// and add them to the play piles, flipping over the second card to compare them.
	// The winner of that comparison gets all cards on the field. If they don't have
	// enough cards to draw two, then they draw one and then do the same comparison.
	// If another tie happens after the double draw and comparison, the tie() function
	// is recursively called upon again until one player eventually wins or there
	// is a draw by tie.
	private void tie() {
		if (playersHaveCards()) {
			playersDrawCards();
			if (playersHaveCards()) {
				playersDrawCards();
				if (playersCompare() > 0) {
					while (aw.getSize() > 0) {
						a.addCard(aw.returnTopCard());
					} while (bw.getSize() > 0) {
						a.addCard(bw.returnTopCard());
					}
				} else if (playersCompare() < 0) {
					while (aw.getSize() > 0) {
						b.addCard(aw.returnTopCard());
					} while (bw.getSize() > 0) {
						b.addCard(bw.returnTopCard());
					}
				} else if (playersCompare() == 0) {
					System.out.println("Tie recursion");
					String i = scan.next();
					if (i.equalsIgnoreCase("c"))
					System.out.println(playersCompare());
					tie();
				} else {
					System.out.println("Error 2");
					System.out.println(playersCompare());
				}
			} else {
				System.out.println("Game over 2");
			}
		} else {
			System.out.println("Game over");
		}
	}
	
	public void WarTester3() {
		System.out.println("---------------------------------------");
		System.out.println("---=============---WAR---===========---");
		System.out.println("---------------------------------------");
		System.out.println("The game is setup to cycle through each");
		System.out.println("hand of the game individually. If a player");
		System.out.println("runs out of cards you will get a game over");
		System.out.println("text declaring the winner. It is possible");
		System.out.println("for War to get stuck in a loop with no end following");
		System.out.println("all rules of the game. Because of this, this is");
		System.out.println("setup to play 5 vs 5 cards so you can easily");
		System.out.println("tell when it's stuck in a loop, and also so");
		System.out.println("so you can play through an entire match in a");
		System.out.println("reasonable amount of time.");
		System.out.println();
		System.out.println("Enter c to continue, x to exit");
		String s = scan.next();
		
		// I was having a lot of difficulty implementing a method
		// to fill each deck with 5 random cards just so that there
		// were few enough cards to track every move and make
		// sure the program runs with no errors. I do have
		// fillStandard() that works totally fine and fills
		// each deck with the standard 52 cards.
		if (s.equalsIgnoreCase("c")) {
			a.addRandomCard();
			a.addRandomCard();			
			a.addRandomCard();
			a.addRandomCard();
			a.addRandomCard();
			b.addRandomCard();
			b.addRandomCard();
			b.addRandomCard();
			b.addRandomCard();
			b.addRandomCard();
		} else {
			System.out.println("Quitting");
		}
		
		// Displays the contents of the decks before the game begins
		a.dumpCards();
		b.dumpCards();
		aw.dumpCards();
		bw.dumpCards();
		
		// There were too many c inputs required to continue so I made this one "always on"
		// by hard coding "c" into what the scanner would normally request.
		String t = "c";
		if (t.equalsIgnoreCase("c")) {
			while (playersHaveCards()) {
				play();
			}
			
			// If a player runs out of cards he loses and the other player wins
			// It then displays deck sizes to verify
			if (a.getSize() > 0 && b.getSize() == 0) {
				System.out.println("Player 1 Wins!");
			} else if (a.getSize() == 0 && b.getSize() > 0) {
				System.out.println("Player 2 Wins!");
			} else {
				System.out.println("Something went wrong.");
			}
			System.out.println("Game Over");
			System.out.println("P1 has " + a.getSize() + " cards.");
			System.out.println("P2 has " + b.getSize() + " cards.");
			System.out.println("P1 cards left in play: " + aw.getSize());
			System.out.println("P2 cards left in play: " + bw.getSize());
		}
	}
	
	// Ensures that no cards are created or destroyed by mistake
	private void repOK() {
		assert (a.getSize() + b.getSize() + aw.getSize() + bw.getSize() == 10);
	}

	// Players draw a card from their deck and put it onto
	// the play area. If one player wins they get both cards.
	// If a tie occurs the tie() function is called.
	private void play() {
			String l = scan.next();
			if (l.equalsIgnoreCase("c")) {
				playersDrawCards();
				if (playersCompare() > 0) {
					System.out.println("P1 Wins the hand.");
					System.out.println("Before State: ");
					System.out.print("P1 Hand: ");
					aw.dumpCards();
					System.out.print("P2 Hand: ");
					bw.dumpCards();
					System.out.print("P1 Deck: ");
					a.dumpCards();
					System.out.print("P2 Deck: ");
					b.dumpCards();
					a.addCard(aw.returnTopCard());
					a.addCard(bw.returnTopCard());
					System.out.println();
					System.out.println("After State: ");
					System.out.print("P1 Hand: ");
					aw.dumpCards();
					System.out.print("P2 Hand: ");
					bw.dumpCards();
					System.out.print("P1 Deck: ");
					a.dumpCards();
					System.out.print("P2 Deck: ");
					b.dumpCards();
				} else if (playersCompare() < 0) {
					System.out.println("P2 Wins the hand.");
					System.out.println("Before State: ");
					System.out.print("P1 Hand: ");
					aw.dumpCards();
					System.out.print("P2 Hand: ");
					bw.dumpCards();
					System.out.print("P1 Deck: ");
					a.dumpCards();
					System.out.print("P2 Deck: ");
					b.dumpCards();
					b.addCard(aw.returnTopCard());
					b.addCard(bw.returnTopCard());
					System.out.println();
					System.out.println("After State: ");
					System.out.print("P1 Hand: ");
					aw.dumpCards();
					System.out.print("P2 Hand: ");
					bw.dumpCards();
					System.out.print("P1 Deck: ");
					a.dumpCards();
					System.out.print("P2 Deck: ");
					b.dumpCards();
				} else if (playersCompare() == 0) {
					System.out.println("Tie");
					System.out.println("P1 Hand: ");
					aw.dumpCards();
					System.out.println("P2 Hand: ");
					bw.dumpCards();
					tie();
				} else {
					System.out.println("Error");
					System.out.println(playersCompare());
				}
				repOK();
			} else {
				System.out.println("Quitting 7");
		}
	}
}
