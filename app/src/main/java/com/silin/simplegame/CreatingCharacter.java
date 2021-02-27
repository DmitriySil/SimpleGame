package com.silin.simplegame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.silin.simplegame.characters.Knight;
import com.silin.simplegame.modifiers.IncreasingParam;
import com.silin.simplegame.save.Save;

public class CreatingCharacter extends AppCompatActivity {

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

if (MainActivity.startNewGame){
        choice = new Dialog(this);
        choice.setContentView(R.layout.choice_character);
        //choice.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choice.show();
//
        char1 = (RadioButton) choice.findViewById(R.id.radioBtnChar1);
        btnContinue = (Button) choice.findViewById(R.id.btnContinueChoice);
        btnContinue.setOnClickListener(v -> {
           if (char1.isChecked()){

                MainActivity.startNewGame = false;

                Knight.getKnight();
                SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putBoolean(MainActivity.newGame,false);
                editor.putInt(LevelsMap.level,0);
                editor.apply();
                Save.originalParam(save);

               valOfHealth = (TextView) findViewById(R.id.valOfHealth);
               valOfHealth.setText(String.valueOf(Knight.getKnight().getHealth()));

               valOfStrength = (TextView) findViewById(R.id.valOfStrength);
               valOfStrength.setText(String.valueOf(Knight.getKnight().getStrength()));
            }
             choice.dismiss();
        });}else {
               valOfHealth = (TextView) findViewById(R.id.valOfHealth);
               valOfHealth.setText(String.valueOf(Knight.getKnight().getHealth()));

               valOfStrength = (TextView) findViewById(R.id.valOfStrength);
               valOfStrength.setText(String.valueOf(Knight.getKnight().getStrength()));
        }



        healthPoint = (EditText) findViewById(R.id.healthPoint);
        strengthPoint = (EditText) findViewById(R.id.strengthPoint);

        valueOfSkillPoints = (EditText) findViewById(R.id.valueOfSkillPoints);
        valueOfSkillPoints.setText(String.valueOf(Knight.getKnight().getSkillPoints()));

        valueLevel = (TextView) findViewById(R.id.valueLevel);
        valueLevel.setText(String.valueOf(Knight.getKnight().getLevel()));



        healthPlus = (ImageView) findViewById(R.id.healthPlus);
        healthPlus.setOnClickListener(v -> {
            if (Integer.parseInt(valueOfSkillPoints.getText().toString())>0){
                IncreasingParam.up(healthPoint,valueOfSkillPoints,valOfHealth,incHealth,universalValue);
            }
        });
        healthNeg = (ImageView) findViewById(R.id.healthNeg);
        healthNeg.setOnClickListener(v -> {
            if (Integer.parseInt(healthPoint.getText().toString())>0){
                IncreasingParam.down(healthPoint,valueOfSkillPoints,valOfHealth,incHealth,universalValue);
            }
        });

        strengthPlus = (ImageView) findViewById(R.id.strengthPlus);
        strengthPlus.setOnClickListener(v -> {
            if (Integer.parseInt(valueOfSkillPoints.getText().toString())>0){
                IncreasingParam.up(strengthPoint,valueOfSkillPoints,valOfStrength,incStrength,universalValue);
            }
        });

        strengthNeg = (ImageView) findViewById(R.id.strengthNeg);
        strengthNeg.setOnClickListener(v -> {
            if (Integer.parseInt(strengthPoint.getText().toString())>0) {
                IncreasingParam.down(strengthPoint, valueOfSkillPoints, valOfStrength, incStrength, universalValue);
            }
        });

        toLevelsMap = (Button) findViewById(R.id.toLevelsMap);
        toLevelsMap.setOnClickListener(v -> {
            Knight.getKnight().setHealth(Integer.parseInt(valOfHealth.getText().toString()));
            Knight.getKnight().setStrength(Integer.parseInt(valOfStrength.getText().toString()));

            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            Save.save(save);
            Intent intent = new Intent(CreatingCharacter.this,LevelsMap.class);
            startActivity(intent);finish();
        });

       



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreatingCharacter.this,MainActivity.class);
        startActivity(intent);finish();
    }
}
