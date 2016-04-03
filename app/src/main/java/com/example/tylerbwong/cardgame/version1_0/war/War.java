package com.example.tylerbwong.cardgame.version1_0.war;

import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.components.Deck;
import com.example.tylerbwong.cardgame.version1_0.components.Hand;

import java.util.*;

/**
 * @author Tyler Wong
 */
public class War extends Observable {
   private Deck deck;
   private Hand userHand;
   private Hand computerHand;
   private ArrayList<Card> prize;
   private Card userCardInPlay;
   private Card compCardInPlay;
   private int prizeSize;
   public GameState currentState;
   public GameState previousState;

   public enum GameState {
      COMP_COLLECT,
      HUM_COLLECT,
      HUM_TURN,
      COMP_WIN,
      HUM_WIN,
   }

   // game constructor
   public War() {
      // create deck and shuffle
      deck = new Deck();
      deck.shuffle();

      // create new hands for user and comp
      userHand = new Hand();
      computerHand = new Hand();

      for (int i = 0; i < 26; i++) {
         userHand.draw(deck);
         computerHand.draw(deck);
      }

      // init prize ArrayList
      prize = new ArrayList<>();

      // set GameState
      currentState = GameState.HUM_TURN;
      previousState = null;
   }

   public int getPrizeSize() {
      return prizeSize;
   }

   public Card getUserCardInPlay() {
      return userCardInPlay;
   }

   public Card getCompCardInPlay() {
      return compCardInPlay;
   }

   /**
    * Reverts to previous state
    */
   public void revertState() {
      currentState = previousState;
   }

   /**
    * Gets the currentState
    * @return the currentState
    */
   public GameState getCurrentState() {
      return currentState;
   }

   /**
    * Sets the current state to specified value
    * and saves old state to previousState
    * @param state - state for currentState
    *              to be set to
    */
   public void setCurrentState(GameState state) {
      previousState = currentState;
      currentState = state;

      setChanged();
      notifyObservers();
   }

   /**
    * Gets the previousState
    * @return the previousState
    */
   public GameState getPreviousState() {
      return previousState;
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

   public void keepScore() {
      System.out.println("Player: " + userHand.getSize());
      System.out.println("Computer: " + computerHand.getSize());
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("Play another! (press 1)");
   }

   public void doTurn() {
      Card userCard = userHand.play(0);
      setUserCardInPlay(userCard);
      Card compCard = computerHand.play(0);
      setCompCardInPlay(compCard);
      prize.add(userCard);
      prize.add(compCard);

      compareCards();
   }

   private void setUserCardInPlay(Card card) {
      userCardInPlay = card;
   }

   private void setCompCardInPlay(Card card) {
      compCardInPlay = card;
   }

   /*
    * Function: compareCards
    * Description: compares two cards to decide outcome
    * Parameters: N/A
    * Return: N/A
    */
   public void compareCards() {
      // assign number values to compare
      int card1 = userCardInPlay.getNum();
      int card2 = compCardInPlay.getNum();

      prizeSize = prize.size();

      // if player wins
      if (card1 > card2) {
         // add cards to player hand from prize
         for (int i = 0; i < prizeSize; i++) {
            userHand.addCard(prize.remove(0));
         }
         setCurrentState(GameState.HUM_COLLECT);
      }

      // if computer wins
      else if (card1 < card2) {
         // add cards to computer hand from prize
         for (int j = 0; j < prizeSize; j++) {
            computerHand.addCard(prize.remove(0));
         }
         setCurrentState(GameState.COMP_COLLECT);
      }

      // if war
      else {
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
            doTurn();
         }
      }
   }

   /*
    * Function: keepScore
    * Description: gets the score of the human
    * Parameters: N/A
    * Return: the human score
    */
   public int getHumScore() {
      return userHand.getSize();
   }

   /**
    * Gets score of the computer
    * @return the computer score
    */
   public int getCompScore() {
      return computerHand.getSize();
   }

   /* Function: endGame
    * Description: decides when the game ends
    * Parameters: N/A
    * Return: a boolean data type
    */
   public boolean endGame() {
      boolean isEnd = false;
      if (userHand.getSize() == 0) {
         setCurrentState(GameState.COMP_WIN);
         isEnd = true;
      }
      else if (computerHand.getSize() == 0) {
         setCurrentState(GameState.HUM_WIN);
         isEnd = true;
      }
      return isEnd;
   }
}

