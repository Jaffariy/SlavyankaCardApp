package com.example.myapplication.MainWindows;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivitiesLoginReg.AddingData;
import com.example.myapplication.ActivitiesLoginReg.LoadingLoginActivity;
import com.example.myapplication.R;

public class CardInfoWindow extends AppCompatActivity {
    private Intent BackToMain;
    TextView cardNumberR, bonuses;

    private Button CardBackButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_card);
        cardNumberR = findViewById(R.id.cardNumber);
        cardNumberR.setText(AddingData.cardnumberDebug);
        bonuses = findViewById(R.id.bonuses);
        bonuses.setText(AddingData.pointsDebug);
        BackToMain = new Intent(this, MainBottomMenu.class);
        CardBackButt = findViewById(R.id.backFromAboutCard);
        CardBackButt.setOnClickListener(view -> {
            startActivity(BackToMain);
            finish();
        });
    }
}
