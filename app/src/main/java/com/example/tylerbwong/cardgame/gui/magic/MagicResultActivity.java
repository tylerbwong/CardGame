package com.example.tylerbwong.cardgame.gui.magic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.R;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicResultActivity extends AppCompatActivity {
   private int suitIdent;
   private String rankIdent;
   private ImageView suitTop;
   private ImageView suitMid;
   private ImageView suitBot;
   private TextView rankTop;
   private TextView rankBot;
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

      suitTop = (ImageView) findViewById(R.id.suit_top);
      suitMid = (ImageView) findViewById(R.id.suit_mid);
      suitBot = (ImageView) findViewById(R.id.suit_bot);

      rankTop = (TextView) findViewById(R.id.rank_top);
      rankBot = (TextView) findViewById(R.id.rank_bot);
      title = (TextView) findViewById(R.id.title_label);
      backMenu = (Button) findViewById(R.id.back_to_main);
      again = (Button) findViewById(R.id.again);

      title.setTypeface(gotham);
      backMenu.setTypeface(gotham);
      again.setTypeface(gotham);
      rankTop.setTypeface(gotham);
      rankBot.setTypeface(gotham);

      Intent startIntent = getIntent();
      suitIdent = startIntent.getIntExtra("suit", 1);
      rankIdent = startIntent.getStringExtra("rank");

      showResult();
   }

   private void showResult() {
      if (suitIdent == 0) {
         suitTop.setImageResource(R.mipmap.spade);
         suitMid.setImageResource(R.mipmap.spade);
         suitBot.setImageResource(R.mipmap.spade);
      }
      else if (suitIdent == 1) {
         suitTop.setImageResource(R.mipmap.heart);
         suitMid.setImageResource(R.mipmap.heart);
         suitBot.setImageResource(R.mipmap.heart);
      }
      else if (suitIdent == 2) {
         suitTop.setImageResource(R.mipmap.club);
         suitMid.setImageResource(R.mipmap.club);
         suitBot.setImageResource(R.mipmap.club);
      }
      else {
         suitTop.setImageResource(R.mipmap.diamond);
         suitMid.setImageResource(R.mipmap.diamond);
         suitBot.setImageResource(R.mipmap.diamond);
      }

      if (rankIdent.equals(ACE) || rankIdent.equals(JACK) || rankIdent.equals(QUEEN) ||
            rankIdent.equals(KING)) {
         switch(rankIdent) {
            case JACK:
               rankTop.setText("J");
               rankBot.setText("J");
               break;
            case QUEEN:
               rankTop.setText("Q");
               rankBot.setText("Q");
               break;
            case KING:
               rankTop.setText("K");
               rankBot.setText("K");
               break;
            default:
               rankTop.setText("A");
               rankBot.setText("A");
               break;
         }
      }
      else {
         rankTop.setText(rankIdent);
         rankBot.setText(rankIdent);
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
