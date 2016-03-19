package com.example.tylerbwong.cardgame.crazyeight;

/********************************************************************************
 * ** Group Members: Brittany Berlanga***Jonathan Tan***Brandon Vo***Tyler Wong***
 * ********************** Class: CPE 103 - Timothy Hawkins ***********************
 * **************************** Section: 09 6-9pm MWF ****************************
 * **************** File: Game.java Updated: 2014/11/2***************************
 ********************************************************************************/

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;
import com.example.tylerbwong.cardgame.components.Hand;
import com.example.tylerbwong.cardgame.components.LQueue.MyException;

public class CrazyEight {
   final int GAME_ON = 2;
   final int CRAZY_8 = 8;

   private Deck deck;
   private Hand userHand;
   private Hand computerHand;
   private Card cardInPlay;

   // constructor
   public CrazyEight() {
      this.deck = new Deck();
      for (int i = 0; i < 3; i++) {
         this.deck.shuffle();
      }
      this.userHand = new Hand();
      this.computerHand = new Hand();
      this.cardInPlay = null;
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
         this.userHand.draw(deck);
         this.computerHand.draw(deck);
      }
      this.cardInPlay = deck.removeCard();
   }

   /*
    * Function: userDraw
    * Description: makes the user draw a card
    * Parameters: N/A
    * Return: a Card data type
    */
   public Card userDraw() {
      return this.userHand.draw(deck);
   }

   /*
    * Function: changeSuit
    * Description: changes the current suit
    * Parameters: N/A
    * Return: N/A
    */
   public void changeSuit(int option) {
      boolean done = false;

      while (! done) {
         try {
            switch (option) {
               case 1: {
                  this.cardInPlay = new Card(CRAZY_8, "Spades");
                  done = true;
                  break;
               }
               case 2: {
                  this.cardInPlay = new Card(CRAZY_8, "Hearts");
                  done = true;
                  break;
               }
               case 3: {
                  this.cardInPlay = new Card(CRAZY_8, "Diamonds");
                  done = true;
                  break;
               }
               case 4: {
                  this.cardInPlay = new Card(CRAZY_8, "Clubs");
                  done = true;
                  break;
               }
               default: {
                  System.out.println("Invalid input.");
                  done = false;
                  break;
               }
            }
         } catch (Exception e) {
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
      if (index == - 1) {
         this.cardInPlay = this.userHand.play(0);
         return true;
      } else if (validTurn(this.userHand.getCard(index - 1)) && (index - 1) < this.userHand.getSize()) {
         this.cardInPlay = this.userHand.play(index - 1);
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
      int handSize = this.computerHand.getSize();
      int handIndex = 0;
      boolean done = false;
      while (handIndex < handSize && ! done) {
         if (validTurn(this.computerHand.getCard(handIndex)) &&
               this.computerHand.getCard(handIndex).getNum() != CRAZY_8) {
            done = true;
            this.cardInPlay = this.computerHand.play(handIndex);
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("The computer has played a card.");
            System.out.println("The computer now has " + this.computerHand.getSize() + " cards.");
            System.out.println("___________________________________");
            System.out.println("");
         }
         handIndex++;
      }
      handIndex = 0;
      while (handIndex < handSize && ! done) {
         if (this.computerHand.getCard(handIndex).getNum() == CRAZY_8) {
            done = true;
            this.cardInPlay = this.computerHand.play(handIndex);
            computerCrazy8();
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("The computer has played a card.");
            System.out.println("The computer now has " + this.computerHand.getSize() + " cards.");
            System.out.println("___________________________________");
            System.out.println("");
         }
         handIndex++;
      }
      while (! done) {
         try {
            this.computerHand.draw(deck);
            System.out.println("The computer drew a card!");
            if (validTurn(this.computerHand.getCard(this.computerHand.getSize() - 1))) {
               this.cardInPlay = this.computerHand.play(this.computerHand.getSize() - 1);
               done = true;
               if (this.cardInPlay.getNum() == CRAZY_8) {
                  computerCrazy8();
               }
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("The computer has played a card.");
               System.out.println("The computer now has " + this.computerHand.getSize() + " cards.");
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
      if (this.computerHand.getSize() > 0) {
         // 0: spades, 1: hearts, 2: clubs, 3: diamonds
         int[] suitCount = new int[4];
         for (int i = 0; i < this.computerHand.getSize(); i++) {
            switch (this.computerHand.getCard(i).getSuit()) {
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
         this.cardInPlay = new Card(CRAZY_8, suit);
      } else {
         this.cardInPlay = new Card(CRAZY_8, "Spades");
      }
      System.out.println("The computer changed the suit to: " + this.cardInPlay.getSuit());
   }

   /*
    * Function: validTurn
    * Description: checks if a card can be put onto the card in play
    * Parameters: c - a variable of type Card
    * Return: N/A
    */
   private boolean validTurn(Card c) {
      return (c.getNum() == this.cardInPlay.getNum() || c.getSuit().equals(this.cardInPlay.getSuit())
            || c.getNum() == 8);
   }

   /*
    * Function: gameOver
    * Description: checks if the game is over
    * Parameters: N/A
    * Return: primitive boolean type
    */
   public int gameOver() {
      if (this.deck.isEmpty()) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("No more cards in the deck.");
         System.out.println("Game Over! It's a draw!");
         System.out.println("___________________________________");
         System.out.println("");
         return - 1;
      } else if (this.computerHand.getSize() == 0) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("The computer ran out of cards.");
         System.out.println("Game Over! You lose. :(");
         System.out.println("___________________________________");
         System.out.println("");
         return 0;
      } else if (this.userHand.getSize() == 0) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("You ran out of cards!");
         System.out.println("Game Over! You win! :)");
         System.out.println("___________________________________");
         System.out.println("");
         return 1;
      }
      return GAME_ON;
   }

   /*
    * Function: getUserHand
    * Description: gets user hand
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Hand getUserHand() {
      return this.userHand;
   }

   /*
    * Function: getComputerHand
    * Description: gets computer hand
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Hand getComputerHand() {
      return this.computerHand;
   }

   /*
    * Function: getCardInPlay
    * Description: gets the card in play
    * Parameters: N/A
    * Return: object of type Hand
    */
   public Card getCardInPlay() {
      return this.cardInPlay;
   }

   /*
    * Function: printUserHand
    * Description: prints out the user hand
    * Parameters: N/A
    * Return: N/A
    */
   public void printUserHand() {
      for (int i = 0; i < this.userHand.getSize(); i++) {
         Card play = this.userHand.getCard(i);
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
      return this.deck;
   }
}
