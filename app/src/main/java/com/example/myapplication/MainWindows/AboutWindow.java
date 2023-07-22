package com.example.myapplication.MainWindows;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;


import com.example.myapplication.ActivitiesLoginReg.SaveAdditionalInfo;
import com.example.myapplication.R;

public class AboutWindow extends AppCompatActivity {
    private Intent BackToMain;
    Button AboutBackButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_about);
        BackToMain = new Intent(this, MainBottomMenu.class);
        AboutBackButt = findViewById(R.id.backButt);
        AboutBackButt.setOnClickListener(view -> startActivity(BackToMain));
    }
}
