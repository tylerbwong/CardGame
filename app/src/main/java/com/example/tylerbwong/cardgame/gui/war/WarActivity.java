package com.example.tylerbwong.cardgame.gui.war;

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

/**
 * Created by tylerbwong on 3/24/16.
 */
public class WarActivity extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_war);

      setFullscreen();

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
      View alertLayout = inflater.inflate(R.layout.dialog_view, null);
      alert.setView(alertLayout);
      AlertDialog dialog = alert.create();
      dialog.create();
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
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
}
