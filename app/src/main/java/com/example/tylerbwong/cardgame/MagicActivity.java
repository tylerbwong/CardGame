package com.example.tylerbwong.cardgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tylerbwong on 3/21/16.
 */
public class MagicActivity extends AppCompatActivity implements OnItemSelectedListener {
   final static int ACE = 1;
   final static int JACK = 11;
   final static int QUEEN = 12;
   final static int KING = 13;
   final static int SPADE = 0;
   final static int HEART = 1;
   final static int CLUB = 2;
   final static int DIAMOND = 3;

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
   private ArrayList<RadioButton> suits;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_magic);

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      suits = new ArrayList<>();

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel1 = (TextView) findViewById(R.id.subtitle_label1);
      subtitleLabel2 = (TextView) findViewById(R.id.subtitle_label2);
      spinner = (Spinner) findViewById(R.id.spinner);
      spadeButton = (RadioButton) findViewById(R.id.spade_button);
      heartButton = (RadioButton) findViewById(R.id.heart_button);
      clubButton = (RadioButton) findViewById(R.id.club_button);
      diamondButton = (RadioButton) findViewById(R.id.diamond_button);

      suits.add(spadeButton);
      suits.add(heartButton);
      suits.add(clubButton);
      suits.add(diamondButton);

      submitButton = (Button) findViewById(R.id.submit);
      submitButton.setEnabled(false);

      backButton = (Button) findViewById(R.id.back);

      titleLabel.setTypeface(gotham);
      subtitleLabel1.setTypeface(gotham);
      subtitleLabel2.setTypeface(gotham);
      submitButton.setTypeface(gotham);
      backButton.setTypeface(gotham);

      MySpinnerAdapter adapter = new MySpinnerAdapter(this,
            android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.value_array)));
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(this);
   }

   private static class MySpinnerAdapter extends ArrayAdapter<String> {
      // Initialise custom font, for example:
      Typeface font = Typeface.createFromAsset(getContext().getAssets(),
            "font/gotham-light.ttf");

      // (In reality I used a manager which caches the Typeface objects)
      // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

      private MySpinnerAdapter(Context context, int resource, List<String> items) {
         super(context, resource, items);
      }

      // Affects default (closed) state of the spinner
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
         TextView view = (TextView) super.getView(position, convertView, parent);
         view.setTypeface(font);
         return view;
      }

      // Affects opened state of the spinner
      @Override
      public View getDropDownView(int position, View convertView, ViewGroup parent) {
         TextView view = (TextView) super.getDropDownView(position, convertView, parent);
         view.setTypeface(font);
         return view;
      }
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
            suitChoice = suits.indexOf(suit);
         }
      }

      if (suitCheck) {
         submitButton.setEnabled(true);
      }
   }

   @Override
   public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      View decorView = getWindow().getDecorView();
      if (hasFocus) {
         decorView.setSystemUiVisibility(
               View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                     | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                     | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                     | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                     | View.SYSTEM_UI_FLAG_FULLSCREEN
                     | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
      }
   }
}
