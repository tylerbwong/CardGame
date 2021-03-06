package com.example.tylerbwong.cardgame.gui.magic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.gui.adapters.MySpinnerAdapter;
import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.Arrays;

/**
 * @author Tyler Wong
 */
public class MagicActivity extends AppCompatActivity implements OnItemSelectedListener {
   private TextView titleLabel;
   private TextView subtitleLabel1;
   private TextView subtitleLabel2;
   private Spinner spinner;
   private Button submitButton;
   private Button backButton;
   private RadioButton spadeButton;
   private RadioButton heartButton;
   private RadioButton clubButton;
   private RadioButton diamondButton;
   private String rank;
   private int suitChoice;
   private RadioButton[] suits;

   final static int NUM_SUITS = 4;

   Typeface gotham;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_magic);

      gotham = Typefaces.get(this, "font/gotham-light.ttf");

      setFullscreen();

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel1 = (TextView) findViewById(R.id.subtitle_label1);
      subtitleLabel2 = (TextView) findViewById(R.id.subtitle_label2);
      spinner = (Spinner) findViewById(R.id.spinner);
      spadeButton = (RadioButton) findViewById(R.id.spade_button);
      heartButton = (RadioButton) findViewById(R.id.heart_button);
      clubButton = (RadioButton) findViewById(R.id.club_button);
      diamondButton = (RadioButton) findViewById(R.id.diamond_button);

      suits = new RadioButton[NUM_SUITS];

      suits[0] = spadeButton;
      suits[1] = heartButton;
      suits[2] = diamondButton;
      suits[3] = clubButton;

      submitButton = (Button) findViewById(R.id.submit);
      submitButton.setEnabled(false);

      backButton = (Button) findViewById(R.id.back);

      titleLabel.setTypeface(gotham);
      subtitleLabel1.setTypeface(gotham);
      subtitleLabel2.setTypeface(gotham);
      submitButton.setTypeface(gotham);
      backButton.setTypeface(gotham);

      MySpinnerAdapter adapter = new MySpinnerAdapter(getWindow().getDecorView(), this,
            android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.value_array)));
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(this);
   }

   @Override
   public void onItemSelected(AdapterView<?> parent, View view,
                              int pos, long id) {
      // An item was selected. You can retrieve the selected item using
      // parent.getItemAtPosition(pos)
      rank = (String) parent.getItemAtPosition(pos);
   }

   @Override
   public void onNothingSelected(AdapterView<?> parent) {
      // Another interface callback
   }

   public void submitAction(View v) {
      Intent submitIntent = new Intent(this, MagicResultActivity.class);
      submitIntent.putExtra("real_magic", false);
      submitIntent.putExtra("suit", suitChoice);
      submitIntent.putExtra("rank", rank);
      startActivity(submitIntent);
   }

   public void backAction(View v) {
      Intent backIntent = new Intent(this, MainActivity.class);
      startActivity(backIntent);
   }

   public void submitButtonEnabled(View view) {
      boolean suitCheck = false;

      for (RadioButton suit : suits) {
         if (suit.isChecked()) {
            suitCheck = true;
            suitChoice = Arrays.asList(suits).indexOf(suit);
         }
      }

      if (suitCheck) {
         submitButton.setEnabled(true);
      }
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
