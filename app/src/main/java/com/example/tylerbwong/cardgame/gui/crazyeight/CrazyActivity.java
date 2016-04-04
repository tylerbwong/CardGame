package com.example.tylerbwong.cardgame.gui.crazyeight;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyController;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyEight;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tyler Wong
 */
public class CrazyActivity extends AppCompatActivity implements Observer {

   private CrazyController controller;

   Typeface gotham = Typefaces.get(this, "font/gotham-light.ttf");

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_black);
      setFullscreen();

      controller = new CrazyController(new CrazyEight(), this);

      displayAlertDialog();
   }

   public void displayAlertDialog() {
      AlertDialog.Builder alert = new AlertDialog.Builder(this);

      alert.setCancelable(false);
      alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

         @Override
         public void onClick(DialogInterface dialog, int which) {
            goBack();
         }
      });

      alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

         @Override
         public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
         }
      });
      LayoutInflater inflater = getLayoutInflater();
      View alertLayout = inflater.inflate(R.layout.crazy_dialog_view, null);
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

   public void goBack() {
      Intent mainIntent = new Intent(this, MainActivity.class);
      startActivity(mainIntent);
   }

   @Override
   protected void onResume() {
      super.onResume();
      setFullscreen();
   }

   private void setFullscreen() {
      View decorView = getWindow().getDecorView();
      decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                  | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                  | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                  | View.SYSTEM_UI_FLAG_FULLSCREEN
                  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
   }

   @Override
   public void update(Observable o, Object arg) {

   }
}
