package com.silin.simplegame.battle;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.silin.simplegame.LevelsMap;
import com.silin.simplegame.R;
import com.silin.simplegame.characters.Character;


public class Battle {
    Character char1, char2;
   // AnimationDrawable2 animationP1, animationP2;
    Button btn1, btn2;

    public Battle() {
    }

    public static void battle(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2, AnimationDrawable animationP1, AnimationDrawable animationP2,
                              ImageButton basicAttack, ImageButton powerAttack, Button btnStart, ProgressBar healthP1, Dialog dialogDef, Dialog dialogWin){

        basicAttack.setOnClickListener(view->{
            if (animationP1.isRunning()) {animationP1.stop();}
            animationP1.start();
            player1.attack(player2);
            if (player1.getPowerAttack()>=2){powerAttack.setVisibility(View.VISIBLE);}
            try {
                battle1(player1,player2,imageViewP1,imageViewP2,animationP1,animationP2,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        powerAttack.setOnClickListener(view2->{
            if (animationP1.isRunning()) {animationP1.stop();}
            animationP1.start();
            player1.powerAttack(player2);
            player1.setPowerAttack(0);powerAttack.setVisibility(View.INVISIBLE);
            try {
                battle1(player1,player2,imageViewP1,imageViewP2,animationP1,animationP2,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public static void battle1(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2, AnimationDrawable animationP1, AnimationDrawable animationP2,
                               ImageButton basicAttack, ImageButton powerAttack, Button btnStart, ProgressBar healthP1, Dialog dialogDef, Dialog dialogWin) throws InterruptedException {

        if (player2.getHealth()<=0){
            animationP2.stop();
            imageViewP2.setBackgroundResource(com.silin.simplegame.LevelsMap.drawablePlayer2Dying);
            animationP2 = (AnimationDrawable)imageViewP2.getBackground();
            animationP2.start();
            dialogWin.show();

        }else
            battle2(player1,player2,imageViewP1,imageViewP2,animationP1,animationP2,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
    }
    public static void battle2(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2, AnimationDrawable animationP1, AnimationDrawable animationP2,
                        ImageButton basicAttack, ImageButton powerAttack, Button btn2, ProgressBar healthP1, Dialog dialogDef,Dialog dialogWin){
        if (animationP2.isRunning()){animationP2.stop();}
        animationP2.start();
        player2.attack(player1);
        healthP1.setProgress(player1.getHealth());
        if (player1.getHealth()<=0){
            if (LevelsMap.lvl1 & LevelsMap.lvlFinished < 1) LevelsMap.lvl1 = false;
            if (LevelsMap.lvl2 & LevelsMap.lvlFinished < 2) LevelsMap.lvl2 = false;
            animationP1.stop();
            imageViewP1.setBackgroundResource(R.drawable.animation_knight_dying);
            animationP1 = (AnimationDrawable)imageViewP1.getBackground();
            animationP1.start();
            dialogDef.show();

        }
    }
}
