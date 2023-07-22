package com.example.myapplication.DiscountWindows;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainWindows.MainBottomMenu;
import com.example.myapplication.R;

public class DiscountFirst extends AppCompatActivity {
    private Intent MoveToMain;
    private Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_first);
        exit = findViewById(R.id.backexitP);
        MoveToMain = new Intent(this, MainBottomMenu.class);
        exit.setOnClickListener(view -> startActivity(MoveToMain));
        //finish();
    }
}
