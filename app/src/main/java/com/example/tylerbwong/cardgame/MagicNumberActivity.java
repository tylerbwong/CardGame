package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.magictrick.MagicTrick;

import java.util.Arrays;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class MagicNumberActivity extends AppCompatActivity implements OnItemSelectedListener {
   private TextView titleLabel;
   private TextView subtitleLabel;
   private Button backButton;
   private Button nextButton;
   private Spinner numChoice;
   private MagicTrick trick;

   private int input = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_favorite);

      setFullscreen();

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel = (TextView) findViewById(R.id.subtitle_label);
      backButton = (Button) findViewById(R.id.back);
      nextButton = (Button) findViewById(R.id.next);
      numChoice = (Spinner) findViewById(R.id.spinner);

      titleLabel.setTypeface(gotham);
      subtitleLabel.setTypeface(gotham);
      backButton.setTypeface(gotham);
      nextButton.setTypeface(gotham);

      MySpinnerAdapter adapter = new MySpinnerAdapter(getWindow().getDecorView(), this,
            android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.number_array)));
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      numChoice.setAdapter(adapter);
      numChoice.setOnItemSelectedListener(this);

      Intent startIntent = getIntent();
      trick = (MagicTrick) startIntent.getSerializableExtra("trick");
   }

   private class TypefaceSpan extends MetricAffectingSpan {
      private Typeface mTypeface;
      public TypefaceSpan(Typeface typeface) {
         mTypeface = typeface;
      }

      @Override
      public void updateMeasureState(TextPaint p) {
         p.setTypeface(mTypeface);
         p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
      }

      @Override
      public void updateDrawState(TextPaint tp) {
         tp.setTypeface(mTypeface);
         tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
      }
   }

   @Override
   public void onItemSelected(AdapterView<?> parent, View view,
                              int pos, long id) {
      // An item was selected. You can retrieve the selected item using
      // parent.getItemAtPosition(pos)
      input = Integer.parseInt((String) parent.getItemAtPosition(pos));
   }

   @Override
   public void onNothingSelected(AdapterView<?> parent) {
      // Another interface callback
   }

   public void backAction(View v) {
      Intent backIntent = new Intent(this, RealMagicActivity.class);
      startActivity(backIntent);
   }

   public void nextAction(View v) {
      //trick.setChoice(input);
      Intent nextIntent = new Intent(this, PileActivity.class);
      nextIntent.putExtra("trick", trick);
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
