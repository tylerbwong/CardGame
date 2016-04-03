package com.example.tylerbwong.cardgame.version1_0.console;

import com.example.tylerbwong.cardgame.version1_0.blackjack.BlackJack;
import com.example.tylerbwong.cardgame.version1_0.components.Card;
import java.util.*;
import com.example.tylerbwong.cardgame.version1_0.components.LQueue.MyException;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyEight;
import com.example.tylerbwong.cardgame.version1_0.war.War;

/**
 * @author Tyler Wong
 */
public class MainDriver {
   /*
    * Function: main
    * Description: runs game program
    * Parameters: args - an array with components of data type string
    * Return: N/A
    */
   public static void main(String[] args) {
      // open scanner
      Scanner s = new Scanner(System.in);
      boolean bigBool = true;
      while (bigBool) {
         // give user options
         prompt();

         // save user input
         int c = s.nextInt();

         // initialize user input
         switch (c) {
            case 1:
               // prompt for game
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("Would you like to play Crazy 8's? y/n");
               System.out.println("___________________________________");
               System.out.println("");
               String w = s.next();

               // play game
               switch (w) {
                  case "y":
                     // prompt user
                     System.out.println("___________________________________");
                     System.out.println("");
                     System.out.println("Let's play Crazy 8's!");
                     System.out.println("___________________________________");
                     System.out.println("");

                     // make game
                     CrazyEight crazy8 = new CrazyEight();
                     crazy8.deal();

                     // play as long as game is not over
                     while (crazy8.gameOver() > 1) {
                        boolean userTurnDone = false;
                        System.out.println("The card in play is: " + crazy8.getCardInPlay());
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("Your hand: ");

                        // print hand
                        crazy8.printUserHand();
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("Play a card. Type the index of the card or 0 to draw a card.");

                        // check if user is done
                        while (! userTurnDone) {
                           if (s.hasNextInt()) {
                              // draw card?
                              int choice = s.nextInt();
                              if (choice == 0) {
                                 try {
                                    System.out.println("___________________________________");
                                    System.out.println("");
                                    System.out.println("You drew " + crazy8.userDraw().toString());
                                    System.out.println("The card in play is: " + crazy8.getCardInPlay());
                                    System.out.println("___________________________________");
                                    System.out.println("");
                                    crazy8.printUserHand();
                                 }
                                 // end of deck case
                                 catch (NullPointerException e) {
                                    userTurnDone = true;
                                 }
                                 // 2nd end of deck case
                                 catch (MyException e) {
                                    userTurnDone = true;
                                 }
                                 if (! crazy8.getDeck().isEmpty()) {
                                    System.out.println("Play a card. Type the index of the card or 0 to draw a card.");
                                 }
                              } else {
                                 try {
                                    // pick card try for invalid choice
                                    if (crazy8.userTurn(choice) && ! crazy8.getDeck().isEmpty()) {
                                       // user turn
                                       userTurnDone = true;
                                       System.out.println("___________________________________");
                                       System.out.println("");
                                       System.out.println("Great choice!");
                                       System.out.println("You played " + crazy8.getCardInPlay().toString());
                                       System.out.println("___________________________________");
                                       System.out.println("");

                                       // if an 8 is chosen
                                       if (crazy8.getCardInPlay().getNum() == 8) {
                                          System.out.println("Choose a suit.");
                                          printSuitSelect();
                                          int v = s.nextInt();
                                          crazy8.changeSuit(v);
                                          System.out.println("The card in play is now " + crazy8.getCardInPlay());
                                       }
                                    } else {
                                       System.out.println("___________________________________");
                                       System.out.println("");
                                       System.out.println("Invalid choice. Try again!");
                                       System.out.println("___________________________________");
                                       System.out.println("");
                                    }
                                 }
                                 // check for invalid input
                                 catch (IndexOutOfBoundsException e) {
                                    System.out.println("___________________________________");
                                    System.out.println("");
                                    System.out.println("Invalid choice. Try again!");
                                    System.out.println("___________________________________");
                                    System.out.println("");
                                    continue;
                                 }
                              }
                           }
                        }
                        // computer turn
                        if (crazy8.gameOver() > 1) {
                           crazy8.computerTurn();
                        }
                     }
                     break;

                  case "n":
                     // end game
                     break;
               }
               break;

            case 2:
               // prompt for game
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("Would you like to play War? y/n");
               System.out.println("___________________________________");
               System.out.println("");
               String e = s.next();

               // initialize game
               switch (e) {
                  case "y":
                     // make a new game of war
                     War game = new War();

                     // give user directions
                     warOps();

                     // game
                     while (! game.endGame()) {
                        int play = s.nextInt();

                        // play game
                        if (play == 1) {
                           // print out game info
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You played a card.");
                           game.compareCards();
                           game.keepScore();
                        }

                        // quit game
                        if (play == 0) {
                           break;
                        }
                     }

                  case "n":
                     // exit game
                     break;
               }
               break;

            case 3:
               // prompt for game
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("Would you like to play Black Jack? y/n");
               System.out.println("___________________________________");
               System.out.println("");
               String g = s.next();

               switch (g) {
                  case "y":
                     // prompt user
                     blackOps();

                     // make game
                     BlackJack black = new BlackJack();
                     black.dealBlack();
                     int userHandSum = black.addBetterHand(black.getUserHand());
                     // check if game is over
                     boolean done = false;
                     int userResult = black.bust(userHandSum);
                     while (userResult < 0 && ! done) {
                        // print user hand
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("Your cards are: ");
                        black.printUserHand();
                        System.out.println("___________________________________");
                        System.out.println("");

                        // hit?
                        System.out.println("Would you like a card? 1 = yes | 0 = no");
                        int o = s.nextInt();
                        if (! black.hit(o)) {
                           done = true;
                        }

                        // add hand
                        userHandSum = black.addBetterHand(black.getUserHand());
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("You are currently at: " + userHandSum);
                        System.out.println("___________________________________");
                        System.out.println("");
                        userResult = black.bust(userHandSum);
                     }

                     int computerResult = black.computerTurn();
                     int computerHandSum = black.addBetterHand(black.getComputerHand());
                     if (userResult == 1) {
                        if (computerResult == 1) {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You both hit blackjack. It's a tie.");
                           System.out.println("___________________________________");
                           System.out.println("");
                        } else {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You won!");
                           System.out.println("___________________________________");
                           System.out.println("");
                        }
                     } else if (userResult == 0) {
                        if (computerResult == 0) {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You both busted. It's a tie.");
                           System.out.println("___________________________________");
                           System.out.println("");
                        } else {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You lose!");
                           System.out.println("___________________________________");
                           System.out.println("");
                        }
                     } else if (computerResult == 0) {
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("You win!");
                        System.out.println("___________________________________");
                        System.out.println("");
                     } else if (computerResult == 1) {
                        System.out.println("___________________________________");
                        System.out.println("");
                        System.out.println("You lose!");
                        System.out.println("___________________________________");
                        System.out.println("");
                     } else {
                        if (computerHandSum > userHandSum) {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You lose!");
                           System.out.println("___________________________________");
                           System.out.println("");
                        } else if (userHandSum > computerHandSum) {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("You win!");
                           System.out.println("___________________________________");
                           System.out.println("");
                        } else {
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.out.println("It's a tie");
                           System.out.println("___________________________________");
                           System.out.println("");
                        }
                     }
                     break;

                  case "n":
                     // exit game
                     break;
               }
               break;

            case 4:
               // prompt for magic trick
               System.out.println("___________________________________");
               System.out.println("");
               System.out.println("Would you like to see a magic trick? y/n");
               System.out.println("(This will end the program)");
               System.out.println("___________________________________");
               System.out.println("");
               String t = s.next();

               switch (t) {
                  case "y":
                     // prompt user
                     System.out.println("Please enter a card suit.");
                     printSuitSelect();

                     // choose suit
                     int suit = s.nextInt();
                     String suitChoice = chooseSuit(suit);

                     // choose card
                     System.out.println("Please enter a value. (11-14 = Jack-Ace)");
                     int h = s.nextInt();

                     // make card
                     final Card play = new Card(h, suitChoice);

                     // timer class for 5 second delay
                     class Run extends TimerTask {
                        public void run() {
                           System.out.println("Was your card the " + play.toString() + "?");
                           System.out.println("___________________________________");
                           System.out.println("");
                           System.exit(1);
                        }
                     }

                     // make timer
                     Timer timer = new Timer();
                     Run ab = new Run();
                     timer.schedule(ab, 5000);

                     // print results
                     System.out.println("___________________________________");
                     System.out.println("");
                     System.out.println("Hmmm, that's a hard one...");
                     System.out.println("Let me think...");
                     bigBool = false;
                     break;

                  case "n":
                     // exit game
                     break;
               }
               break;

            case 5:
               // exit application
               System.exit(1);

            default:
               // invalid input case at main menu
               System.out.println("Please choose 1 - 5.");
         }
      }
      // scanner close
      s.close();
   }

   /*
    * Function: prompt
    * Description: prompt user options
    * Parameters: N/A
    * Return: N/A
    */
   private static void prompt() {
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("Main Menu: ");
      System.out.println("Which game would you like to play?");
      System.out.println("1 - Crazy8");
      System.out.println("2 - War");
      System.out.println("3 - Black Jack");
      System.out.println("4 - Magic Trick");
      System.out.println("5 - Exit");
      System.out.println("___________________________________");
      System.out.println("");
   }

   /*
    * Function: warOps
    * Description: give War options
    * Parameter: N/A
    * Return: N/A
    */
   private static void warOps() {
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("\\         / /\\   |\\  |");
      System.out.println(" \\  / \\  / /__\\  |/  |");
      System.out.println("  \\/   \\/ /    \\ |\\  o");
      System.out.println("The game has begun! Press 1 to play a card or 0 to exit.");
      System.out.println("___________________________________");
      System.out.println("");
   }

   /*
    * Function: blackOps
    * Description: give Black Jack options
    * Parameter: N/A
    * Return: N/A
    */
   private static void blackOps() {
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("Let's play Blackjack!");
      System.out.println("Disclaimer: In this game, you can't split doubles.");
      System.out.println("___________________________________");
      System.out.println("");
   }

   /*
    * Function: printSuitSelect
    * Description: prints suit selections
    * Parameters: N/A
    * Return: N/A
    */
   private static void printSuitSelect() {
      System.out.println("___________________________________");
      System.out.println("");
      System.out.println("1 - Spades");
      System.out.println("2 - Hearts");
      System.out.println("3 - Diamonds");
      System.out.println("4 - Clubs");
      System.out.println("___________________________________");
      System.out.println("");
   }

   /*
    * Function: chooseSuit
    * Description: chooses a suit
    * Parameters: s - a variable of data type string
    * Return: a string data type
    */
   private static String chooseSuit(int s) {
      switch (s) {
         case 1:
            return "Spades";
         case 2:
            return "Hearts";
         case 3:
            return "Diamonds";
         case 4:
            return "Clubs";
      }
      System.out.println("Since you can't choose the right suit, the suit is Spades.");
      return "Spades";
   }
}