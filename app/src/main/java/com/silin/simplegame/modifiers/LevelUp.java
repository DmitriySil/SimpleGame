package com.silin.simplegame.modifiers;

import android.widget.TextView;

public class LevelUp {

    public static void levelUp(TextView view1,TextView view2,TextView view3,int inc,int uValue){
        uValue = Integer.parseInt(view1.getText().toString())+1;
        view1.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view2.getText().toString())-1;
        view2.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view3.getText().toString())+inc;
        view3.setText(String.valueOf(uValue));

    }

    public static void levelDown(TextView view1,TextView view2,TextView view3,int inc,int uValue){
        uValue = Integer.parseInt(view1.getText().toString())-1;
        view1.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view2.getText().toString())+1;
        view2.setText(String.valueOf(uValue));
        uValue = Integer.parseInt(view3.getText().toString())-inc;
        view3.setText(String.valueOf(uValue));
    }
}
