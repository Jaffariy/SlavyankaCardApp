package com.example.myapplication.MainWindows;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ActivitiesLoginReg.AddingData;
import com.example.myapplication.ActivitiesLoginReg.LoadingLoginActivity;
import com.example.myapplication.R;

public class MyDataWindow extends AppCompatActivity {
    private Intent BackToMain;
    private TextView familiya, imya, telephone, pochta, town, birthdatE, gender, fathername;
    private Button DataBackButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_aboutme);
        familiya = findViewById(R.id.familiya);
        imya = findViewById(R.id.imya);
        telephone = findViewById(R.id.telephone);
        pochta = findViewById(R.id.pochta);
        town = findViewById(R.id.town);
        birthdatE = findViewById(R.id.birthdatE);
        gender = findViewById(R.id.gender);
        fathername = findViewById(R.id.fathername);

        familiya.setText(AddingData.surnameE);
        imya.setText(AddingData.nameE);
        telephone.setText(AddingData.phoneDebug);
        pochta.setText(AddingData.emailDebug);
        town.setText(AddingData.cityDebug);
        birthdatE.setText(AddingData.dateBirthDebug);
        gender.setText(AddingData.genderDebug);
        fathername.setText(AddingData.fathernameE);

        BackToMain = new Intent(this, MainBottomMenu.class);
        DataBackButt = findViewById(R.id.backButtonn);
        DataBackButt.setOnClickListener(view -> startActivity(BackToMain));
    }
}
