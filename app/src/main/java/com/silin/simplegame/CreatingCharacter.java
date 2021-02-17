package com.silin.simplegame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.silin.simplegame.characters.Knight;
import com.silin.simplegame.characters.Modifiers;

public class CreatingCharacter extends AppCompatActivity {

    public static final String health = "Health";
    public static final String armor = "Armor";
    public static final String speed = "Speed";
    public static final String strength = "Strength";
    public static final String skillPoints = "skillPoints";
    public static final String level = "Level";
    public static final String critChance = "CritChance";
    public static final String critDamage = "CritDamage";
    public static final String accuracy = "Accuracy";
    public static final String dodge = "Dodge";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_level);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Knight.getKnight();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt(health,Knight.getKnight().getHealth());
        editor.apply();
    }
}
