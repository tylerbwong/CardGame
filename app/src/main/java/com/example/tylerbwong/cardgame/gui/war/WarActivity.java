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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.version1_0.war.War;

/**
 * Created by tylerbwong on 3/24/16.
 */
public class WarActivity extends AppCompatActivity {
   private TextView titleLabel;
   private TextView compCards;
   private TextView compRank;
   private TextView statusLabel;
   private TextView humCards;
   private TextView humRank;
   private TextView compLabel;
   private TextView humLabel;
   private ImageView compSuit;
   private ImageView humSuit;
   private Button playButton;
   private Button confirmButton;

   private War war;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_war);

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      setFullscreen();

      titleLabel = (TextView) findViewById(R.id.title_label);
      compCards = (TextView) findViewById(R.id.comp_cards);
      compRank = (TextView) findViewById(R.id.comp_rank);
      statusLabel = (TextView) findViewById(R.id.status_label);
      humCards = (TextView) findViewById(R.id.hum_cards);
      humRank = (TextView) findViewById(R.id.hum_rank);
      compLabel = (TextView) findViewById(R.id.comp_text);
      humLabel = (TextView) findViewById(R.id.hum_text);
      compSuit = (ImageView) findViewById(R.id.comp_suit);
      humSuit = (ImageView) findViewById(R.id.hum_suit);
      playButton = (Button) findViewById(R.id.play_button);
      confirmButton = (Button) findViewById(R.id.confirm_button);

      titleLabel.setTypeface(gotham);
      compCards.setTypeface(gotham);
      compRank.setTypeface(gotham);
      statusLabel.setTypeface(gotham);
      humCards.setTypeface(gotham);
      humRank.setTypeface(gotham);
      compLabel.setTypeface(gotham);
      humLabel.setTypeface(gotham);
      playButton.setTypeface(gotham);
      confirmButton.setTypeface(gotham);

      war = new War();

      confirmButton.setEnabled(false);

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

   public void playAction(View v) {

   }

   public void confirmAction(View v) {

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
