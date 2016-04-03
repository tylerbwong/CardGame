package com.example.tylerbwong.cardgame.version1_0.components;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Tyler Wong
 */
public class Card implements Parcelable {
   private int num;
   private String suit;
   private int suitNum = 0;

   final static String JACK = "Jack";
   final static String QUEEN = "Queen";
   final static String KING = "King";
   final static String ACE = "Ace";

   // default constructor (ace of spades)
   public Card() {
      this.num = 1;
      this.suit = "Spades";
      this.suitNum = 0;
   }

   // constructor
   public Card(int num, String suit) {
      this.num = num;
      this.suit = suit;
   }

   public int getSuitNum() {
      switch (suit) {
         case "Spades":
            return 0;
         case "Hearts":
            return 1;
         case "Diamonds":
            return 2;
         default:
            return 3;
      }
   }

   /*
    * Function: getNum
    * Description: gets the type of card
    * Parameters: N/A
    * Return: returns type of card as an int
    */
   public int getNum() {
      return num;
   }

   /*
    * Function: getSuit
    * Description: gets the suit of the card
    * Parameters: N/A
    * Return: returns type of suit as a string
    */
   public String getSuit() {
      return suit;
   }

   /*
    * Function: toString
    * Description: makes the card into a string
    * Parameters: N/A
    * Return: a String data type
    */
   @Override
   public String toString() {
      String face = "Jack";
      switch (num) {
         case 11:
            face = JACK;
            break;
         case 12:
            face = QUEEN;
            break;
         case 13:
            face = KING;
            break;
         case 14:
            face = ACE;
            break;
         default:
            face = num + "";
            break;
      }
      return face + " of " + suit;
   }

   protected Card(Parcel in) {
      num = in.readInt();
      suit = in.readString();
      suitNum = in.readInt();
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(num);
      dest.writeString(suit);
      dest.writeInt(suitNum);
   }

   @SuppressWarnings("unused")
   public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
      @Override
      public Card createFromParcel(Parcel in) {
         return new Card(in);
      }

      @Override
      public Card[] newArray(int size) {
         return new Card[size];
      }
   };
}

