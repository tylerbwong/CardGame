package com.example.tylerbwong.cardgame.magictrick;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicTrick implements Parcelable{
   private Deck deck;
   private Card solution;
   private int choice;
   private Card card;
   private Card[] trickDeck;
   private Card[] pile1;
   private Card[] pile2;
   private Card[] pile3;
   private Card[][] piles;
   private int[] order;
   private int stage = 0;
   private int currentChoice = - 1;

   final static int NUM_CARDS = 27;
   final static int STAGES = 3;
   final static int NUM_PILES = 3;
   final static int PILE_SIZE = 9;

   public MagicTrick(Deck deck) {
      this.deck = deck;
      this.deck.shuffle();
      pile1 = new Card[PILE_SIZE];
      pile2 = new Card[PILE_SIZE];
      pile3 = new Card[PILE_SIZE];
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
      trickDeck = new Card[NUM_CARDS];
      for (int index = 0; index < NUM_CARDS; index++) {
         trickDeck[index] = deck.removeCard();
      }
   }

   private void repopulateTrickDeck() {
      int index = 0;
      for (Card[] pile : piles) {
         for (int index1 = 0; index1 < PILE_SIZE; index1++, index++) {
            trickDeck[index] = pile[index1];
         }
      }
   }

   public void dealToPiles() {
      int index3 = 0;
      pile1 = new Card[PILE_SIZE];
      pile2 = new Card[PILE_SIZE];
      pile3 = new Card[PILE_SIZE];
      piles = new Card[][] {pile1, pile2, pile3};

      for (int index1 = 0; index1 < PILE_SIZE; index1++) {
         for (int index2 = 0; index2 < NUM_PILES; index2++, index3++) {
            piles[index2][index1] = trickDeck[index3];
         }
      }
   }

   public void setChoice(int choice) {
      this.choice = choice;
      convertToBaseThree();
   }

   public boolean isLastStage() {
      return getStage() == 2;
   }

   public void setCard(int choice) {
      card = trickDeck[choice];
   }

   public Card[] getTrickDeck() {
      return trickDeck;
   }

   public Card[][] getPiles() {
      return piles;
   }

   public int getStage() {
      return stage;
   }

   public void returnPilesToDeck() {
      trickDeck = new Card[NUM_CARDS];

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

   public void setPileChoice(int userChoice) {
      currentChoice = userChoice;
   }

   private void swapPiles(int from, int to) {
      Card[] temp;

      temp = piles[from];
      piles[from] = piles[to];
      piles[to] = temp;
   }

   public Card getSolution() {
      return trickDeck[choice];
   }

   protected MagicTrick(Parcel in) {
      trickDeck = in.createTypedArray(Card.CREATOR);
      order = in.createIntArray();
      solution = in.readParcelable(Card.class.getClassLoader());
      choice = in.readInt();
      card = in.readParcelable(Card.class.getClassLoader());
      stage = in.readInt();
      currentChoice = in.readInt();
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeTypedArray(trickDeck, flags);
      dest.writeIntArray(order);
      dest.writeParcelable(solution, flags);
      dest.writeInt(choice);
      dest.writeParcelable(card, flags);
      dest.writeInt(stage);
      dest.writeInt(currentChoice);
   }

   @SuppressWarnings("unused")
   public static final Parcelable.Creator<MagicTrick> CREATOR = new Parcelable.Creator<MagicTrick>() {
      @Override
      public MagicTrick createFromParcel(Parcel in) {
         return new MagicTrick(in);
      }

      @Override
      public MagicTrick[] newArray(int size) {
         return new MagicTrick[size];
      }
   };
}
