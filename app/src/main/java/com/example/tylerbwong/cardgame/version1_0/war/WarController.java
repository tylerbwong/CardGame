package com.example.tylerbwong.cardgame.version1_0.war;

import com.example.tylerbwong.cardgame.gui.war.WarActivity;

import java.util.Observable;
import java.util.Observer;

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
      war.setCurrentState(War.GameState.HUM_TURN);
   }
}
