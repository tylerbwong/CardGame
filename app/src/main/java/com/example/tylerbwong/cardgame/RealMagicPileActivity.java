package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.components.Card;
import com.example.tylerbwong.cardgame.magictrick.MagicTrick;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class RealMagicPileActivity extends AppCompatActivity {
   private MagicTrick trick;
   private TextView titleLabel;
   private TextView subtitleLabel;
   private TextView pile1Label;
   private TextView pile2Label;
   private TextView pile3Label;
   private RecyclerView pile1;
   private RecyclerView pile2;
   private RecyclerView pile3;
   private Button pile1Button;
   private Button pile2Button;
   private Button pile3Button;
   private Card[][] piles;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pile);

      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel = (TextView) findViewById(R.id.subtitle_label);
      pile1Label = (TextView) findViewById(R.id.pile1_label);
      pile2Label = (TextView) findViewById(R.id.pile2_label);
      pile3Label = (TextView) findViewById(R.id.pile3_label);
      pile1 = (RecyclerView) findViewById(R.id.pile1);
      pile2 = (RecyclerView) findViewById(R.id.pile2);
      pile3 = (RecyclerView) findViewById(R.id.pile3);
      pile1Button = (Button) findViewById(R.id.pile1_button);
      pile2Button = (Button) findViewById(R.id.pile2_button);
      pile3Button = (Button) findViewById(R.id.pile3_button);

      titleLabel.setTypeface(gotham);
      subtitleLabel.setTypeface(gotham);
      pile1Label.setTypeface(gotham);
      pile2Label.setTypeface(gotham);
      pile3Label.setTypeface(gotham);
      pile1Button.setTypeface(gotham);
      pile2Button.setTypeface(gotham);
      pile3Button.setTypeface(gotham);

      pile1.setHasFixedSize(true);
      pile2.setHasFixedSize(true);
      pile3.setHasFixedSize(true);

      LinearLayoutManager layout1 = new LinearLayoutManager(this);
      layout1.setOrientation(LinearLayoutManager.HORIZONTAL);

      LinearLayoutManager layout2 = new LinearLayoutManager(this);
      layout2.setOrientation(LinearLayoutManager.HORIZONTAL);

      LinearLayoutManager layout3 = new LinearLayoutManager(this);
      layout3.setOrientation(LinearLayoutManager.HORIZONTAL);

      pile1.setLayoutManager(layout1);
      pile2.setLayoutManager(layout2);
      pile3.setLayoutManager(layout3);

      Intent startIntent = getIntent();
      Bundle trickBundle = startIntent.getExtras();
      trick = trickBundle.getParcelable("trick");

      updatePileView();
   }

   private void updatePileView() {
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      trick.dealToPiles();

      piles = trick.getPiles();

      PileAdapter pile1Adapter = new PileAdapter(piles[0], gotham);
      PileAdapter pile2Adapter = new PileAdapter(piles[1], gotham);
      PileAdapter pile3Adapter = new PileAdapter(piles[2], gotham);

      pile1.setAdapter(pile1Adapter);
      pile2.setAdapter(pile2Adapter);
      pile3.setAdapter(pile3Adapter);
   }

   public void pile1Clicked(View v) {
      if (trick.verifyChoice(0)) {
         trick.returnPilesToDeck();
      }
      if (isLastStage()) {
         startSolutionActivity();
      }
      updatePileView();
   }

   public void pile2Clicked(View v) {
      if (trick.verifyChoice(1)) {
         trick.returnPilesToDeck();
      }
      if (isLastStage()) {
         startSolutionActivity();
      }
      updatePileView();
   }

   public void pile3Clicked(View v) {
      if (trick.verifyChoice(2)) {
         trick.returnPilesToDeck();
      }
      if (isLastStage()) {
         startSolutionActivity();
      }
      updatePileView();
   }

   private boolean isLastStage() {
      return trick.getStage() == 4;
   }

   private void startSolutionActivity() {
      //Intent solIntent = new Intent(this, MagicSolution.class);
      //solIntent.putExtra("solution", trick.getSolution());
      //startActivity(solIntent);
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
