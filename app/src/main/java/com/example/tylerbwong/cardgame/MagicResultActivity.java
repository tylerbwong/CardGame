package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by tylerbwong on 3/22/16.
 */
public class MagicResultActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceBundle) {
      super.onCreate(savedInstanceBundle);
      setContentView(R.layout.activity_magic_result);
   }

   public void mainMenuAction(View v) {
      Intent menuIntent = new Intent(this, MainActivity.class);
      startActivity(menuIntent);
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
