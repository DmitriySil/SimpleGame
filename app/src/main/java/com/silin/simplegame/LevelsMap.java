package com.silin.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.silin.simplegame.characters.EnemyFactory;
import com.silin.simplegame.save.Save;

public class LevelsMap extends AppCompatActivity {
    Dialog dialog;

    Button btnBack;

    public static int lvlFinished = 0,lvl2Finished=0,lvl3Finished=0;
    public static boolean lvl1=false,lvl2=false,lvl3=false;
    public static String level = "Level";

    static EnemyFactory.Level lvl;
    static int drawablePlayer2;
    static int drawablePlayer1;
    public static int drawablePlayer2Dying;
    public static int drawablePlayer1Dying;

    public static int health;
    static int armor;
    static int speed;
    public static int strengthLvl1;
    static int skillPoints;


    @SuppressLint("StaticFieldLeak")
    public static ImageView imgLvl1;
    @SuppressLint("StaticFieldLeak")
    public static ImageView imgLvl2;
    @SuppressLint("StaticFieldLeak")
    public static ImageView imgLvl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_map);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//загрузка сохранения
        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        if (save.contains(level)){
        lvlFinished = save.getInt(level, lvlFinished);}
//сохранения
//        Knight.getKnight().withHealth(save.getInt(Save.health,0)).create();
        TextView healthKnight = (TextView) findViewById(R.id.healthknight);
        String text = String.valueOf(save.getInt(Save.health,0));
        healthKnight.setText(text);
        //Knight.getKnight().withHealth(save.getInt("Health",0));


//выбор/начало лвл1
        imgLvl1 = (ImageView) findViewById(R.id.imgLvl1);
        imgLvl2 = (ImageView) findViewById(R.id.imgLvl2);
        imgLvl3 = (ImageView) findViewById(R.id.imgLvl3);

        if (lvlFinished < 1){imgLvl2.setVisibility(View.INVISIBLE);}
        if(lvlFinished < 2){imgLvl3.setVisibility(View.INVISIBLE);}


        imgLvl1.setOnClickListener(v->{
            try {
                lvl1 = true;
                strengthLvl1 = 10;
                health = 100;
                dialog = new Dialog(this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.previewd_dialog_lvl1);
                TextView btnClose = (TextView)dialog.findViewById(R.id.btnClose);
                btnClose.setOnClickListener(view -> {
                    dialog.dismiss();
                });
                RadioGroup difLvl = (RadioGroup) dialog.findViewById(R.id.difLvl);
                difLvl.setOnCheckedChangeListener((group, id) -> {
                    if (id == R.id.radioBtnEasy){
                        LevelsMap.strengthLvl1 = 10;
                        LevelsMap.health = 100;}
                    if (id == R.id.radioBtnMedium){
                        LevelsMap.strengthLvl1 = 25;
                        LevelsMap.health = 150;}
                    if (id == R.id.radioBtnHard){
                        LevelsMap.strengthLvl1 = 50;
                        LevelsMap.health = 250;}
                });

                Button btnStart = (Button)dialog.findViewById(R.id.btnContinueGame);
                btnStart.setOnClickListener(view -> {
                    Intent intent = new Intent(LevelsMap.this,BattleLevel.class);
                    startActivity(intent);finish();
                    dialog.dismiss();
                });
                dialog.show();
                lvl = EnemyFactory.Level.LVL1;
                drawablePlayer2 = R.drawable.animation_fallen_attack;
                drawablePlayer2Dying = R.drawable.animation_fallen1_dying;
            }catch (Exception e){}
        });
//конец лвл1

//начало лвл2

        imgLvl2.setOnClickListener(v->{
            try {
                lvl2 = true;
                lvl = EnemyFactory.Level.LVL2;
                drawablePlayer2 = R.drawable.animation_fallen2_attack;
                drawablePlayer2Dying = R.drawable.animation_fallen2_dying;
                Intent intent = new Intent(LevelsMap.this,BattleLevel.class);
                startActivity(intent);finish();
            }catch (Exception e){}
        });
//конец лвл2
        imgLvl3.setOnClickListener(v->{
            try {
                lvl3 = true;
                lvl = EnemyFactory.Level.LVL3;
                drawablePlayer2 = R.drawable.animation_dragon_attack;
                drawablePlayer2Dying = R.drawable.animation_fallen2_dying;
                Intent intent = new Intent(LevelsMap.this,BattleLevel.class);
                startActivity(intent);finish();
            }catch (Exception e){}
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsMap.this,MainActivity.class);
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
        try {
            Intent intent = new Intent(LevelsMap.this,MainActivity.class);
            startActivity(intent);finish();
        }catch (Exception e){}
    }
}