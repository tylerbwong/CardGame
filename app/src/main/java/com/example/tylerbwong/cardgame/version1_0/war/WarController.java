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

   public void handleUserPlay() {
      war.userPlay();
   }

   public void handleCompPlay() {
      war.computerPlay();
   }

   public void handleCompare() {
      war.compareCards();
   }

   public void handleConfirm() {
      war.setCurrentState(War.GameState.HUM_TURN);
   }
}
