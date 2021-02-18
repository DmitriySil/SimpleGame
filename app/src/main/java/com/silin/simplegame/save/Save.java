package com.silin.simplegame.save;

import android.content.SharedPreferences;

import com.silin.simplegame.characters.Knight;

public class Save {

    public static  String health = "Health";
    public static  String armor = "Armor";
    public static  String speed = "Speed";
    public static  String strength = "Strength";
    public static  String skillPoints = "skillPoints";
    public static  String level = "Level";
    public static  String critChance = "CritChance";
    public static  String critDamage = "CritDamage";
    public static  String accuracy = "Accuracy";
    public static  String dodge = "Dodge";

    public static void save(SharedPreferences save){

        SharedPreferences.Editor editor = save.edit();
        editor.putInt(health, Knight.getKnight().getHealth());
        editor.putInt(armor,Knight.getKnight().getArmor());
        editor.putInt(speed,Knight.getKnight().getSpeed());
        editor.putInt(strength,Knight.getKnight().getStrength());
        editor.putInt(skillPoints,Knight.getKnight().getSkillPoints());
        editor.putInt(level,Knight.getKnight().getLevel());
        editor.putInt(critChance,Knight.getKnight().getCritChance());
        editor.putFloat(critDamage, (float) Knight.getKnight().getCritDamage());
        editor.putInt(accuracy,Knight.getKnight().getAccuracy());
        editor.putInt(dodge,Knight.getKnight().getDodge());
        editor.apply();
    }
}
