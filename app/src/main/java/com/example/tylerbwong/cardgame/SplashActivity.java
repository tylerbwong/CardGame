package com.example.tylerbwong.cardgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by brittanyberlanga on 3/21/16.
 */
public class SplashActivity extends AppCompatActivity {
   private static final int SPLASH_DISPLAY_LENGTH = 1000;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.splash_activity);
      setFullscreen();
      TextView title = (TextView) findViewById(R.id.title_label);
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      title.setTypeface(gotham);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
                /* Create an Intent that will start the Menu-Activity. */
               Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
               mainIntent.putExtra("logo_transition", true);
               LinearLayout logo = (LinearLayout) findViewById(R.id.logo);
               ActivityOptionsCompat options = ActivityOptionsCompat.
                       makeSceneTransitionAnimation(SplashActivity.this, logo, getString(R.string.logo_transition));
               SplashActivity.this.startActivity(mainIntent, options.toBundle());
           }
       }, SPLASH_DISPLAY_LENGTH);
   }

   @Override
   public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      if (hasFocus) {
         setFullscreen();
      }
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
