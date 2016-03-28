package com.example.tylerbwong.cardgame.gui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.version1_0.util.Typefaces;

import java.util.List;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class MySpinnerAdapter extends ArrayAdapter<String> {

   private View decorView;

   Typeface gotham;

   public MySpinnerAdapter(View decorView, Context context, int resource, List<String> items) {
      super(context, resource, items);
      this.decorView = decorView;
      gotham = Typefaces.get(getContext(), "font/gotham-light.ttf");
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      TextView view = (TextView) super.getView(position, convertView, parent);
      view.setTypeface(gotham);
      setFullscreen();
      return view;
   }

   @Override
   public View getDropDownView(int position, View convertView, ViewGroup parent) {
      TextView view = (TextView) super.getDropDownView(position, convertView, parent);
      view.setTypeface(gotham);
      setFullscreen();
      return view;
   }

   private void setFullscreen() {
      decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                  | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                  | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                  | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                  | View.SYSTEM_UI_FLAG_FULLSCREEN
                  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
   }
}
