package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.components.Deck;
import com.example.tylerbwong.cardgame.magictrick.MagicTrick;

import java.util.ArrayList;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class RealMagicActivity extends AppCompatActivity {
   private TextView titleLabel;
   private TextView subtitleLabel;
   private Button backButton;
   private Button nextButton;
   private RecyclerView listCards;
   private MagicTrick trick;
   private ArrayList<Card> trickDeck;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_real_magic);
      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel = (TextView) findViewById(R.id.subtitle_label);
      backButton = (Button) findViewById(R.id.back);
      nextButton = (Button) findViewById(R.id.next);
      listCards = (RecyclerView) findViewById(R.id.list_cards);

      Deck deck = new Deck();
      trick = new MagicTrick(deck);
      trickDeck = trick.getTrickDeck();

      titleLabel.setTypeface(gotham);
      subtitleLabel.setTypeface(gotham);
      backButton.setTypeface(gotham);
      nextButton.setTypeface(gotham);

      listCards.setHasFixedSize(true);
      LinearLayoutManager llm = new LinearLayoutManager(this);
      llm.setOrientation(LinearLayoutManager.VERTICAL);
      listCards.setLayoutManager(llm);
      CardAdapter cardAdapter = new CardAdapter(trickDeck, gotham);
      listCards.setAdapter(cardAdapter);
   }

   public void backAction(View v) {
      Intent backIntent = new Intent(this, MainActivity.class);
      startActivity(backIntent);
   }

   public void nextAction(View v) {
      Intent nextIntent = new Intent(this, MagicNumberActivity.class);
      //nextIntent.putExtra("trick", trick);
      startActivity(nextIntent);
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