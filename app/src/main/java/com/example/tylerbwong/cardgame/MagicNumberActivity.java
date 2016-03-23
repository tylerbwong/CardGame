package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.MetricAffectingSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.magictrick.MagicTrick;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class MagicNumberActivity extends AppCompatActivity implements TextWatcher {
   private TextView titleLabel;
   private TextView subtitleLabel;
   private Button backButton;
   private Button nextButton;
   private TextInputLayout textEdit;
   private EditText inputText;
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
      textEdit = (TextInputLayout) findViewById(R.id.input_layout);
      backButton = (Button) findViewById(R.id.back);
      nextButton = (Button) findViewById(R.id.next);
      inputText = (EditText) findViewById(R.id.favorite_number);

      textEdit.setTypeface(gotham);
      titleLabel.setTypeface(gotham);
      subtitleLabel.setTypeface(gotham);
      backButton.setTypeface(gotham);
      nextButton.setTypeface(gotham);
      inputText.setTypeface(gotham);

      nextButton.setEnabled(false);

      SpannableString error = new SpannableString("Invalid input");
      error.setSpan(new TypefaceSpan(gotham), 0, error.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      textEdit.setError(error);

      inputText.addTextChangedListener(this);

      Intent startIntent = getIntent();
      trick = (MagicTrick) startIntent.getSerializableExtra("trick");
   }

   @Override
   public void afterTextChanged(Editable s) {
      onTextEdit();
   }

   @Override
   public void beforeTextChanged(CharSequence s, int start, int count, int after) {
   }

   @Override
   public void onTextChanged(CharSequence s, int start, int before, int count) {
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

   private boolean isValidEntry(int num) {
      if (num < 1 || num > 26) {
         return false;
      }
      return true;
   }

   private void onTextEdit() {
      try {
         input = Integer.parseInt(inputText.getText().toString());

         if (isValidEntry(input)) {
            textEdit.setErrorEnabled(false);
            nextButton.setEnabled(true);
         }
         else {
            textEdit.setErrorEnabled(true);
            nextButton.setEnabled(false);
         }
      }
      catch (NumberFormatException e) {
         textEdit.setErrorEnabled(true);
         nextButton.setEnabled(false);
      }
   }

   public void backAction(View v) {
      Intent backIntent = new Intent(this, RealMagicActivity.class);
      startActivity(backIntent);
   }

   public void nextAction(View v) {
      trick.setChoice(input);
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
