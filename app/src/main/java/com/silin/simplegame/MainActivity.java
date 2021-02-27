package com.silin.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double backTime;
    private Toast backToast;
    public static Boolean startNewGame = true;
    public static String newGame = "NewGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Uri data = this.getIntent().getData();
        if (data != null && data.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            Log.i("MyApp", "Deep link clicked " + uri);
            Intent intent = new Intent(MainActivity.this,Browser.class);
            startActivity(intent);finish();
        }

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        if (save.contains(newGame)){
        startNewGame = save.getBoolean(newGame,startNewGame);}



        Button btnContinueGame = (Button) findViewById(R.id.btnContinueGame);
        if (startNewGame){
        btnContinueGame.setVisibility(View.GONE);}
        if (!startNewGame){
            btnContinueGame.setVisibility(View.VISIBLE);
        btnContinueGame.setOnClickListener(v->{
            try {
                Intent intent = new Intent(MainActivity.this,LevelsMap.class);
                startActivity(intent);finish();

            }catch (Exception e){}
        });}

        Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(v -> {
            startNewGame = true;
            Intent intent = new Intent(MainActivity.this,CreatingCharacter.class);
            startActivity(intent);finish();
        });

        Button btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Browser.class);
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
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        if (backTime+2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
        }else backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз чтобы выйти", Toast.LENGTH_SHORT);
        backToast.show();
        backTime = System.currentTimeMillis();
    }
}