package com.example.tylerbwong.cardgame.version1_0.crazyeight;

import com.example.tylerbwong.cardgame.gui.crazyeight.CrazyActivity;
import com.example.tylerbwong.cardgame.version1_0.crazyeight.CrazyEight;

/**
 * @author Tyler Wong
 */
public class CrazyController {
   private CrazyEight crazy;

   public CrazyController(CrazyEight crazy, CrazyActivity activity) {
      this.crazy = crazy;
      crazy.addObserver(activity);
   }
}
