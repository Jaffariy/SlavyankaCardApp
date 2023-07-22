package com.example.myapplication.OtherWindows;

import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.myapplication.OtherWindows.IntroductionActivity;
import com.example.myapplication.R;

public class LoadingUseless extends AppCompatActivity {
    private Intent MoveToInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginloading);
        MoveToInt = new Intent(this, IntroductionActivity.class);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(MoveToInt);
            }
        }, 5000);
    }
    @Override
    public void onBackPressed() {
    }
}
