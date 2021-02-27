package com.silin.simplegame.modifiers;

import android.widget.TextView;

import com.silin.simplegame.characters.Knight;

public class IncreasingParam {

    public static void up(TextView view1, TextView view2, TextView view3, int inc, int uValue){
        uValue = Integer.parseInt(view1.getText().toString())+1;
        view1.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view2.getText().toString())-1;
        view2.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view3.getText().toString())+inc;
        view3.setText(String.valueOf(uValue));
        Knight.getKnight().setSkillPoints(Knight.getKnight().getSkillPoints()-1);
    }

    public static void down(TextView view1, TextView view2, TextView view3, int inc, int uValue){
        uValue = Integer.parseInt(view1.getText().toString())-1;
        view1.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view2.getText().toString())+1;
        view2.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view3.getText().toString())-inc;
        view3.setText(String.valueOf(uValue));
        Knight.getKnight().setSkillPoints(Knight.getKnight().getSkillPoints()+1);
    }
}
