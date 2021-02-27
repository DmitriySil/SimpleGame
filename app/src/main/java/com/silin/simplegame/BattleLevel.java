package com.silin.simplegame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.silin.simplegame.battle.Battle;
import com.silin.simplegame.characters.Character;
import com.silin.simplegame.characters.EnemyFactory;
import com.silin.simplegame.characters.Knight;
import com.silin.simplegame.modifiers.IncreasingExp;
import com.silin.simplegame.modifiers.LevelUp;
import com.silin.simplegame.save.Save;


public class BattleLevel extends AppCompatActivity {
    Dialog dialogDef;
    Dialog dialogWin;
    Knight player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_level);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        player1 = Knight.getKnight();

        EnemyFactory enemy = new EnemyFactory();


        Character player2 = enemy.getLvl(LevelsMap.lvl);

        ProgressBar healthP1 = (ProgressBar) findViewById(R.id.healthP1);
        healthP1.setMax(player1.getHealth());
        healthP1.setProgress(player1.getHealth());


        ImageView imageViewP1 = (ImageView) findViewById(R.id.imgPlayer1);
        imageViewP1.setBackgroundResource(R.drawable.animation_knight_attack);
        AnimationDrawable animationP1 = (AnimationDrawable) imageViewP1.getBackground();

        ImageView imageViewP2 = (ImageView) findViewById(R.id.imgPlayer2);
        imageViewP2.setBackgroundResource(LevelsMap.drawablePlayer2);
        AnimationDrawable animationP2 = (AnimationDrawable) imageViewP2.getBackground();

        imageViewP2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
            }
        });

        ImageView imgFire = (ImageView) findViewById(R.id.imgFire);
        Animation fireAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dragon_fire);
        Animation btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_attack);



        Button btnPauseMenu = (Button) findViewById(R.id.btnMenu);
        Button btnBattle = (Button) findViewById(R.id.btnStartBattle);
        ImageButton basicAttack = (ImageButton) findViewById(R.id.simple_attack);
        ImageButton powerAttack = (ImageButton) findViewById(R.id.power_attack);
        powerAttack.setVisibility(View.INVISIBLE);

        dialogWin = new Dialog(this);
        dialogWin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWin.setContentView(R.layout.dialog_win);
        dialogWin.setCancelable(false);
        Button btnContinueVic = (Button) dialogWin.findViewById(R.id.btnContinueWin);
        btnContinueVic.setOnClickListener(view -> {

            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            SharedPreferences.Editor editor = save.edit();
             if (LevelsMap.lvl1 & LevelsMap.lvlFinished<1){
                LevelsMap.imgLvl2.setVisibility(View.VISIBLE);
                LevelsMap.lvlFinished = 1;
                IncreasingExp.increasingExp(player1,LevelsMap.lvlFinished);
//сохранение прогресса
                editor.putInt(LevelsMap.level, LevelsMap.lvlFinished);
                editor.apply();
            }
             if (LevelsMap.lvl2 & LevelsMap.lvlFinished<2){
                LevelsMap.imgLvl3.setVisibility(View.VISIBLE);
                LevelsMap.lvlFinished = 2;
                IncreasingExp.increasingExp(player1,LevelsMap.lvlFinished);
//сохранение прогресса
                 editor.putInt(LevelsMap.level, LevelsMap.lvlFinished);
                 editor.apply();
             }
            if (LevelsMap.lvl3 & LevelsMap.lvlFinished<3){
                LevelsMap.imgLvl3.setVisibility(View.VISIBLE);
                LevelsMap.lvlFinished = 3;
                IncreasingExp.increasingExp(player1,LevelsMap.lvlFinished);
//сохранение прогресса
                editor.putInt(LevelsMap.level, LevelsMap.lvlFinished);
                editor.apply();
            }
//возвращение здоровья после битвы
            Knight.getKnight().setHealth(save.getInt(Save.health,0));
            LevelsMap.lvl3 = false;LevelsMap.lvl2 = false;LevelsMap.lvl1 = false;
            LevelUp.levelUp(player1);
            Save.save(save);

            Intent intent = new Intent(BattleLevel.this, com.silin.simplegame.LevelsMap.class);
            startActivity(intent);finish();
            dialogWin.dismiss();
        });

        dialogDef = new Dialog(this);
        dialogDef.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDef.setContentView(R.layout.dialog_defeat);
        dialogDef.setCancelable(false);
        Button btnContinueDef = (Button) dialogDef.findViewById(R.id.btnContinueDef);
        btnContinueDef.setOnClickListener(view -> {

            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            Knight.getKnight().setHealth(save.getInt(Save.health,0));

            Intent intent = new Intent(BattleLevel.this, com.silin.simplegame.LevelsMap.class);
            startActivity(intent);finish();
            dialogDef.dismiss();
        });

        btnBattle.setOnClickListener(v -> {
            btnBattle.setVisibility(View.INVISIBLE);

        Battle.battle(player1, player2, imageViewP1, imageViewP2, imgFire, animationP1, animationP2, fireAnim, btnAnim, basicAttack, powerAttack,btnBattle,healthP1, dialogDef, dialogWin);

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
            Intent intent = new Intent(BattleLevel.this, com.silin.simplegame.LevelsMap.class);
            startActivity(intent);finish();
        }catch (Exception e){}
    }
}