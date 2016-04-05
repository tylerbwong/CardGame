package com.example.tylerbwong.cardgame.gui.crazyeight;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyController;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyEight;
import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tyler Wong
 */
public class CrazyActivity extends AppCompatActivity implements Observer {

   private CrazyController controller;

   Typeface gotham = Typefaces.get(this, "font/gotham-light.ttf");

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_black);
      setFullscreen();

      controller = new CrazyController(new CrazyEight(), this);
      controller.deal();

      controller.displayChangeSuitDialog(gotham);
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

   }
}
