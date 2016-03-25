package com.example.tylerbwong.cardgame.gui.realmagic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.gui.adapters.PileAdapter;
import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.magictrick.MagicTrick;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class RealMagicPileActivity extends AppCompatActivity {
   private MagicTrick trick;
   private TextView titleLabel;
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

   private PileAdapter pile1Adapter;
   private PileAdapter pile2Adapter;
   private PileAdapter pile3Adapter;

   private LinearLayoutManager layout1;
   private LinearLayoutManager layout2;
   private LinearLayoutManager layout3;

   final static SparseArray<Integer> titleStages;

   static {
      titleStages = new SparseArray<>();

      titleStages.put(1, R.string.pile_2);
      titleStages.put(2, R.string.pile_3);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pile);

      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
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
      pile1Label.setTypeface(gotham);
      pile2Label.setTypeface(gotham);
      pile3Label.setTypeface(gotham);
      pile1Button.setTypeface(gotham);
      pile2Button.setTypeface(gotham);
      pile3Button.setTypeface(gotham);

      pile1.setHasFixedSize(true);
      pile2.setHasFixedSize(true);
      pile3.setHasFixedSize(true);

      layout1 = new LinearLayoutManager(this);
      layout1.setOrientation(LinearLayoutManager.HORIZONTAL);

      layout2 = new LinearLayoutManager(this);
      layout2.setOrientation(LinearLayoutManager.HORIZONTAL);

      layout3 = new LinearLayoutManager(this);
      layout3.setOrientation(LinearLayoutManager.HORIZONTAL);

      pile1.setLayoutManager(layout1);
      pile2.setLayoutManager(layout2);
      pile3.setLayoutManager(layout3);

      Intent startIntent = getIntent();
      Bundle trickBundle = startIntent.getExtras();
      trick = trickBundle.getParcelable("trick");

      makePileView();
   }

   private void makePileView() {
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      trick.dealToPiles();

      piles = trick.getPiles();

      pile1Adapter = new PileAdapter(piles[0], gotham);
      pile2Adapter = new PileAdapter(piles[1], gotham);
      pile3Adapter = new PileAdapter(piles[2], gotham);

      pile1.setAdapter(pile1Adapter);
      pile2.setAdapter(pile2Adapter);
      pile3.setAdapter(pile3Adapter);
   }

   private void updatePileAdapters(Card[][] newPiles) {
      pile1Adapter.changeCards(newPiles[0]);
      System.out.println("Pile 1: ");
      for (int i = 0; i < newPiles[0].length; i++) {
         System.out.println(newPiles[0][i].getNum() + newPiles[0][i].getSuit());
      }
      pile2Adapter.changeCards(newPiles[1]);
      System.out.println("Pile 2: ");
      for (int i = 0; i < newPiles[1].length; i++) {
         System.out.println(newPiles[1][i].getNum() + newPiles[1][i].getSuit());
      }
      pile3Adapter.changeCards(newPiles[2]);
      System.out.println("Pile 2: ");
      for (int i = 0; i < newPiles[2].length; i++) {
         System.out.println(newPiles[2][i].getNum() + newPiles[2][i].getSuit());
      }
      resetRecyclerPosition();
   }

   private void resetRecyclerPosition() {
      layout1.scrollToPosition(0);
      layout2.scrollToPosition(0);
      layout3.scrollToPosition(0);
   }

   public void pile1Clicked(View v) {
      trick.setPileChoice(0);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity(v);
      }
      else {
         doStage();
      }
   }

   public void pile2Clicked(View v) {
      trick.setPileChoice(1);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity(v);
      }
      else {
         doStage();
      }
   }

   public void pile3Clicked(View v) {
      trick.setPileChoice(2);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity(v);
      }
      else {
         doStage();
      }
   }

   private void doStage() {
      trick.returnPilesToDeck();
      trick.dealToPiles();
      updatePileAdapters(trick.getPiles());
      titleLabel.setText(getResources().getText(titleStages.get(trick.getStage())));
   }

   private void startSolutionActivity(View v) {
      Intent solIntent = new Intent(this, RealMagicResultActivity.class);
      solIntent.putExtra("solution", trick.getSolution());
      startActivity(solIntent);
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