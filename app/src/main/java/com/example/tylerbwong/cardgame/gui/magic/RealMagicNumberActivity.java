package com.example.tylerbwong.cardgame.gui.magic;

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

import com.example.tylerbwong.cardgame.gui.adapters.MySpinnerAdapter;
import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.version1_0.magictrick.MagicTrick;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.Arrays;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class RealMagicNumberActivity extends AppCompatActivity implements OnItemSelectedListener {
   private TextView titleLabel;
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

      Typeface gotham = Typefaces.get(this, "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
      backButton = (Button) findViewById(R.id.back);
      nextButton = (Button) findViewById(R.id.next);
      numChoice = (Spinner) findViewById(R.id.spinner);

      titleLabel.setTypeface(gotham);
      backButton.setTypeface(gotham);
      nextButton.setTypeface(gotham);

      MySpinnerAdapter adapter = new MySpinnerAdapter(getWindow().getDecorView(), this,
            android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.number_array)));
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      numChoice.setAdapter(adapter);
      numChoice.setOnItemSelectedListener(this);

      Intent startIntent = getIntent();
      Bundle trickBundle = startIntent.getExtras();
      trick = trickBundle.getParcelable("trick");
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
      backIntent.putExtra("restart", true);
      backIntent.putExtra("trick", trick);
      startActivity(backIntent);
   }

   public void nextAction(View v) {
      trick.setChoice(input);
      Intent nextIntent = new Intent(this, RealMagicPileActivity.class);
      nextIntent.putExtra("trick", trick);
      startActivity(nextIntent);
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
