package com.silin.simplegame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.silin.simplegame.characters.Knight;
import com.silin.simplegame.modifiers.LevelUp;
import com.silin.simplegame.save.Save;

public class CreatingCharacter extends AppCompatActivity {

//    public static final String health = "Health";
//    public static final String armor = "Armor";
//    public static final String speed = "Speed";
//    public static final String strength = "Strength";
//    public static final String skillPoints = "skillPoints";
//    public static final String level = "Level";
//    public static final String critChance = "CritChance";
//    public static final String critDamage = "CritDamage";
//    public static final String accuracy = "Accuracy";
//    public static final String dodge = "Dodge";

    private Dialog choice;
    private Button btnContinue,toLevelsMap;
    private RadioButton char1;
    private TextView valueOfSkillPoints,valueLevel,valOfHealth, valOfStrength,valOfCritCh,valOfCritDm,valOfAccuracy,valOfDodge;
    private TextView healthPoint,strengthPoint,critChancePoint,critDamagePoint,accuracyPoint,dodgePoint;
    private ImageView healthPlus,strengthPlus,critChancePlus,critDamagePlus,accuracyPlus,dodgePlus;
    private ImageView healthNeg,strengthNeg,critChanceNeg,critDamageNeg,accuracyNeg,dodgeNeg;
    int incHealth = 15,incStrength = 5,incCritChance = 2,incAccuracy = 2,incDodge = 2;
    double incCritDamage = 0.1;
    int universalValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating_character);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        choice = new Dialog(this);
        choice.setContentView(R.layout.choice_character);
        //choice.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choice.show();
//
        char1 = (RadioButton) choice.findViewById(R.id.radioBtnChar1);
        btnContinue = (Button) choice.findViewById(R.id.btnContinueChoice);
        btnContinue.setOnClickListener(v -> {
           if (char1.isChecked()){

                Knight.getKnight();
                SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                Save.originalParam(save);
                System.out.println("knight " + Knight.getKnight().getHealth());

               valOfHealth = (TextView) findViewById(R.id.valOfHealth);
               valOfHealth.setText(String.valueOf(Knight.getKnight().getHealth()));

               valOfStrength = (TextView) findViewById(R.id.valOfStrength);
               valOfStrength.setText(String.valueOf(Knight.getKnight().getStrength()));

//
//                SharedPreferences.Editor editor = save.edit();
//                editor.putInt(health,Knight.getKnight().getHealth());
//                editor.putInt(armor,Knight.getKnight().getArmor());
//                editor.putInt(speed,Knight.getKnight().getSpeed());
//                editor.putInt(strength,Knight.getKnight().getStrength());
//                editor.putInt(skillPoints,Knight.getKnight().getSkillPoints());
//                editor.putInt(level,Knight.getKnight().getLevel());
//                editor.putInt(critChance,Knight.getKnight().getCritChance());
//                editor.putFloat(critDamage, (float) Knight.getKnight().getCritDamage());
//                editor.putInt(accuracy,Knight.getKnight().getAccuracy());
//                editor.putInt(dodge,Knight.getKnight().getDodge());
//                editor.apply();
            }
             choice.dismiss();
        });

        healthPoint = (EditText) findViewById(R.id.healthPoint);
        strengthPoint = (EditText) findViewById(R.id.strengthPoint);

        valueOfSkillPoints = (EditText) findViewById(R.id.valueOfSkillPoints);
        valueOfSkillPoints.setText(String.valueOf(Knight.getKnight().getSkillPoints()));

        valueLevel = (TextView) findViewById(R.id.valueLevel);
        valueLevel.setText(String.valueOf(Knight.getKnight().getLevel()));



        healthPlus = (ImageView) findViewById(R.id.healthPlus);
        healthPlus.setOnClickListener(v -> {
            if (Integer.parseInt(valueOfSkillPoints.getText().toString())>0){
                LevelUp.levelUp(healthPoint,valueOfSkillPoints,valOfHealth,incHealth,universalValue);
//                universalValue = Integer.parseInt(healthPoint.getText().toString())+1;
//                healthPoint.setText(String.valueOf(universalValue));
//                universalValue = Integer.parseInt(valueOfSkillPoints.getText().toString())-1;
//                valueOfSkillPoints.setText(String.valueOf(universalValue));
//                universalValue = Integer.parseInt(valOfHealth.getText().toString())+incHealth;
//                valOfHealth.setText(String.valueOf(universalValue));
            }
        });
        healthNeg = (ImageView) findViewById(R.id.healthNeg);
        healthNeg.setOnClickListener(v -> {
            if (Integer.parseInt(healthPoint.getText().toString())>0){
                LevelUp.levelDown(healthPoint,valueOfSkillPoints,valOfHealth,incHealth,universalValue);
//                universalValue = Integer.parseInt(healthPoint.getText().toString())-1;
//                healthPoint.setText(String.valueOf(universalValue));
//                universalValue = Integer.parseInt(valueOfSkillPoints.getText().toString())+1;
//                valueOfSkillPoints.setText(String.valueOf(universalValue));
//                universalValue = Integer.parseInt(valOfHealth.getText().toString())-incHealth;
//                valOfHealth.setText(String.valueOf(universalValue));
            }
        });

        strengthPlus = (ImageView) findViewById(R.id.strengthPlus);
        strengthPlus.setOnClickListener(v -> {
            if (Integer.parseInt(valueOfSkillPoints.getText().toString())>0){
                LevelUp.levelUp(strengthPoint,valueOfSkillPoints,valOfStrength,incStrength,universalValue);
            }
        });

        strengthNeg = (ImageView) findViewById(R.id.strengthNeg);
        strengthNeg.setOnClickListener(v -> {
            if (Integer.parseInt(strengthPoint.getText().toString())>0) {
                LevelUp.levelDown(strengthPoint, valueOfSkillPoints, valOfStrength, incStrength, universalValue);
            }
        });

        toLevelsMap = (Button) findViewById(R.id.toLevelsMap);
        toLevelsMap.setOnClickListener(v -> {
            Knight.getKnight().setHealth(Integer.parseInt(valOfHealth.getText().toString()));
            Knight.getKnight().setStrength(Integer.parseInt(valOfStrength.getText().toString()));

            MainActivity.startNewGame = false;
            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            Save.save(save);
            SharedPreferences.Editor editor = save.edit();
            editor.putBoolean(MainActivity.newGame,false);
            editor.putInt(LevelsMap.level,0);
            editor.apply();
            Intent intent = new Intent(CreatingCharacter.this,LevelsMap.class);
            startActivity(intent);finish();
        });

       



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreatingCharacter.this,MainActivity.class);
        startActivity(intent);finish();
    }
}
