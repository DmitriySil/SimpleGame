package com.silin.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.silin.simplegame.characters.Knight;

public class MainActivity extends AppCompatActivity {
    private double backTime;
    private Toast backToast;

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

        Knight.CreateKnight.getKnight().withHealth(150).create();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt(CreatingCharacter.health,Knight.getKnight().getHealth());
        editor.apply();
        String trxth = String.valueOf(save.getInt(CreatingCharacter.health,0));
        String ttt = String.valueOf(Knight.getKnight().getHealth());
        TextView ff = (TextView) findViewById(R.id.knightHel);
        ff.setText(trxth);
        TextView knig1 = (TextView) findViewById(R.id.knightHel1);
        knig1.setText(ttt);


        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v->{
            try {
                Intent intent = new Intent(MainActivity.this,LevelsMap.class);
                startActivity(intent);finish();

            }catch (Exception e){}
        });

        Button btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Browser.class);
            startActivity(intent);finish();
        });
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