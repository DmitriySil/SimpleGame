package com.silin.simplegame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.silin.simplegame.battle.Battle;
import com.silin.simplegame.characters.Character;
import com.silin.simplegame.characters.EnemyFactory;
import com.silin.simplegame.characters.Knight;
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

//singleton сделать todo
        player1 = Knight.CreateKnight.getKnight().create();

        EnemyFactory enemy = new EnemyFactory();


        Character player2 = enemy.getLvl(com.silin.simplegame.LevelsMap.lvl);

        ProgressBar healthP1 = (ProgressBar) findViewById(R.id.healthP1);
        healthP1.setMax(player1.getHealth());


        ImageView imageViewP1 = (ImageView) findViewById(R.id.imgPlayer1);
        imageViewP1.setBackgroundResource(R.drawable.animation_knight_attack);
        AnimationDrawable animationP1 = (AnimationDrawable) imageViewP1.getBackground();

        ImageView imageViewP2 = (ImageView) findViewById(R.id.imgPlayer2);
        imageViewP2.setBackgroundResource(com.silin.simplegame.LevelsMap.drawablePlayer2);
        AnimationDrawable animationP2 = (AnimationDrawable) imageViewP2.getBackground();

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
//сохранение прогресса
            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            SharedPreferences.Editor editor = save.edit();
             if (LevelsMap.lvl1){
                LevelsMap.imgLvl2.setVisibility(View.VISIBLE);
                LevelsMap.lvlFinished = 1;

                editor.putInt(LevelsMap.level, LevelsMap.lvlFinished);
                editor.apply();
            }
             if (LevelsMap.lvl2){
                LevelsMap.imgLvl3.setVisibility(View.VISIBLE);
                LevelsMap.lvlFinished = 2;

                 editor.putInt(LevelsMap.level, LevelsMap.lvlFinished);
                 editor.apply();

             }

            Knight.CreateKnight.getKnight().withHealth(save.getInt(Save.health,0)).create();
//сохранение прогресса
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
            Knight.CreateKnight.getKnight().withHealth(save.getInt(Save.health,0)).create();

            Intent intent = new Intent(BattleLevel.this, com.silin.simplegame.LevelsMap.class);
            startActivity(intent);finish();
            dialogDef.dismiss();
        });

        btnBattle.setOnClickListener(v -> {
            btnBattle.setVisibility(View.INVISIBLE);

        Battle.battle(player1, player2, imageViewP1, imageViewP2, animationP1, animationP2, basicAttack, powerAttack,btnBattle,healthP1, dialogDef, dialogWin);

        });
    }
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(BattleLevel.this, com.silin.simplegame.LevelsMap.class);
            startActivity(intent);finish();
        }catch (Exception e){}
    }
}