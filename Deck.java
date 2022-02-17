package Blackjack;
/**
 * This class generates decks and handles deck functions
 * such as shuffling, drawing, removing, returning cards,
 * and getting card values for both player and the dealer.
 * It uses ArrayList to make a resizable array from the Card class
 * which is necessary to add/remove cards for decks,
 * and uses the Collections class for its shuffle() function.
 * @author Dane
 * @version 1.0
 *
 */
import java.util.ArrayList;
import java.util.Collections;


public class Deck
{
	private ArrayList<Card> cards;
	
	/* Constructor
	 */
	public Deck()
	{
		this.cards = new ArrayList<Card>();
	}
	/** Builds 52 card deck using for-each loops 
	 * @param Suit (4) 
	 * @param Rank (13)
	 */
	public void buildDeck()
	{
		for(Suit cardSuit : Suit.values())
		{
			for(Rank cardRank : Rank.values())
			{
				/* Adds a card to the deck */
				this.cards.add(new Card(cardSuit, cardRank));
			}
		}
	}
	public void shuffle()
	{
		Collections.shuffle(this.cards);
	}
	/**
	 * Draws from the beginning of the array list i.e. top of the Deck
	 * and removes that card from play
	 * @param deck -- a deck from the main game logic
	*/
	public void draw(Deck deck)
	{
		this.cards.add(deck.getCard(0));
		deck.removeCard(0);
	}
	/** @return value of cards in hand 
	 */
	public int cardValue()
	{
		int totalValue = 0;
		int aces = 0;
		for(Card aCard : this.cards)
		{
			switch(aCard.getRank())
			{
			case Ace: aces += 1; break;
			case Two:  totalValue += 2; break;
			case Three: totalValue += 3; break;
			case Four: totalValue += 4; break;
			case Five: totalValue += 5; break;
			case Six:  totalValue += 6; break;
			case Seven: totalValue += 7; break;
			case Eight: totalValue += 8; break;
			case Nine: totalValue += 9; break;
			case Ten: totalValue += 10; break;
			case Jack: totalValue += 10; break;
			case Queen: totalValue += 10; break;
			case King: totalValue += 10; break;
			}
		}
		/* Accounts for aces having a value of 1 or 11
		 * depending on dealer/player's total card value
		 */
		for(int i = 0; i < aces; i++)
		{
			if(totalValue > 10) 
			{
				totalValue += 1;
			}
			else
			{
				totalValue += 11;
			}
		}
		/**@return total value of both cards
		 * for both player and dealer
		 */
		return totalValue;
	}
	/**
	 * @param i -- the index value for the cards array when drawing from a deck
	 * removes a card
	 */
	public void removeCard(int i)
	{
		this.cards.remove(i);
	}
	/**
	 * @param i -- the index value for the cards array when drawing from a deck
	 * @return pulls a card from a built deck
	 */
	public Card getCard(int i)
	{
		return this.cards.get(i);
	}
	/**
	 * @param addCard -- card from a deck
	 */
	public void addCard(Card addCard)
	{
		this.cards.add(addCard);
	}
	/**@return the size of the deck as an integer
	 */
	public int deckSize()
	{
		return this.cards.size();
	}
	/**
	 * @param returnTo -- a deck in the main game
	 * returns cards from play to a deck every new round
	 */
	public void returnCardToDeck(Deck returnTo)
	{
		int thisDeck = this.cards.size();
		for(int i = 0; i < thisDeck; i++)
		{
			returnTo.addCard(this.getCard(i));
		}
		for(int i = 0; i < thisDeck; i++)
		{
			this.removeCard(0);
		}
	}
	/**
	 * @return the .toString() from class Card
	 * representing a playing card.
	 */
	public String toString()
	{
		String cardOutput = "";
		for(Card c : this.cards)
		{
			cardOutput += "\n" + c.toString();
		}
		return cardOutput;
	}
	
}