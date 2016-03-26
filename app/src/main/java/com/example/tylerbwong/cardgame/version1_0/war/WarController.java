package com.example.tylerbwong.cardgame.version1_0.war;

import com.example.tylerbwong.cardgame.gui.war.WarActivity;

/**
 * Created by tylerbwong on 3/25/16.
 */
public class WarController {
   private War war;

   public WarController(War war, WarActivity activity) {
      this.war = war;
      war.addObserver(activity);
   }

   public void handleTurn() {
      war.doTurn();
   }

   public void handleConfirm() {
      if (!war.endGame()) {
         war.setCurrentState(War.GameState.HUM_TURN);
      }
      else {
         // end the game.
      }
   }
}
