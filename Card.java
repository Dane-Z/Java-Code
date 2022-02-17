package Blackjack;
/**
 * This class gets the suit and rank for a card from the Suit and Rank enums
 * then returns a String representing the generated card
 * @author Dane
 * @version 1.0
 */
public class Card 
{
	private Suit suit;
	private Rank rank;
	/** 
	 * @param Suit suit -- a suit string from enum Suit
	 * @param Rank rank -- a rank string from enum Rank
	 * @return a String combining both strings to create a card
	 */
	Card(Suit suit, Rank rank)
	{
		this.suit = suit;
		this.rank = rank;
	}
	public Rank getRank()
	{
		return this.rank;
	}
	public String toString()
	{
		return this.rank.toString() + " of " + this.suit.toString();
	}
}