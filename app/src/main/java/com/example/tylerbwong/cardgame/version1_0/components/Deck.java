package com.example.tylerbwong.cardgame.version1_0.components;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * @author Tyler Wong
 */
public class Deck extends LQueue<Card> implements Parcelable {

   final static int MIN_CARD = 2;
   final static int MAX_CARD = 14;
   // constructs deck
   public Deck() {
      // construct LQueue
      super();

      // add cards to deck
      for (int i = MIN_CARD; i <= MAX_CARD; i++) {
         enqueue(new Card(i, "Hearts"));
         enqueue(new Card(i, "Diamonds"));
         enqueue(new Card(i, "Clubs"));
         enqueue(new Card(i, "Spades"));
      }
   }

   /*
    * Function: showTopCard
    * Description: peeks at the first card in the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card showTopCard() {
      Card c = peekFront();
      return c;
   }

   /*
    * Function: showBottomCard
    * Description: peeks at the last card in the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card showBottomCard() {
      Card c = peekEnd();
      return c;
   }

   /*
    * Function: addCard
    * Description: adds a Card to the bottom of the deck
    * Parameters: c - a variable of type Card
    * Return: N/A
    */
   public void addCard(Card c) {
      enqueue(c);
   }

   /*
    * Function: removeCard
    * Description: removes a Card from the top of the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card removeCard() {
      return dequeue();
   }

   /*
    * Function: isEmpty
    * Description: checks if deck is empty
    * Parameters: N/A
    * Return: a boolean data type
    */

   public boolean isEmpty() {
      return super.isEmpty();
   }

   /*
    * Function: shuffle
    * Description: puts cards in LQueue<Card> in random order
    * Parameters: N/A
    * Return: N/A
    */
   public void shuffle() {
      // make random
      Random rand = new Random();

      // set temp cards
      int length = size();

      Card[] list = new Card[length];

      // copy queue to array list for shuffling
      for (int i = 0; i < length; i++) {
         list[i] = this.dequeue();
      }

      for (int i = length - 1; i > 0; i--)
      {
         int index = rand.nextInt(i + 1);
         // Simple swap
         Card c = list[index];
         list[index] = list[i];
         list[i] = c;
      }

      // re-queue the cards
      for (int i = 0; i < length; i++) {
         enqueue(list[i]);
      }
   }

   protected Deck(Parcel in) {

   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {

   }

   @SuppressWarnings("unused")
   public static final Parcelable.Creator<Deck> CREATOR = new Parcelable.Creator<Deck>() {
      @Override
      public Deck createFromParcel(Parcel in) {
         return new Deck(in);
      }

      @Override
      public Deck[] newArray(int size) {
         return new Deck[size];
      }
   };
}
