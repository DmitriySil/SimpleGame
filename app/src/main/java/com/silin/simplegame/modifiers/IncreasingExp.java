package com.silin.simplegame.modifiers;

import android.widget.TextView;

import com.silin.simplegame.characters.Knight;

public class IncreasingExp {

    public static final int[] expArray = new int[]{0, 10, 50, 150, 300, 500, 1000, 2000, 3500, 6000, 9000, 14000, 20000};

   public static void increasingExp(Knight knight , int lvl){
       knight.setExperience(knight.getExperience() + expArray[lvl]);
   }
}
