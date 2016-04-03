package com.example.tylerbwong.cardgame.gui.war;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;
import com.example.tylerbwong.cardgame.version1_0.war.War;
import com.example.tylerbwong.cardgame.version1_0.war.WarController;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tyler Wong
 */
public class WarActivity extends AppCompatActivity implements Observer {
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

   private WarController controller;

   final static int LAST_CARD = 1;
   final static int MIN_PRIZE = 2;
   final static SparseArray<Integer> suitMap;

   Typeface gotham = Typefaces.get(this, "font/gotham-light.ttf");

   static {
      suitMap = new SparseArray<>();

      suitMap.put(0, R.mipmap.spade);
      suitMap.put(1, R.mipmap.heart);
      suitMap.put(2, R.mipmap.diamond);
      suitMap.put(3, R.mipmap.club);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_war);

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

      titleLabel.setTypeface(gotham);
      compCards.setTypeface(gotham);
      compRank.setTypeface(gotham);
      statusLabel.setTypeface(gotham);
      humCards.setTypeface(gotham);
      humRank.setTypeface(gotham);
      compLabel.setTypeface(gotham);
      humLabel.setTypeface(gotham);
      playButton.setTypeface(gotham);

      controller = new WarController(new War(), this);

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
      if (playButton.getText().toString().equals(getResources().getText(R.string.play_card).toString())) {
         controller.handleTurn();
      }
      else if (playButton.getText().toString().equals(getResources().getText(R.string.confirm).toString())){
         controller.handleConfirm();
      }
      else {
         Intent backIntent = new Intent(this, MainActivity.class);
         startActivity(backIntent);
      }
   }

   @Override
   public void update(Observable o, Object arg) {
      War war = (War) o;

      // update status label
      String warString = "";
      int prizeSize = war.getPrizeSize();

      switch (war.getCurrentState()) {
         case HUM_COLLECT:
            if (prizeSize > MIN_PRIZE) {
               warString = getResources().getText(R.string.war_event).toString() +
                     "You get " + war.getPrizeSize() + " cards!";
            }
            else {
               warString = "You get " + war.getPrizeSize() + " cards!";
            }
            statusLabel.setText(warString);
            playButton.setText(getResources().getText(R.string.confirm).toString());
            setScore(war);
            setCards(war);
            break;
         case COMP_COLLECT:
            if (prizeSize > MIN_PRIZE) {
               warString = getResources().getText(R.string.war_event).toString() +
                     "The computer gets " + war.getPrizeSize() + " cards!";
            }
            else {
               warString = "The computer gets " + war.getPrizeSize() + " cards!";
            }
            statusLabel.setText(warString);
            playButton.setText(getResources().getText(R.string.confirm).toString());
            setScore(war);
            setCards(war);
            break;
         case COMP_WIN:
            statusLabel.setText(getResources().getText(R.string.comp_win));
            playButton.setText(getResources().getText(R.string.back_to_main));
            break;
         case HUM_WIN:
            statusLabel.setText(getResources().getText(R.string.hum_win));
            playButton.setText(getResources().getText(R.string.back_to_main));
            break;
         case HUM_TURN:
            statusLabel.setText(getResources().getText(R.string.hum_turn));
            playButton.setText(getResources().getText(R.string.play_card).toString());
            setScore(war);
            break;
         default:
            statusLabel.setText(getResources().getText(R.string.war_start));
            setScore(war);
            break;
      }
   }

   private void setCards(War war) {
      // update cards played
      compSuit.setImageResource(suitMap.get(war.getCompCardInPlay().getSuitNum()));
      humSuit.setImageResource(suitMap.get(war.getUserCardInPlay().getSuitNum()));

      compRank.setText(convertRank(war.getCompCardInPlay().getNum()));
      humRank.setText(convertRank(war.getUserCardInPlay().getNum()));
   }

   private void setScore(War war) {
      // update cards left labels
      String compCardsLeft, humCardsLeft;
      int compScore = war.getCompScore();
      int humScore = war.getHumScore();

      if (compScore != LAST_CARD) {
         compCardsLeft = getResources().getText(R.string.cards_left).toString();
      }
      else {
         compCardsLeft = getResources().getText(R.string.card_left).toString();
      }

      if (humScore != LAST_CARD) {
         humCardsLeft = getResources().getText(R.string.cards_left).toString();
      }
      else {
         humCardsLeft = getResources().getText(R.string.card_left).toString();
      }

      compCards.setText(war.getCompScore() + " " + compCardsLeft);
      humCards.setText(war.getHumScore() + " " + humCardsLeft);
   }

   private String convertRank(int rank) {
      String result = "";
      if (rank >= 11 && rank <= 14) {
         switch (rank) {
            case 11:
               result = "J";
               break;
            case 12:
               result = "Q";
               break;
            case 13:
               result = "K";
               break;
            default:
               result = "A";
               break;
         }
      }
      else {
         result = rank + "";
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
