package com.example.tylerbwong.cardgame;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tylerbwong on 3/21/16.
 */
public class MagicActivity extends AppCompatActivity implements OnItemSelectedListener {
   private TextView titleLabel;
   private TextView subtitleLabel1;
   private TextView subtitleLabel2;
   private Spinner spinner;
   private Button submitButton;
   private String selection;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_magic);

      Typeface gotham = Typeface.createFromAsset(getAssets(), "font/gotham-light.ttf");

      titleLabel = (TextView) findViewById(R.id.title_label);
      subtitleLabel1 = (TextView) findViewById(R.id.subtitle_label1);
      subtitleLabel2 = (TextView) findViewById(R.id.subtitle_label2);
      spinner = (Spinner) findViewById(R.id.spinner);
      submitButton = (Button) findViewById(R.id.submit);

      titleLabel.setTypeface(gotham);
      subtitleLabel1.setTypeface(gotham);
      subtitleLabel2.setTypeface(gotham);
      submitButton.setTypeface(gotham);

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
      selection = (String) parent.getItemAtPosition(pos);
   }

   @Override
   public void onNothingSelected(AdapterView<?> parent) {
      // Another interface callback
   }

   public void submitAction(View v) {

   }
}
