package com.example.tylerbwong.cardgame.version1_0.blackjack;

import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.components.Deck;
import com.example.tylerbwong.cardgame.version1_0.components.Hand;

import java.util.Observable;

/**
 * @author Tyler Wong
 */
public class BlackJack extends Observable {
   private Deck deck;
   private Hand userHand;
   private Hand computerHand;
   final static int BLACK_JACK = 21;
   final static int DEALER_HIT = 17;

   public BlackJack() {
      deck = new Deck();
      for (int i = 0; i < 2; i++) {
         deck.shuffle();
      }
      userHand = new Hand();
      computerHand = new Hand();
   }

   /*
    * Function: dealBlack
    * Description: deals two cards to each player
    * Parameters: N/A
    * Return: N/A
    */
   public void dealBlack() {
      for (int i = 0; i < 2; i++) {
         userHand.draw(deck);
         computerHand.draw(deck);
      }
   }

   /*
    * Function: hit
    * Description: gives card to user
    * Parameters: i - a variable of type int
    * Return: N/A
    */
   public boolean hit(int i) {
      if (i == 1) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("Hit me!");
         Card c = userHand.draw(deck);
         System.out.println("You received: " + c.toString());
         System.out.println("___________________________________");
         System.out.println("");
         return true;
      } else {
         System.out.println("You stayed.");
         return false;
      }
   }

   /*
    * Function: computerTurn
    * Description: Decides what actions the computer takes
    * Parameters: N/A
    * Return: N/A
    */
   public int computerTurn() {
      int returnValue = - 1;
      boolean done = false;
      int computerHandSum = 0;
      while (! done) {
         System.out.println("");
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("The dealer's card are: ");
         printComputerHand();
         System.out.println("___________________________________");
         System.out.println("");
         computerHandSum = addBetterHand(computerHand);
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("The dealer is currently at: " + computerHandSum);
         System.out.println("___________________________________");
         System.out.println("");
         if (computerHandSum < DEALER_HIT) {
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("The dealer hit!");
            Card c = computerHand.draw(this.deck);
            System.out.println("The dealer received: " + c.toString());
            System.out.println("___________________________________");
            System.out.println("");
         } else {
            if (computerHandSum > BLACK_JACK) {
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("The dealer busted!");
               System.out.println("___________________________________");
               System.out.println("");
               returnValue = 0; // return 0 if over 21
            } else if (computerHandSum == BLACK_JACK) {
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("The dealer got blackjack!");
               System.out.println("___________________________________");
               System.out.println("");
               returnValue = 1; // return 1 if 21
            } else {
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("The dealer stayed.");
               System.out.println("___________________________________");
               System.out.println("");
            }
            done = true;
         }
      }
      return returnValue;
   }

   /*
    * Function: getUserHand
    * Description: gets the user's hand
    * Parameters: N/A
    * Return: a hand data type
    */
   public Hand getUserHand() {
      return userHand;
   }

   /*
    * Function: getComputerHand
    * Description: gets the computer's hand
    * Parameters: N/A
    * Return: a hand data type
    */
   public Hand getComputerHand() {
      return computerHand;
   }

   /*
    * Function: addHand
    * Description: adds the values of the player's hand where A = 1
    * Parameters: h - a variable of type hand
    * Return: an int data type
    */
   private int addHand(Hand h) {
      int sum = 0;
      for (int i = 0; i < h.getSize(); i++) {
         int value = h.getCard(i).getNum();
         if (value == 11 || value == 12 || value == 13) {
            value = 10;
         }
         if (value == 14) {
            value = 1;
         }
         sum += value;
      }
      return sum;
   }

   /*
    * Function: addHandAlt
    * Description: adds the values of the player's hand where A = 11
    * Parameters: h - a variable of type hand
    * Return: an int data type
    */
   private int addHandAlt(Hand h) {
      int sum = 0;
      for (int i = 0; i < h.getSize(); i++) {
         int value = h.getCard(i).getNum();
         if (value == 11 || value == 12 || value == 13) {
            value = 10;
         }
         if (value == 14) {
            value = 11;
         }
         sum += value;
      }
      return sum;
   }

   /*
    * Function: betterHand
    * Description: chooses the better hand
    * Parameters: h - a variable of type hand
    * Return: an int data type
    */
   public int addBetterHand(Hand h) {
      int sum = addHand(h);
      int sumAlt = addHandAlt(h);
      if (sum == sumAlt || sumAlt > BLACK_JACK) {
         return sum;
      } else if (sumAlt > sum) {
         return sumAlt;
      }
      return sumAlt;
   }

   /*
    * Function: bust
    * Description: determines if the game is over
    * Parameters: userHandSum - a variable of data type int
    * Return: primitive type int
    */
   public int bust(int userHandSum) {
      if (userHandSum >= BLACK_JACK) {
         if (userHandSum > BLACK_JACK) {
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("You bust!");
            System.out.println("___________________________________");
            System.out.println("");
            return 0; // return 0 if greater than 21
         }
         if (userHandSum == BLACK_JACK) {
            System.out.println("___________________________________");
            System.out.println("");
            System.out.println("You got blackjack!");
            System.out.println("___________________________________");
            System.out.println("");
            return 1; // return 1 if exactly 21
         }
      }
      return - 1; // return -1 if anything else
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
         System.out.println(play.toString());
      }
   }

   /*
    * Function: printComputerHand
    * Description: prints out the computer hand
    * Parameters: N/A
    * Return: N/A
    */
   public void printComputerHand() {
      for (int i = 0; i < computerHand.getSize(); i++) {
         Card play = computerHand.getCard(i);
         System.out.println(play.toString());
      }
   }
}