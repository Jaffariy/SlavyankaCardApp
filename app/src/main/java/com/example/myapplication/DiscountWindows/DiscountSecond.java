package com.example.myapplication.DiscountWindows;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainWindows.MainBottomMenu;
import com.example.myapplication.R;

public class DiscountSecond extends AppCompatActivity {
    private Intent MoveToMain;
    private Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_second);
        exit = findViewById(R.id.backex);
        MoveToMain = new Intent(this, MainBottomMenu.class);
        exit.setOnClickListener(view -> startActivity(MoveToMain));
        //finish();
    }
}
