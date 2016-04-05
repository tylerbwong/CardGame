package com.example.tylerbwong.cardgame.version1_0.components;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Tyler Wong
 */
public class Hand implements Parcelable {
   private ArrayList<Card> hand;

   // default hand constructor
   public Hand() {
      hand = new ArrayList<>();
   }

   // hand constructor
   public Hand(Deck d, int num) {
      hand = new ArrayList<>();

      for (int i = 0; i < num; i++) {
         hand.add(i, d.removeCard());
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
      hand.add(ret);
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
      Card ret = hand.remove(c);
      hand.trimToSize();
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
      return hand.size();
   }

   /*
    * Function: addCard
    * Descrption: adds a card to players hand
    * Parameters: c - a variable of data type card
    * Return: N/A
    */
   public void addCard(Card c) {
      hand.add(c);
   }

   protected Hand(Parcel in) {
      if (in.readByte() == 0x01) {
         hand = new ArrayList<Card>();
         in.readList(hand, Card.class.getClassLoader());
      } else {
         hand = null;
      }
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      if (hand == null) {
         dest.writeByte((byte) (0x00));
      } else {
         dest.writeByte((byte) (0x01));
         dest.writeList(hand);
      }
   }

   @SuppressWarnings("unused")
   public static final Parcelable.Creator<Hand> CREATOR = new Parcelable.Creator<Hand>() {
      @Override
      public Hand createFromParcel(Parcel in) {
         return new Hand(in);
      }

      @Override
      public Hand[] newArray(int size) {
         return new Hand[size];
      }
   };
}

