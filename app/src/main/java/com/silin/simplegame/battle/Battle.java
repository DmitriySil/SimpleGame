package com.silin.simplegame.battle;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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

    public static void battle(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2,ImageView imgFire, AnimationDrawable animationP1,
                              AnimationDrawable animationP2, Animation fireAnim, Animation btnAnim,ImageButton basicAttack, ImageButton powerAttack, Button btnStart, ProgressBar healthP1, Dialog dialogDef, Dialog dialogWin){

        basicAttack.setOnClickListener(view->{
            basicAttack.startAnimation(btnAnim);
            btnAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (animationP1.isRunning()) {animationP1.stop();}
                    animationP1.start();
                    player1.attack(player2);
                    if (player1.getPowerAttack()>=2){powerAttack.setVisibility(View.VISIBLE);}

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    try {
                        battle1(player1,player2,imageViewP1,imageViewP2,imgFire,animationP1,animationP2,fireAnim,btnAnim,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        });

        powerAttack.setOnClickListener(view2->{
            if (animationP1.isRunning()) {animationP1.stop();}
            animationP1.start();
            player1.powerAttack(player2);
            player1.setPowerAttack(0);powerAttack.setVisibility(View.INVISIBLE);
            try {
                battle1(player1,player2,imageViewP1,imageViewP2,imgFire,animationP1,animationP2,fireAnim,btnAnim,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public static void battle1(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2,ImageView imgFire, AnimationDrawable animationP1, AnimationDrawable animationP2,Animation fireAnim,Animation btnAnim,
                               ImageButton basicAttack, ImageButton powerAttack, Button btnStart, ProgressBar healthP1, Dialog dialogDef, Dialog dialogWin) throws InterruptedException {

        if (player2.getHealth()<=0){
            animationP2.stop();
            imageViewP2.setBackgroundResource(com.silin.simplegame.LevelsMap.drawablePlayer2Dying);
            animationP2 = (AnimationDrawable)imageViewP2.getBackground();
            animationP2.start();
            dialogWin.show();

        }else
            battle2(player1,player2,imageViewP1,imageViewP2,imgFire,animationP1,animationP2,fireAnim,btnAnim,basicAttack,powerAttack,btnStart,healthP1,dialogDef,dialogWin);
    }
    public static void battle2(Character player1, Character player2, ImageView imageViewP1, ImageView imageViewP2,ImageView imgFire, AnimationDrawable animationP1, AnimationDrawable animationP2, Animation fireAnim,Animation btnAnim,
                        ImageButton basicAttack, ImageButton powerAttack, Button btn2, ProgressBar healthP1, Dialog dialogDef,Dialog dialogWin){
        if (animationP2.isRunning()){animationP2.stop();}
        animationP2.start();
        player2.attack(player1);
        healthP1.setProgress(player1.getHealth());
        if (LevelsMap.lvl3){
        imgFire.setBackgroundResource(R.drawable.shot_robot);
        //imgFire.startAnimation(fireAnim);
        int x = (int)imageViewP2.getX()-230;
        int y = (int)imageViewP2.getBottom()/2;
        TranslateAnimation animFire = new TranslateAnimation(x,25,-y,-5);
        System.out.println();
        animFire.setDuration(1000);
        imgFire.startAnimation(animFire);

        animFire.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                imgFire.setBackgroundResource(R.drawable.hit_the_shot);
                AnimationDrawable animationShot = (AnimationDrawable) imgFire.getBackground();
                animationShot.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });}

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
