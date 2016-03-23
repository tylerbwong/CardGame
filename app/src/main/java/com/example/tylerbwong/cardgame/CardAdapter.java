package com.example.tylerbwong.cardgame;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tylerbwong.cardgame.components.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class CardAdapter extends RecyclerView.Adapter<ViewHolder> {
   private ArrayList<Card> cards;
   private Typeface typeface;
   public static HashMap<String, Integer> suitMap;

   final static String ACE = "Ace";
   final static String JACK = "Jack";
   final static String QUEEN = "Queen";
   final static String KING = "King";

   static {
      suitMap = new HashMap<>();

      suitMap.put("Clubs", R.mipmap.club);
      suitMap.put("Hearts", R.mipmap.heart);
      suitMap.put("Diamonds", R.mipmap.diamond);
      suitMap.put("Spades", R.mipmap.spade);
   }

   // Provide a suitable constructor (depends on the kind of dataset)
   public CardAdapter(ArrayList<Card> cards, Typeface typeface) {
      this.cards = cards;
      this.typeface = typeface;
   }

   // Create new views (invoked by the layout manager)
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
      // create a new view
      View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_view, parent, false);
      // set the view's size, margins, paddings and layout parameters

      ViewHolder vh = new ViewHolder(v);
      return vh;
   }

   // Replace the contents of a view (invoked by the layout manager)
   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
      // - get element from your dataset at this position
      // - replace the contents of the view with that element
      holder.rank.setText(cards.get(position).toString());
      int temp = cards.get(position).getNum();
      String result = "";
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
      holder.smallRank.setText(result);
      holder.suit.setImageResource(suitMap.get(cards.get(position).getSuit()));
      holder.rank.setTypeface(typeface);
      holder.smallRank.setTypeface(typeface);
   }

   // Return the size of your dataset (invoked by the layout manager)
   @Override
   public int getItemCount() {
      return cards.size();
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }
}
