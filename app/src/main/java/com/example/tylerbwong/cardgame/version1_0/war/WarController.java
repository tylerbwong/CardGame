package com.example.tylerbwong.cardgame.version1_0.war;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.war.WarActivity;

/**
 * @author Tyler Wong
 */
public class WarController {
   private War war;
   private WarActivity activity;

   public WarController(War war, WarActivity activity) {
      this.war = war;
      this.activity = activity;
      war.addObserver(activity);
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
      View alertLayout = inflater.inflate(R.layout.war_dialog_view, null);
      alert.setView(alertLayout);
      AlertDialog dialog = alert.create();
      dialog.create();

      Button noButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);
      noButton.setTypeface(gotham);
      Button yesButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
      yesButton.setTypeface(gotham);
      TextView titleLabel = (TextView) alertLayout.findViewById(R.id.title_label);

      titleLabel.setTypeface(gotham);

      dialog.show();
   }

   public void handleTurn() {
      war.doTurn();
   }

   public void handleConfirm() {
      if (!war.endGame()) {
         war.setCurrentState(War.GameState.HUM_TURN);
      }
      else {
         // end the game.
      }
   }
}
