package com.example.tylerbwong.cardgame.gui.crazyeight;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.adapters.CrazyCardAdapter;
import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.version1_0.components.Card;
import com.example.tylerbwong.cardgame.version1_0.components.Hand;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyController;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyEight;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tyler Wong
 */
public class CrazyActivity extends AppCompatActivity implements Observer {

   private TextView titleLabel;
   private RecyclerView playerHand;
   private TextView topRank;
   private TextView botRank;
   private ImageView topSuit;
   private ImageView midSuit;
   private ImageView botSuit;

   private LinearLayoutManager handLayout;
   private CrazyCardAdapter handAdapter;

   private CrazyController controller;

   Typeface gotham = Typefaces.get(this, "font/gotham-light.ttf");

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_crazy);
      setFullscreen();

      titleLabel = (TextView) findViewById(R.id.title_label);
      playerHand = (RecyclerView) findViewById(R.id.player_hand);
      topRank = (TextView) findViewById(R.id.rank_top);
      botRank = (TextView) findViewById(R.id.rank_bot);
      topSuit = (ImageView) findViewById(R.id.suit_top);
      midSuit = (ImageView) findViewById(R.id.suit_mid);
      botSuit = (ImageView) findViewById(R.id.suit_bot);

      titleLabel.setTypeface(gotham);

      controller = new CrazyController(new CrazyEight(), this);
      controller.deal();

      controller.displayAlertDialog(gotham);

      playerHand.setHasFixedSize(true);
      handLayout = new LinearLayoutManager(this);
      handLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
      playerHand.setLayoutManager(handLayout);
      Card[] handArray = new Card[controller.getUserHand().getSize()];
      handAdapter = new CrazyCardAdapter(controller.getUserHand().toArray(handArray), gotham);
      playerHand.setAdapter(handAdapter);
   }

   public void playCardAction(View v) {
      int position = playerHand.getChildAdapterPosition(v);
      controller.userTurn(position);
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

   @Override
   public void update(Observable o, Object arg) {
      CrazyEight crazy = (CrazyEight) o;

      // update card in play
      Card cardInPlay = crazy.getCardInPlay();

      topRank.setText(cardInPlay.convertNum());
      botRank.setText(cardInPlay.convertNum());
      topSuit.setImageResource(CrazyCardAdapter.suitMap.get(cardInPlay.getSuitNum()));
      midSuit.setImageResource(CrazyCardAdapter.suitMap.get(cardInPlay.getSuitNum()));
      botSuit.setImageResource(CrazyCardAdapter.suitMap.get(cardInPlay.getSuitNum()));
      topRank.setTypeface(gotham);
      botRank.setTypeface(gotham);

      // update player hand
      Card[] handArray = new Card[crazy.getUserHand().getSize()];
      handAdapter.changeCards(controller.getUserHand().toArray(handArray));
      handLayout.scrollToPosition(0);
   }
}
