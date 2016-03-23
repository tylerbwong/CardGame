package com.example.tylerbwong.cardgame;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tylerbwong on 3/23/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
   public ImageView suit;
   public TextView rank;
   public TextView smallRank;

   public ViewHolder(View itemView) {
      super(itemView);

      suit = (ImageView) itemView.findViewById(R.id.suit);
      rank = (TextView) itemView.findViewById(R.id.rank);
      smallRank = (TextView) itemView.findViewById(R.id.small_rank);
   }
}
