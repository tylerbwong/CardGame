package com.example.tylerbwong.cardgame.components;

import java.io.Serializable;

public class Card implements Serializable {
   private int num;
   private String suit;
   final static String JACK = "Jack";
   final static String QUEEN = "Queen";
   final static String KING = "King";
   final static String ACE = "Ace";

   // default constructor (ace of spades)
   public Card() {
      this.num = 1;
      this.suit = "Spades";
   }

   // constructor
   public Card(int num, String suit) {
      this.num = num;
      this.suit = suit;
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
}

