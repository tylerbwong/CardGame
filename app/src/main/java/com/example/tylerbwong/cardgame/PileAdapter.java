package com.example.tylerbwong.cardgame;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tylerbwong.cardgame.components.Card;

public class PileAdapter extends RecyclerView.Adapter<PileViewHolder> {
   private Card[] cards;
   private Typeface typeface;
   public static SparseArray<Integer> suitMap;

   final static String ACE = "Ace";
   final static String JACK = "Jack";
   final static String QUEEN = "Queen";
   final static String KING = "King";

   static {
      suitMap = new SparseArray<>();

      suitMap.put(0, R.mipmap.spade);
      suitMap.put(1, R.mipmap.heart);
      suitMap.put(2, R.mipmap.diamond);
      suitMap.put(3, R.mipmap.club);
   }

   // Provide a suitable constructor (depends on the kind of dataset)
   public PileAdapter(Card[] cards, Typeface typeface) {
      this.cards = cards;
      this.typeface = typeface;
   }

   // Create new views (invoked by the layout manager)
   @Override
   public PileViewHolder onCreateViewHolder(ViewGroup parent,
                                        int viewType) {
      // create a new view
      View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.pile_view, parent, false);
      // set the view's size, margins, paddings and layout parameters

      PileViewHolder vh = new PileViewHolder(v);
      return vh;
   }

   // Replace the contents of a view (invoked by the layout manager)
   @Override
   public void onBindViewHolder(PileViewHolder holder, int position) {
      // - get element from your dataset at this position
      // - replace the contents of the view with that element
      int temp = cards[position].getNum();
      String result;
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
      holder.suit.setImageResource(suitMap.get(cards[position].getSuitNum()));
      holder.smallRank.setTypeface(typeface);
   }

   // Return the size of your dataset (invoked by the layout manager)
   @Override
   public int getItemCount() {
      return cards.length;
   }

   public void changeCards(Card[] newCards)
   {
      cards = newCards;
      notifyDataSetChanged();
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }
}
