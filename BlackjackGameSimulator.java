package Blackjack;
/**
 * Mini Project 2 - Blackjack
 * En.605.201.85.SU20
 * @author Dane
 * @version 1.0
 * 
 */
import java.util.Scanner; 

public class BlackjackGameSimulator 
{
	public static void main(String [] args) 
	{
		int currentCash = 100;
		int bet = 0;
		int playerInput;
		int hitOrStay;
		Scanner input = new Scanner(System.in);
		/* Welcome Message to start the game. */
		System.out.println("Welcome to the Blackjack Table!\nWould you like to play? (type '1' for Yes, anything else for No)");
		Deck gameDeck = new Deck();
		gameDeck.buildDeck();
		gameDeck.shuffle();
		Deck playerCards = new Deck();
		Deck dealerCards = new Deck();
		
		playerInput = input.nextInt();
		/*
		 * '1' If player chooses to play.
		 */
		if(playerInput == 1)
		{
			System.out.println("Changing $100!!");
			do
			{
				boolean newRound = false;
				do
				{
					playerCards.draw(gameDeck);
					playerCards.draw(gameDeck);

					dealerCards.draw(gameDeck);
					dealerCards.draw(gameDeck);
					System.out.println("You have $" + currentCash + ". How much will you wager on this hand?");
					bet = input.nextInt();
					while(true)
					{
						/* Takes player bet each round 
						 * based on current cash on hand
						 * and resets value boolean to signify 
						 * a new round of play.
						 */
						
						newRound = false;
						/*Player Hand and Value
						 */
						System.out.println("Player Hand:\n" + playerCards.toString() + "\nValue: " + playerCards.cardValue());
						/*Dealer Hand and Value
						 * only shows 1 card.
						 */
						System.out.println("Dealer Hand:\n" + dealerCards.getCard(0).toString() + "[Face Down]");
						/*Checks to see if either the dealer or player
						 * got a BlackJack before hitting.
						 */
						if(dealerCards.cardValue() == 21 && newRound == false)
						{
							System.out.println("Dealer has Blackjack! Bad Luck.");
							currentCash -= bet;
							newRound = true;
							break;
						}
						else if(playerCards.cardValue() == 21 && newRound == false)
						{
							System.out.println("You got Blackjack! Great Luck! You receive 2 x $" + bet);
							currentCash += 2*bet;
							newRound = true;
							break;
						}
						/* Ask if they will 'hit' or 'stay'.
						 */
						System.out.println("Type '1' to Hit to receive another card or '2' to Stay at current value");
						hitOrStay = input.nextInt();
						/*'1' If they choose to hit
						 */
						if(hitOrStay == 1)
						{
							playerCards.draw(gameDeck);
							/* '-1' because size isn't 0 based
							 * prints card that was added to hand most recently.
							 */
							System.out.println("You Draw a(n) " + playerCards.getCard(playerCards.deckSize() - 1).toString());
							/* Check to see if player has lost round by going over 21
							 * then sets newRound to true to start a new round
							 */
							if(playerCards.cardValue() > 21 && newRound == false)
							{
								System.out.println("You bust with a Value of: " + playerCards.cardValue() + " and lose: " + bet);
								currentCash -= bet;
								newRound = true;
								break;
							}
						}
						/*if they choose '2' to stay.
						 */
						else
						{
							break;
						}
					}
					/*Show Dealer's hand
					 */
					System.out.println("Dealer's Cards: " + dealerCards.toString());
					/*Dealer draws according to standard house rules,
					 * hits when <16, stays >=17.
					 * @param dealerCards.cardValue() -- value of dealer's cards.
					 * @param newRound -- new round must be false or it won't perform check.
					 */
					while(dealerCards.cardValue() < 17 && newRound == false)
					{
						dealerCards.draw(gameDeck);
						System.out.println("Dealer draws a(n) " + dealerCards.getCard(dealerCards.deckSize() -1).toString());
					}
					/*If Dealer busts and the round is not over.
					 * @param dealerCards.cardValue() -- value of dealer's cards.
					 * @param newRound -- new round must be false or it won't perform check.
					 */
					if((dealerCards.cardValue() > 21) && newRound == false)
					{
						System.out.println("Dealer busts with a Value of: " + dealerCards.cardValue() + " you receive: " + bet);
						currentCash += bet;
						newRound = true;
					}
					/*Check for Winner or Draw.
					 * @param dealerCards.cardValue() -- value of dealer's cards.
					 * @param playerCards.cardValue() -- value of player's cards.
					 * @param newRound -- new round must be false or it won't perform a check.
					 */
					if (dealerCards.cardValue() > playerCards.cardValue() && newRound == false)
					{
						System.out.println("Dealer Wins! You lose your bet.");
						currentCash -= bet;
						newRound = true;
					}
					/* Shows dealer's hand value after both participants's turns.
					 * @param dealerCards.cardValue() --  value of dealer's cards.
					 */
					System.out.println("Dealer hand value: " + dealerCards.cardValue());
					
					/*If Player Wins.
					 * @param playerCards.cardValue() -- value of dealer's cards.
					 * @param dealerCards.cardValue() -- value of player's cards.
					 * @param newRound -- new round must be false or it won't perform a check.
					 */
					if(playerCards.cardValue() > dealerCards.cardValue() && newRound == false)
					{
						System.out.println("You Win!! You receive $" + bet);
						currentCash += bet;
						newRound = true;
					}
					/* If there's a Push.
					 * @param playerCards.cardValue() -- value of dealer's cards.
					 * @param dealerCards.cardValue() -- value of player's cards.
					 * @param newRound -- new round must be false or it won't perform a check.
					*/
					else if(playerCards.cardValue() == dealerCards.cardValue() && newRound == false)
					{
						System.out.println("You and the Dealer Tie! It's a PUSH!");
						newRound = true;
					}
					/*If player loses to Dealer.
					 * @param newRound -- new round must be false or it won't perform a check.
					*/
					else if(newRound == false)
					{
						System.out.println("The Dealer's: " + dealerCards.cardValue() + " beats your: " + playerCards.cardValue());
						currentCash -= bet;
						newRound = true;
					}

					playerCards.returnCardToDeck(gameDeck);
					dealerCards.returnCardToDeck(gameDeck);
					gameDeck.shuffle();
					System.out.println("**************************************************************");
				}
				/* Check to see if bet is within cash limit, if not it cashes player out.
				 * @param bet -- player's bet.
				 * @param currentCash -- game currency.
				 */
				while(bet <= currentCash);
			}
			/* Checks to see if the player still has game currency and if they opted to play.
			 * @param currentCash -- game currency.
			 * @param playerInput -- start game input
			 */
			while(currentCash > 0 && playerInput == 1);
			System.out.println("You cash out with $"+currentCash+"!");
		}
		else
		{
			System.out.println("You cash out with $"+currentCash+"!");
		}
	}
}

