package com.silin.simplegame.modifiers;

import com.silin.simplegame.characters.Knight;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LevelUp {

    public static final int lvl0 = 0;
    public static final int[] levelArray = new int[]{0, 10, 50, 150, 300, 500, 1000, 2000, 3500, 6000, 9000, 14000, 20000};


    public static void levelUp(Knight knight){
        while (true){
            if (knight.getExperience()>= levelArray[knight.getLevel()+1]){
                knight.setLevel(knight.getLevel()+1);
                knight.setExperience(knight.getExperience()-levelArray[knight.getLevel()+1]);
                knight.setSkillPoints(knight.getSkillPoints()+5);
            }else break;}
    }
}
