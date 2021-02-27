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
    public static  String experience = "Experience";

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
        editor.putInt(experience,Knight.getKnight().getExperience());
        editor.apply();
    }

    public static void load(SharedPreferences save){
        Knight.getKnight().setHealth(save.getInt(health,0));
        Knight.getKnight().setArmor(save.getInt(armor,0));
        Knight.getKnight().setSpeed(save.getInt(speed,0));
        Knight.getKnight().setStrength( save.getInt(strength,0));
        Knight.getKnight().setSkillPoints(save.getInt(skillPoints,0));
        Knight.getKnight().setLevel(save.getInt(level,0));
        Knight.getKnight().setCritChance(save.getInt(critChance,0));
        Knight.getKnight().setCritDamage(save.getFloat(critDamage,(float)0));
        Knight.getKnight().setAccuracy( save.getInt(accuracy,0));
        Knight.getKnight().setDodge( save.getInt(dodge,0));
        Knight.getKnight().setExperience( save.getInt(experience,0));
    }

    public static void originalParam(SharedPreferences save){
        SharedPreferences.Editor editor = save.edit();

        editor.putInt(health, 100);
        editor.putInt(armor,0);
        editor.putInt(speed,50);
        editor.putInt(strength,10);
        editor.putInt(skillPoints,5);
        editor.putInt(level,1);
        editor.putInt(critChance,15);
        editor.putFloat(critDamage, (float) 1.2);
        editor.putInt(accuracy,10);
        editor.putInt(dodge,10);
        editor.putInt(experience,0);
        editor.apply();

        Knight.getKnight().setHealth(save.getInt(health,0));
        System.out.println("hel " + save.getInt(health,0));
        Knight.getKnight().setArmor(save.getInt(armor,0));
        Knight.getKnight().setSpeed(save.getInt(speed,0));
        Knight.getKnight().setStrength( save.getInt(strength,0));
        Knight.getKnight().setSkillPoints(save.getInt(skillPoints,0));
        Knight.getKnight().setLevel(save.getInt(level,0));
        Knight.getKnight().setCritChance(save.getInt(critChance,0));
        Knight.getKnight().setCritDamage(save.getFloat(critDamage,(float)0));
        Knight.getKnight().setAccuracy( save.getInt(accuracy,0));
        Knight.getKnight().setDodge( save.getInt(dodge,0));
        Knight.getKnight().setExperience( save.getInt(experience,0));

    }
}
