package com.example.tylerbwong.cardgame;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DecorContentParent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Process;

public class MainActivity extends AppCompatActivity {
   private TextView titleLabel;
   private Button crazyEights;
   private Button war;
   private Button blackJack;
   private Button magicTrick;
   private Button exit;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      Typeface gotham = Typeface.createFromAsset(getAssets(),"font/gotham-light.ttf");

      // initialize TextView
      titleLabel = (TextView) findViewById(R.id.title_label);

      // initialize Buttons
      crazyEights = (Button) findViewById(R.id.crazy_eight);
      war = (Button) findViewById(R.id.war);
      blackJack = (Button) findViewById(R.id.black_jack);
      magicTrick = (Button) findViewById(R.id.magic_trick);
      exit = (Button) findViewById(R.id.exit);

      // set title font to gotham light
      titleLabel.setTypeface(gotham);

      // set buttons font to gotham light
      crazyEights.setTypeface(gotham);
      war.setTypeface(gotham);
      blackJack.setTypeface(gotham);
      magicTrick.setTypeface(gotham);
      exit.setTypeface(gotham);
   }

   @Override
   public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      View decorView = getWindow().getDecorView();
      if (hasFocus) {
         decorView.setSystemUiVisibility(
               View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                     | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                     | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                     | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                     | View.SYSTEM_UI_FLAG_FULLSCREEN
                     | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
   }

   /**
    * This method will start a game of
    * Crazy Eights.
    * @param v
    */
   public void crazyAction(View v) {

   }

   /**
    * This method will start a game of
    * War.
    * @param v
    */
   public void warAction(View v) {

   }

   /**
    * This method will start a game of
    * Black Jack.
    * @param v
    */
   public void blackAction(View v) {

   }

   /**
    * This method will start a Magic
    * Trick.
    * @param v
    */
   public void magicAction(View v) {

   }

   /**
    * This method will exit the
    * application.
    * @param v
    */
   public void exitAction(View v) {
      Process.killProcess(Process.myPid());
      System.exit(1);
   }
}
