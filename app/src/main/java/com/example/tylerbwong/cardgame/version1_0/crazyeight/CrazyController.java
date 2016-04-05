package com.example.tylerbwong.cardgame.version1_0.crazyeight;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.crazyeight.CrazyActivity;
import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.components.Hand;

/**
 * @author Tyler Wong
 */
public class CrazyController {
   private CrazyEight crazy;
   private CrazyActivity activity;
   private AlertDialog dialog;

   public static SparseArray<Integer> suitMap;

   static {
      suitMap = new SparseArray<>();

      suitMap.put(0, R.mipmap.spade);
      suitMap.put(1, R.mipmap.heart);
      suitMap.put(2, R.mipmap.club);
      suitMap.put(3, R.mipmap.diamond);
   }

   public CrazyController(CrazyEight crazy, CrazyActivity activity) {
      this.crazy = crazy;
      this.activity = activity;
      crazy.addObserver(activity);
   }

   public void displayAlertDialog(Typeface gotham) {
      AlertDialog.Builder alert = new AlertDialog.Builder(activity);

      alert.setCancelable(false);
      alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

         @Override
         public void onClick(DialogInterface dialog, int which) {
            activity.goBack();
         }
      });

      alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

         @Override
         public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
         }
      });
      LayoutInflater inflater = activity.getLayoutInflater();
      View alertLayout = inflater.inflate(R.layout.crazy_dialog_view, null);
      alert.setView(alertLayout);
      dialog = alert.create();
      dialog.create();

      Button noButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);
      noButton.setTypeface(gotham);
      Button yesButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
      yesButton.setTypeface(gotham);
      TextView titleLabel = (TextView) alertLayout.findViewById(R.id.title_label);
      titleLabel.setTypeface(gotham);

      dialog.show();
   }

   public void displayChangeSuitDialog(Typeface gotham) {
      AlertDialog.Builder alert = new AlertDialog.Builder(activity);

      alert.setCancelable(false);
      LayoutInflater inflater = activity.getLayoutInflater();
      final View alertLayout = inflater.inflate(R.layout.change_suit_dialog_view, null);
      alert.setView(alertLayout);
      dialog = alert.create();
      dialog.create();

      TextView titleLabel = (TextView) alertLayout.findViewById(R.id.title_label);
      titleLabel.setTypeface(gotham);

      ImageButton spade = (ImageButton) alertLayout.findViewById(R.id.spade_button);
      spade.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            suitClicked(alertLayout);
         }
      });

      ImageButton heart = (ImageButton) alertLayout.findViewById(R.id.heart_button);
      heart.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            suitClicked(alertLayout);
         }
      });

      ImageButton club = (ImageButton) alertLayout.findViewById(R.id.club_button);
      club.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            suitClicked(alertLayout);
         }
      });

      ImageButton diamond = (ImageButton) alertLayout.findViewById(R.id.diamond_button);
      diamond.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            suitClicked(alertLayout);
         }
      });

      dialog.show();
   }

   private void suitClicked(View v) {
      switch(v.getId()) {
         case R.id.spade_button:
            changeSuit(1);
            break;
         case R.id.heart_button:
            changeSuit(2);
            break;
         case R.id.club_button:
            changeSuit(3);
            break;
         case R.id.diamond_button:
            changeSuit(4);
            break;
         default:
            break;
      }
      dialog.cancel();
   }

   public void deal() {
      crazy.deal();
   }

   public Card userDraw() {
      return crazy.userDraw();
   }

   private void changeSuit(int option) {
      crazy.changeSuit(option);
   }

   public boolean userTurn(int choice) {
      return crazy.userTurn(choice);
   }

   public void computerTurn() {
      crazy.computerTurn();
   }

   public int gameOver() {
      return crazy.gameOver();
   }

   public Hand getUserHand() {
      return crazy.getUserHand();
   }

   public Hand getComputerHand() {
      return crazy.getComputerHand();
   }

   public Card getCardInPlay() {
      return crazy.getCardInPlay();
   }
}
