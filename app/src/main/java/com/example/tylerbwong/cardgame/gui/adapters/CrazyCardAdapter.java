package com.example.tylerbwong.cardgame.gui.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tylerbwong.cardgame.R;
import com.example.tylerbwong.cardgame.gui.viewholders.CardViewHolder;
import com.example.tylerbwong.cardgame.version1_0.components.Card;

/**
 * @author Tyler Wong
 */
public class CrazyCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
   private Card[] cards;
   private Typeface typeface;
   public static SparseArray<Integer> suitMap;

   static {
      suitMap = new SparseArray<>();

      suitMap.put(0, R.mipmap.spade);
      suitMap.put(1, R.mipmap.heart);
      suitMap.put(2, R.mipmap.diamond);
      suitMap.put(3, R.mipmap.club);
   }

   // Provide a suitable constructor (depends on the kind of dataset)
   public CrazyCardAdapter(Card[] cards, Typeface typeface) {
      this.cards = cards;
      this.typeface = typeface;
   }

   public Card[] getCards() {
      return cards;
   }

   // Create new views (invoked by the layout manager)
   @Override
   public CardViewHolder onCreateViewHolder(ViewGroup parent,
                                            int viewType) {
      // create a new view
      View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_view_small, parent, false);
      // set the view's size, margins, paddings and layout parameters

      CardViewHolder vh = new CardViewHolder(v);
      return vh;
   }

   // Replace the contents of a view (invoked by the layout manager)
   @Override
   public void onBindViewHolder(CardViewHolder holder, int position) {
      // - get element from your dataset at this position
      // - replace the contents of the view with that element
      holder.rankTop.setText(cards[position].toString());
      holder.rankBot.setText(cards[position].toString());
      int temp = cards[position].getNum();
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
      holder.rankTop.setText(result);
      holder.rankBot.setText(result);
      holder.suitTop.setImageResource(suitMap.get(cards[position].getSuitNum()));
      holder.suitMid.setImageResource(suitMap.get(cards[position].getSuitNum()));
      holder.suitBot.setImageResource(suitMap.get(cards[position].getSuitNum()));
      holder.rankTop.setTypeface(typeface);
      holder.rankBot.setTypeface(typeface);
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
