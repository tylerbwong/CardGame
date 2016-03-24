package com.example.tylerbwong.cardgame.gui.realmagic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.gui.mainmenu.MainActivity;
import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.version1_0.components.Card;

/**
 * Created by tylerbwong on 3/24/16.
 */
public class RealMagicResultActivity extends AppCompatActivity {
   private Card solution;
   private ImageView suit;
   private TextView rank;
   private TextView title;
   private Button backMenu;
   private Button again;

   final static SparseArray<Integer> suitMap;

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
      setContentView(R.layout.activity_real_magic_result);

      setFullscreen();
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      suit = (ImageView) findViewById(R.id.suit);
      rank = (TextView) findViewById(R.id.rank);
      title = (TextView) findViewById(R.id.title_label);
      backMenu = (Button) findViewById(R.id.back_to_main);
      again = (Button) findViewById(R.id.again);

      title.setTypeface(gotham);
      backMenu.setTypeface(gotham);
      again.setTypeface(gotham);

      Intent startIntent = getIntent();
      Bundle trickBundle = startIntent.getExtras();
      solution = trickBundle.getParcelable("solution");

      showSolution();
   }

   private void showSolution() {
      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");
      String result = "";
      int temp = solution.getNum();
      if (temp >= 11 && temp <= 14) {
         switch (temp) {
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
         result = temp + "";
      }
      rank.setText(result);
      suit.setImageResource(suitMap.get(solution.getSuitNum()));
      rank.setTypeface(gotham);
   }

   public void mainMenuAction(View v) {
      Intent menuIntent = new Intent(this, MainActivity.class);
      startActivity(menuIntent);
   }

   public void againAction(View v) {
      Intent againIntent = new Intent(this, RealMagicActivity.class);
      startActivity(againIntent);
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
