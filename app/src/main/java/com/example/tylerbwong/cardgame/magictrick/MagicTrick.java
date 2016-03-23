package com.example.tylerbwong.cardgame.magictrick;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicTrick implements Serializable {
   private Deck deck;
   private Card solution;
   private int choice;
   private Card card;
   private ArrayList<Card> trickDeck;
   private ArrayList<Card> pile1;
   private ArrayList<Card> pile2;
   private ArrayList<Card> pile3;
   private ArrayList<Card>[] piles;
   private int[] order;
   private int stage = 0;
   private int currentChoice = - 1;

   final static int NUM_CARDS = 27;
   final static int STAGES = 3;

   public MagicTrick(Deck deck) {
      this.deck = deck;
      this.deck.shuffle();
      pile1 = new ArrayList<Card>();
      pile2 = new ArrayList<Card>();
      pile3 = new ArrayList<Card>();
      makeTrickDeck();
   }

   private void convertToBaseThree() {
      int rem, num = choice;
      int base = STAGES;
      int index = 0;
      order = new int[STAGES];

      while (num != 0) {
         rem = num % base;
         num = num / base;
         order[index++] = rem;
      }
   }

   private void makeTrickDeck() {
      // initialize 27-card deck
      trickDeck = new ArrayList<>();
      for (int index = 0; index < NUM_CARDS; index++) {
         trickDeck.add(deck.removeCard());
      }
   }

   private void repopulateTrickDeck() {
      for (ArrayList<Card> pile : piles) {
         for (int index = 0; index < pile.size(); index++) {
            trickDeck.add(pile.get(index));
         }
      }
   }

   public void dealToPiles() {
      int index1 = 0;
      pile1.clear();
      pile2.clear();
      pile3.clear();
      piles = new ArrayList[]{pile1, pile2, pile3};

      while (index1 < NUM_CARDS) {
         for (int index2 = 0; index2 < piles.length; index2++) {
            piles[index2].add(trickDeck.get(index1));
            index1++;
         }
      }
   }

   public void setChoice(int choice) {
      this.choice = choice;
      convertToBaseThree();
   }

   public void setCard(int choice) {
      card = trickDeck.get(choice);
   }

   public ArrayList<Card> getTrickDeck() {
      return trickDeck;
   }

   public ArrayList<Card>[] getPiles() {
      return piles;
   }

   public int getStage() {
      return stage;
   }

   public void returnPilesToDeck() {
      trickDeck.clear();

      // TODO return piles in correct order
      if (stage == 0) {
         swapPiles(currentChoice, order[stage]);
         stage++;
      } else if (stage == 1) {
         swapPiles(currentChoice, order[stage]);
         stage++;
      } else {
         swapPiles(currentChoice, order[stage]);
         stage++;
      }
      repopulateTrickDeck();
   }

   public boolean verifyChoice(int userChoice) {
      //if (piles[userChoice].contains(card)) {
      currentChoice = userChoice;
      return true;
      //}
      //return false;
   }

   private void swapPiles(int from, int to) {
      ArrayList<Card> temp;

      temp = piles[from];
      piles[from] = piles[to];
      piles[to] = temp;
   }

   public Card getSolution() {
      return trickDeck.get(choice);
   }
}
