package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicResultActivity extends AppCompatActivity {
   private int suitIdent;
   private String rankIdent;
   private ImageView suit;
   private TextView rank;
   private TextView title;
   private Button backMenu;
   private Button again;

   final static String ACE = "Ace";
   final static String JACK = "Jack";
   final static String QUEEN = "Queen";
   final static String KING = "King";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_magic_result);
      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      suit = (ImageView) findViewById(R.id.suit);
      rank = (TextView) findViewById(R.id.rank);
      title = (TextView) findViewById(R.id.title_label);
      backMenu = (Button) findViewById(R.id.back_to_main);
      again = (Button) findViewById(R.id.again);

      title.setTypeface(gotham);
      backMenu.setTypeface(gotham);
      again.setTypeface(gotham);
      rank.setTypeface(gotham);

      Intent startIntent = getIntent();
      suitIdent = startIntent.getIntExtra("suit", 1);
      rankIdent = startIntent.getStringExtra("rank");

      showResult();
   }

   private void showResult() {
      if (suitIdent == 0) {
         suit.setImageResource(R.mipmap.spade);
      }
      else if (suitIdent == 1) {
         suit.setImageResource(R.mipmap.heart);
      }
      else if (suitIdent == 2) {
         suit.setImageResource(R.mipmap.club);
      }
      else {
         suit.setImageResource(R.mipmap.diamond);
      }

      if (rankIdent.equals(ACE) || rankIdent.equals(JACK) || rankIdent.equals(QUEEN) ||
            rankIdent.equals(KING)) {
         switch(rankIdent) {
            case JACK:
               rank.setText("J");
               break;
            case QUEEN:
               rank.setText("Q");
               break;
            case KING:
               rank.setText("K");
               break;
            default:
               rank.setText("A");
               break;
         }
      }
      else {
         rank.setText(rankIdent);
      }
   }

   public void mainMenuAction(View v) {
      Intent menuIntent = new Intent(this, MainActivity.class);
      startActivity(menuIntent);
   }

   public void againAction(View v) {
      Intent againIntent = new Intent(this, MagicActivity.class);
      startActivity(againIntent);
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
