package com.example.tylerbwong.cardgame.gui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;

/**
 * @author Tyler Wong
 */
public class PileViewHolder extends RecyclerView.ViewHolder {
   public ImageView suit;
   public TextView smallRank;

   public PileViewHolder(View itemView) {
      super(itemView);

      suit = (ImageView) itemView.findViewById(R.id.suit);
      smallRank = (TextView) itemView.findViewById(R.id.small_rank);
   }
}
