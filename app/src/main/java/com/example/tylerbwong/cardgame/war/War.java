package com.example.tylerbwong.cardgame.war;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;
import com.example.tylerbwong.cardgame.components.Hand;

import java.util.*;

public class War {
   private Deck deck;
   private Hand userHand;
   private Hand computerHand;
   private ArrayList<Card> prize;

   // game constructor
   public War() {
      deck = new Deck();
      for (int i = 0; i < 2; i++) {
         deck.shuffle();
      }
      userHand = new Hand();
      computerHand = new Hand();

      for (int i = 0; i < 26; i++) {
         userHand.draw(deck);
         computerHand.draw(deck);
      }
      prize = new ArrayList<>();
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
    * Function: getCompHand
    * Description: gets the computer's hand
    * Parameters: N/A
    * Return: a hand data type
    */
   public Hand getCompHand() {
      return computerHand;
   }

   /*
    * Function: userPlay
    * Description: plays a card from user's hand
    * Parameters: N/A
    * Return: a card data type
    */
   private Card userPlay() {
      Card play = userHand.play(0);
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("You played " + play.toString());
      return play;
   }

   /*
    * Function: computerPlay
    * Description: plays a card from computer's hand
    * Parameters: N/A
    * Return: a card data type
    */
   private Card computerPlay() {
      Card play = computerHand.play(0);
      System.out.println("");
      System.out.println("The computer played " + play.toString());
      System.out.println("___________________________________");
      System.out.println("");
      return play;
   }

   /*
    * Function: compareCards
    * Description: compares two cards to decide outcome
    * Parameters: N/A
    * Return: N/A
    */
   public void compareCards() {
      // have user and computer play cards
      Card player = userPlay();
      Card comp = computerPlay();

      // assign number values to compare
      int card1 = player.getNum();
      int card2 = comp.getNum();

      // add played cards to prize pool
      prize.add(player);
      prize.add(comp);
      int size = prize.size();

      // if player wins
      if (card1 > card2) {
         // add cards to player hand from prize
         for (int i = 0; i < size; i++) {
            userHand.addCard(prize.remove(0));
         }
         System.out.println("The cards are yours!");
      }

      // if computer wins
      if (card1 < card2) {
         // add cards to computer hand from prize
         for (int j = 0; j < size; j++) {
            computerHand.addCard(prize.remove(0));
         }
         System.out.println("You lost the cards.");
      }

      // if war
      if (card1 == card2) {
         System.out.println("___________________________________");
         System.out.println("");
         System.out.println("\\         / /\\   |\\  |");
         System.out.println(" \\  / \\  / /__\\  |/  |");
         System.out.println("  \\/   \\/ /    \\ |\\  o");
         System.out.println("___________________________________");
         System.out.println("");
         int sizeUser = 3;
         int sizeComp = 3;

         // make sure user has enough cards
         if (userHand.getSize() <= 3) {
            sizeUser = userHand.getSize() - 1;
         }

         // make sure comp has enough cards
         if (computerHand.getSize() <= 3) {
            sizeComp = computerHand.getSize() - 1;
         }

         // user and computer play 3 cards each
         for (int k = 0; k < sizeUser; k++) {
            prize.add(userHand.play(0));
         }
         for (int l = 0; l < sizeComp; l++) {
            prize.add(computerHand.play(0));
         }

         // if possible compare the two cards again
         if (userHand.getSize() > 0 && computerHand.getSize() > 0) {
            compareCards();
         }
      }
   }

   /*
    * Function: keepScore
    * Description: keeps the score of the game
    * Parameters: N/A
    * Return: N/A
    */
   public void keepScore() {
      System.out.println("Player: " + userHand.getSize());
      System.out.println("Computer: " + computerHand.getSize());
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("Play another! (press 1)");
   }

   /* Function: endGame
    * Description: decides when the game ends
    * Parameters: N/A
    * Return: a boolean data type
    */
   public boolean endGame() {
      if (userHand.getSize() == 0) {
         System.out.println("The computer wins! :(");
         return true;
      }
      if (computerHand.getSize() == 0) {
         System.out.println("You win! :)");
         return true;
      }
      return false;
   }
}

