package com.example.tylerbwong.cardgame.magictrick;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicDriver {
   public static void printArray(ArrayList<Card> array) {
      for (Card card : array) {
         System.out.println(card.toString());
      }
   }

   public static void printPiles(ArrayList<Card>[] piles) {
      for (int index = 0; index < piles.length; index++) {
         System.out.println("Choice " + index);
         printArray(piles[index]);
         System.out.println("--------------------");
      }
   }

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Would you like to see a magic trick? (1 - yes, 2 - no)");
      int yesNo = scan.nextInt();

      if (yesNo == 1) {
         // make deck and trick objects
         Deck deck = new Deck();
         MagicTrick trick = new MagicTrick(deck);

         // get user number selection
         System.out.println("Enter a number between 0 and 27");
         int cardChoice = scan.nextInt();
         trick.setChoice(cardChoice);

         // print out cards
         System.out.println("Remember one of these cards and don't forget!");
         ArrayList<Card> trickDeck = trick.getTrickDeck();
         printArray(trickDeck);
         System.out.println("Press 1 when ready!");
         int answer = scan.nextInt();

         // answer was 1
         if (answer == 1) {
            // while still dealing to piles
            while (trick.getStage() < 3) {
               trick.dealToPiles();
               System.out.println("Which pile is your card in? (0 - 2)");
               printPiles(trick.getPiles());

               // verify choice
               int decision = scan.nextInt();
               if (trick.verifyChoice(decision)) {
                  trick.returnPilesToDeck();
               }
            }
         }
         System.out.println("Was your card the " + trick.getSolution().toString());
      }
      else if (yesNo == 2) {
         System.exit(1);
      }
      scan.close();
   }
}
