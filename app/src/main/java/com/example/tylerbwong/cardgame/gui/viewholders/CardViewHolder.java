package com.example.tylerbwong.cardgame.gui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tylerbwong.cardgame.R;

/**
 * @author Tyler Wong
 */
public class CardViewHolder extends RecyclerView.ViewHolder {
   public ImageView suitTop;
   public ImageView suitMid;
   public ImageView suitBot;
   public TextView rankTop;
   public TextView rankBot;

   public CardViewHolder(View itemView) {
      super(itemView);

      suitTop = (ImageView) itemView.findViewById(R.id.suit_top);
      suitMid = (ImageView) itemView.findViewById(R.id.suit_mid);
      suitBot = (ImageView) itemView.findViewById(R.id.suit_bot);
      rankTop = (TextView) itemView.findViewById(R.id.rank_top);
      rankBot = (TextView) itemView.findViewById(R.id.rank_bot);

   }
}
