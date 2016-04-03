package com.example.tylerbwong.cardgame.gui.magic;

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

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.adapters.PileAdapter;
import com.example.tylerbwong.cardgame.gui.magic.MagicResultActivity;
import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.magictrick.MagicTrick;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

/**
 * @author Tyler Wong
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

   final static int PILE_1 = 0;
   final static int PILE_2 = 1;
   final static int PILE_3 = 2;

   final static SparseArray<Integer> titleStages;

   static {
      titleStages = new SparseArray<>();

      titleStages.put(1, R.string.pile_2);
      titleStages.put(2, R.string.pile_3);
   }

   Typeface gotham;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pile);

      gotham = Typefaces.get(this, "font/gotham-light.ttf");

      setFullscreen();

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
      trick.dealToPiles();

      piles = trick.getPiles();

      pile1Adapter = new PileAdapter(piles[PILE_1], gotham);
      pile2Adapter = new PileAdapter(piles[PILE_2], gotham);
      pile3Adapter = new PileAdapter(piles[PILE_3], gotham);

      pile1.setAdapter(pile1Adapter);
      pile2.setAdapter(pile2Adapter);
      pile3.setAdapter(pile3Adapter);
   }

   private void updatePileAdapters(Card[][] newPiles) {
      pile1Adapter.changeCards(newPiles[PILE_1]);
      pile2Adapter.changeCards(newPiles[PILE_2]);
      pile3Adapter.changeCards(newPiles[PILE_3]);
      resetRecyclerPosition();
   }

   private void resetRecyclerPosition() {
      layout1.scrollToPosition(0);
      layout2.scrollToPosition(0);
      layout3.scrollToPosition(0);
   }

   public void pile1Clicked(View v) {
      trick.setPileChoice(PILE_1);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity();
      }
      else {
         doStage();
      }
   }

   public void pile2Clicked(View v) {
      trick.setPileChoice(PILE_2);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity();
      }
      else {
         doStage();
      }
   }

   public void pile3Clicked(View v) {
      trick.setPileChoice(PILE_3);
      if (trick.isLastStage()) {
         trick.returnPilesToDeck();
         startSolutionActivity();
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

   private void startSolutionActivity() {
      Intent solIntent = new Intent(this, MagicResultActivity.class);
      Card card = trick.getSolution();
      solIntent.putExtra("real_magic", true);
      solIntent.putExtra("suit", card.getSuitNum());
      solIntent.putExtra("rank", getStringRank(card.getNum()));
      startActivity(solIntent);
   }

   private String getStringRank(int temp) {
      String result;
      if (temp >= 11 && temp <= 14) {
         switch (temp) {
            case 11:
               result = "Jack";
               break;
            case 12:
               result = "Queen";
               break;
            case 13:
               result = "King";
               break;
            default:
               result = "Ace";
               break;
         }
      }
      else {
         result = temp + "";
      }
      return result;
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
