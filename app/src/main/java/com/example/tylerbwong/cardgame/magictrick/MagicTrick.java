package com.example.tylerbwong.cardgame.magictrick;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicTrick {
   private Deck deck;
   private Card solution;
   private int choice;
   private ArrayList<Card> trickDeck;
   private ArrayList<Card> pile1;
   private ArrayList<Card> pile2;
   private ArrayList<Card> pile3;
   private ArrayList<Card>[] piles;

   final static int TOP = 0;
   final static int MIDDLE = 1;
   final static int BOTTOM = 2;
   final static int NUM_CARDS = 27;

   LinkedHashMap<Integer, OrderSet> order;

   public MagicTrick(Deck deck, int choice) {
      this.deck = deck;
      this.choice = choice;
      this.deck.shuffle();
      makeTrickDeck();
      initOrders();
   }

   private void initOrders() {
      order = new LinkedHashMap<Integer, OrderSet>();

      order.put(0, new OrderSet(TOP, TOP, TOP));
      order.put(1, new OrderSet(MIDDLE, TOP, TOP));
      order.put(2, new OrderSet(BOTTOM, TOP, TOP));
   }

   private void makeTrickDeck() {
      // initialize 27-card deck
      trickDeck = new ArrayList<>();
      for (int index = 0; index < NUM_CARDS; index++) {
         trickDeck.add(deck.removeCard());
      }
   }

   private void dealToPiles() {
      for (int index = 0; index < NUM_CARDS; index++) {
         if (index < 9) {
            pile1.add(trickDeck.get(index));
         }
         else if (index < 18) {
            pile2.add(trickDeck.get(index));
         }
         else {
            pile3.add(trickDeck.get(index));
         }
      }
      piles = new ArrayList[]{pile1, pile2, pile3};
   }

   private void returnPilesToDeck() {
      trickDeck.clear();
   }

   public boolean verifyChoice(ArrayList<Card> pile) {
      if (!pile.contains(solution)) {
         return false;
      }
      return true;
   }

   public void doTrick() {
      dealToPiles();
   }

   private class OrderSet {
      public int round1;
      public int round2;
      public int round3;

      public OrderSet(int round1, int round2, int round3) {
         this.round1 = round1;
         this.round2 = round2;
         this.round3 = round3;
      }
   }
}
