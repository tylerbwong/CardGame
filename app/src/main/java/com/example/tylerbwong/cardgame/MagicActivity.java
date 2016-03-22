package com.example.tylerbwong.cardgame;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tylerbwong on 3/21/16.
 */
public class MagicActivity extends AppCompatActivity {
   private TextView titleLabel;
   private TextView subtitleLabel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_magic);

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel = (TextView) findViewById(R.id.subtitle_label);

      Typeface gotham = Typeface.createFromAsset(getAssets(),"font/gotham-light.ttf");

      titleLabel.setTypeface(gotham);
      subtitleLabel.setTypeface(gotham);
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
                     | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
   }
}
