package com.example.tylerbwong.cardgame.version1_0.crazyeight;

import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.components.Deck;
import com.example.tylerbwong.cardgame.version1_0.components.Hand;
import com.example.tylerbwong.cardgame.version1_0.components.LQueue.MyException;

import java.util.*;

/**
 * @author Tyler Wong
 */
public class CrazyEight extends Observable {
   final int GAME_ON = 2;
   final int CRAZY_8 = 8;

   private Deck deck;
   private Hand userHand;
   private Hand computerHand;
   private Card cardInPlay;

   // constructor
   public CrazyEight() {
      deck = new Deck();

      for (int i = 0; i < 3; i++) {
         deck.shuffle();
      }

      userHand = new Hand();
      computerHand = new Hand();
      cardInPlay = null;
   }

   /*
    * Function: deal
    * Description: deals the user and the computer 8 cards into their hands
    * and sets the first card in play
    * Parameters: N/A
    * Return: N/A
    */
   public void deal() {
      int numOfCards = 8;
      for (int i = 0; i < numOfCards; i++) {
         userHand.draw(deck);
         computerHand.draw(deck);
      }
      cardInPlay = deck.removeCard();
   }

   /*
    * Function: userDraw
    * Description: makes the user draw a card
    * Parameters: N/A
    * Return: a Card data type
    */
   public Card userDraw() {
      return userHand.draw(deck);
   }

   /*
    * Function: changeSuit
    * Description: changes the current suit
    * Parameters: N/A
    * Return: N/A
    */
   public void changeSuit(int option) {
      boolean done = false;

      while (!done) {
         try {
            switch (option) {
               case 1:
                  cardInPlay = new Card(CRAZY_8, "Spades");
                  done = true;
                  break;
               case 2:
                  cardInPlay = new Card(CRAZY_8, "Hearts");
                  done = true;
                  break;
               case 3:
                  cardInPlay = new Card(CRAZY_8, "Clubs");
                  done = true;
                  break;
               case 4:
                  cardInPlay = new Card(CRAZY_8, "Diamonds");
                  done = true;
                  break;
               default:
                  System.out.println("Invalid input.");
                  done = false;
                  break;
            }
         }
         catch (Exception e) {
            System.out.println("Invalid input.");
            continue;
         }
      }
   }

   /*
    * Function: userTurn
    * Description: puts the user card into play
    * Parameters: index - a primitive int
    * Return: N/A
    */
   public boolean userTurn(int index) {
      if (index == -1) {
         cardInPlay = userHand.play(0);
         return true;
      }
      else if (validTurn(userHand.getCard(index - 1)) && (index - 1) < userHand.getSize()) {
         cardInPlay = userHand.play(index - 1);
         return true;
      }
      return false;
   }

   /*
    * Function: computerTurn
    * Description: decides what card the computer should play and puts it
    * into play
    * Parameters: N/A
    * Return: N/A
    */
   public void computerTurn() {
      int handSize = computerHand.getSize();
      int handIndex = 0;
      boolean done = false;
      while (handIndex < handSize && ! done) {
         if (validTurn(computerHand.getCard(handIndex)) &&
               computerHand.getCard(handIndex).getNum() != CRAZY_8) {
            done = true;
            cardInPlay = computerHand.play(handIndex);
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("The computer has played a card.");
            System.out.println("The computer now has " + computerHand.getSize() + " cards.");
            System.out.println("___________________________________");
            System.out.println("");
         }
         handIndex++;
      }
      handIndex = 0;
      while (handIndex < handSize && !done) {
         if (computerHand.getCard(handIndex).getNum() == CRAZY_8) {
            done = true;
            cardInPlay = computerHand.play(handIndex);
            computerCrazy8();
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("The computer has played a card.");
            System.out.println("The computer now has " + computerHand.getSize() + " cards.");
            System.out.println("___________________________________");
            System.out.println("");
         }
         handIndex++;
      }
      while (!done) {
         try {
            computerHand.draw(deck);
            System.out.println("The computer drew a card!");
            if (validTurn(computerHand.getCard(computerHand.getSize() - 1))) {
               cardInPlay = computerHand.play(computerHand.getSize() - 1);
               done = true;
               if (cardInPlay.getNum() == CRAZY_8) {
                  computerCrazy8();
               }
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("The computer has played a card.");
               System.out.println("The computer now has " + computerHand.getSize() + " cards.");
               System.out.println("___________________________________");
               System.out.println("");
            }
         } catch (IndexOutOfBoundsException e) {
            done = true;
         } catch (MyException e) {
            done = true;
         }
      }
   }

   /*
    * Function: computerCrazy8
    * Description: chooses the suit of the 8
    * Parameter: N/A
    * Return: N/A
    */
   private void computerCrazy8() {
      if (computerHand.getSize() > 0) {
         // 0: spades, 1: hearts, 2: clubs, 3: diamonds
         int[] suitCount = new int[4];
         for (int i = 0; i < computerHand.getSize(); i++) {
            switch (computerHand.getCard(i).getSuit()) {
               case "Spades": {
                  suitCount[0]++;
                  break;
               }
               case "Hearts": {
                  suitCount[1]++;
                  break;
               }
               case "Clubs": {
                  suitCount[2]++;
                  break;
               }
               case "Diamonds": {
                  suitCount[3]++;
                  break;
               }
               default: {
                  break;
               }
            }
         }
         int max = suitCount[0];
         int indexMax = 0;
         for (int i = 1; i < 4; i++) {
            if (suitCount[i] > max) {
               max = suitCount[i];
               indexMax = i;
            }
         }
         String suit = "";
         switch (indexMax) {
            case 0: {
               suit = "Spades";
               break;
            }
            case 1: {
               suit = "Hearts";
               break;
            }
            case 2: {
               suit = "Clubs";
               break;
            }
            case 3: {
               suit = "Diamonds";
               break;
            }
            default: {
               break;
            }
         }
         cardInPlay = new Card(CRAZY_8, suit);
      }
      else {
         cardInPlay = new Card(CRAZY_8, "Spades");
      }
      System.out.println("The computer changed the suit to: " + cardInPlay.getSuit());
   }

   /*
    * Function: validTurn
    * Description: checks if a card can be put onto the card in play
    * Parameters: c - a variable of type Card
    * Return: N/A
    */
   private boolean validTurn(Card c) {
      return (c.getNum() == cardInPlay.getNum() || c.getSuit().equals(cardInPlay.getSuit())
            || c.getNum() == 8);
   }

   /*
    * Function: gameOver
    * Description: checks if the game is over
    * Parameters: N/A
    * Return: primitive boolean type
    */
   public int gameOver() {
      int gameOver = GAME_ON;

      if (deck.isEmpty()) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("No more cards in the deck.");
         System.out.println("Game Over! It's a draw!");
         System.out.println("___________________________________");
         System.out.println("");
         gameOver = -1;
      }
      else if (computerHand.getSize() == 0) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("The computer ran out of cards.");
         System.out.println("Game Over! You lose. :(");
         System.out.println("___________________________________");
         System.out.println("");
         gameOver = 0;
      }
      else if (userHand.getSize() == 0) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("You ran out of cards!");
         System.out.println("Game Over! You win! :)");
         System.out.println("___________________________________");
         System.out.println("");
         gameOver = 1;
      }
      return gameOver;
   }

   /*
    * Function: getUserHand
    * Description: gets user hand
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Hand getUserHand() {
      return userHand;
   }

   /*
    * Function: getComputerHand
    * Description: gets computer hand
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Hand getComputerHand() {
      return computerHand;
   }

   /*
    * Function: getCardInPlay
    * Description: gets the card in play
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Card getCardInPlay() {
      return cardInPlay;
   }

   /*
    * Function: printUserHand
    * Description: prints out the user hand
    * Parameters: N/A
    * Return: N/A
    */
   public void printUserHand() {
      for (int i = 0; i < userHand.getSize(); i++) {
         Card play = userHand.getCard(i);
         System.out.println((i + 1) + " - " + play.toString());
      }
   }

   /*
    * Function: getDeck
    * Description: gets the deck
    * Parameters: N/A
    * Return: a deck data type
    */
   public Deck getDeck() {
      return deck;
   }
}
