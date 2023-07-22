package com.example.myapplication.OtherWindows;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivitiesLoginReg.LoadingLoginActivity;
import com.example.myapplication.R;

public class IntroductionActivity extends AppCompatActivity {
    private Intent MoveToLoginCheck;

    Button beginRegButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_reg_page);
        MoveToLoginCheck = new Intent(this, LoadingLoginActivity.class);
        beginRegButt = findViewById(R.id.beginRegButt);
        beginRegButt.setOnClickListener(view -> startActivity(MoveToLoginCheck));
    }
    @Override
    public void onBackPressed() {
    }

}
