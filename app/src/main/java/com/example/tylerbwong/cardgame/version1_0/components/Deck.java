package com.example.tylerbwong.cardgame.version1_0.components;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Deck extends LQueue<Card> implements Parcelable{

   // constructs deck
   public Deck() {
      // construct LQueue
      super();

      // add cards to deck
      for (int i = 2; i < 15; i++) {
         this.enqueue(new Card(i, "Hearts"));
         this.enqueue(new Card(i, "Diamonds"));
         this.enqueue(new Card(i, "Clubs"));
         this.enqueue(new Card(i, "Spades"));
      }
   }

   /*
    * Function: showTopCard
    * Description: peeks at the first card in the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card showTopCard() {
      Card c = this.peekFront();
      return c;
   }

   /*
    * Function: showBottomCard
    * Description: peeks at the last card in the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card showBottomCard() {
      Card c = this.peekEnd();
      return c;
   }

   /*
    * Function: addCard
    * Description: adds a Card to the bottom of the deck
    * Parameters: c - a variable of type Card
    * Return: N/A
    */
   public void addCard(Card c) {
      this.enqueue(c);
   }

   /*
    * Function: removeCard
    * Description: removes a Card from the top of the deck
    * Parameters: N/A
    * Return: a Card type
    */
   public Card removeCard() {
      return this.dequeue();
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
      int length = this.size();
      Card temp1, temp2;

      // make array for copying
      int randint;
      Card[] list = new Card[length];

      // copy queue to array list for shuffling
      for (int i = 0; i < length; i++) {
         list[i] = this.dequeue();
      }

      // re-copy cards in random order
      for (int i = length - 1; i >= 0; i--) {
         //Creates a random int bounded by 0 to length
         randint = rand.nextInt(length);

         // random swap at random location
         temp1 = list[i];
         temp2 = list[randint];

         // re-add to array list
         list[i] = temp2;

         // check for duplicates
         if (! temp1.equals(temp2)) {
            list[randint] = temp1;
         }
      }

      // re-queue the cards
      for (int i = 0; i < length; i++) {
         this.enqueue(list[i]);
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
