package com.example.tylerbwong.cardgame.components;

import java.util.ArrayList;

public class Hand {
   // array of cards
   private int num;
   private ArrayList<Card> hand;

   // default hand constructor
   public Hand() {
      this.hand = new ArrayList<>();
      this.num = 0;
   }

   // hand constructor
   public Hand(Deck d, int num) {
      this.num = num;

      for (int i = 0; i < this.num; i++) {
         this.hand.add(i, d.removeCard());
      }
   }

   /*
    * Function: draw
    * Description: draws a card from the deck
    * Parameters: d - a variable of type Deck
    * Return: a type Card
    */
   public Card draw(Deck d) {
      Card ret = d.removeCard();
      this.hand.add(ret);
      this.num++;
      return ret;
   }

   /*
    * Function: play
    * Description: plays a card from the hand
    * Parameters: c - a variable of type int
    * Return: a type Card
    */
   public Card play(int c) {
      if (c == - 1) {
         c = 0;
      }
      Card ret = this.hand.remove(c);
      this.hand.trimToSize();
      this.num--;
      return ret;
   }

   /*
    * Function: getCard
    * Description: returns the card at the specified index
    * Parameters: index - the index of the card in the hand
    * Return: a type Card
    */
   public Card getCard(int index) {
      return hand.get(index);
   }

   /*
    * Function: getSize
    * Description: returns the number of cards in hand
    * Parameters: N/A
    * Return: a primitive int
    */
   public int getSize() {
      return this.num;
   }

   /*
    * Function: addCard
    * Descrption: adds a card to players hand
    * Parameters: c - a variable of data type card
    * Return: N/A
    */
   public void addCard(Card c) {
      this.hand.add(c);
      this.num++;
   }
}

