package com.example.tylerbwong.cardgame.gui.mainmenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.magic.MagicActivity;
import com.example.tylerbwong.cardgame.gui.realmagic.RealMagicActivity;
import com.example.tylerbwong.cardgame.gui.war.WarActivity;

public class MainActivity extends AppCompatActivity {
   private TextView titleLabel;
   private Button crazyEights;
   private Button war;
   private Button blackJack;
   private Button magicTrick;
   private Button realMagicTrick;
   private Button exit;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      boolean logoTransition = getIntent().getBooleanExtra("logo_transition", false);
      if (logoTransition) {
         overridePendingTransition(R.anim.slow_transition, R.anim.slow_transition);
      }

      setContentView(R.layout.activity_main);
      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      // initialize TextView
      titleLabel = (TextView) findViewById(R.id.title_label);

      // initialize Buttons
      crazyEights = (Button) findViewById(R.id.crazy_eight);
      war = (Button) findViewById(R.id.war);
      blackJack = (Button) findViewById(R.id.black_jack);
      magicTrick = (Button) findViewById(R.id.magic_trick);
      realMagicTrick = (Button) findViewById(R.id.real_magic_trick);
      exit = (Button) findViewById(R.id.exit);

      // set title font to gotham light
      titleLabel.setTypeface(gotham);

      // set buttons font to gotham light
      crazyEights.setTypeface(gotham);
      war.setTypeface(gotham);
      blackJack.setTypeface(gotham);
      magicTrick.setTypeface(gotham);
      realMagicTrick.setTypeface(gotham);
      exit.setTypeface(gotham);
   }

   /**
    * This method will start a game of
    * Crazy Eights.
    *
    * @param v
    */
   public void crazyAction(View v) {
   }

   /**
    * This method will start a game of
    * War.
    *
    * @param v
    */
   public void warAction(View v) {
      Intent warIntent = new Intent(this, WarActivity.class);
      startActivity(warIntent);
   }

   /**
    * This method will start a game of
    * Black Jack.
    *
    * @param v
    */
   public void blackAction(View v) {
   }

   /**
    * This method will start a Magic
    * Trick.
    *
    * @param v
    */
   public void magicAction(View v) {
      Intent magicIntent = new Intent(this, MagicActivity.class);
      startActivity(magicIntent);
   }

   /**
    * This method will start a REAL Magic
    * Trick.
    *
    * @param v
    */
   public void realMagicAction(View v) {
      Intent realMagicIntent = new Intent(this, RealMagicActivity.class);
      startActivity(realMagicIntent);
   }

   /**
    * This method will exit the
    * application.
    *
    * @param v
    */
   public void exitAction(View v) {
      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.addCategory(Intent.CATEGORY_HOME);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
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
