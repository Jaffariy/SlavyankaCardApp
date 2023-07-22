package com.example.myapplication.ActivitiesLoginReg;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;
import android.app.DatePickerDialog;
import android.view.View;


import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

import com.example.myapplication.R;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity{

    public static final String COLLECTION_PATH = "users";
    public static String password;
    public static String email;
    private DatabaseReference DataBase;
    private String USER_KEY = "User";
    FirebaseAuth firebaseAuth;
    private Intent NextRegStep;
    FirebaseFirestore firestore;
    public static String emailcheck;
    String localsavecardnum = LoadingLoginActivity.cardnumber;
    EditText emailreg, passCreate, passRepeat;
    Button reg2NextButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg2);
        FirebaseApp.initializeApp(this);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        emailreg = findViewById(R.id.emailreg);
        passCreate = findViewById(R.id.passCreate);
        passRepeat = findViewById(R.id.passRepeat);

        reg2NextButt = findViewById(R.id.reg2NextButt);
        reg2NextButt.setOnClickListener(view -> EmailValidCheck());
    }
    public void signUpUser() {
        password = passCreate.getText().toString();
        NextRegStep = new Intent(this, SaveAdditionalInfo.class);
        String password_check = passRepeat.getText().toString();
        email = emailreg.getText().toString();
        String fieldCanNotBeEmpty = getString(R.string.field_can_not_be_empty);
        String passwordIsShort = "Пароль слишком короткий!";
        String passwordIsNotEqual = "Пароль не соответствует!";

        if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailreg.setError(fieldCanNotBeEmpty);
        }
        else if (TextUtils.isEmpty(password)) {
            passCreate.setError(fieldCanNotBeEmpty);
        }
        else if (TextUtils.isEmpty(password_check)) {
            passCreate.setError(fieldCanNotBeEmpty);
        }
        else if(password.length() < 6){
            passCreate.setError(passwordIsShort);
        }
        else if(!password.equals(password_check)){
           passCreate.setError(passwordIsNotEqual);
            Log.d("STRREGNAME","Strings not equal " + password_check);
            Log.d("STRREGNAME","Strings not equal " + password);
       }
        else{
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            startActivity(NextRegStep);
                            finish();
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, getString(R.string.error_can_not_perfom_sign_up), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("TAG", "Failed to create user", e);
                    });
        }
    }

    private void EmailValidCheck() {
        emailcheck = emailreg.getText().toString().trim();
        if (TextUtils.isEmpty(emailcheck)) {
            emailreg.setError(getString(R.string.email_warn));
            emailreg.requestFocus();
        }
         else {
            CollectionReference usersCollection = firestore.collection(COLLECTION_PATH);
            usersCollection
                    .whereEqualTo("email", emailcheck)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                emailreg.setError("Данная электронная почта уже зарегистрирована!");
                                emailreg.requestFocus();
                            } else {
                                signUpUser();
                            }
                        } else {
                            //Log.e("CardValidCheck", "Ошибка при выполнении запроса", task.getException());

                        }
                    });
        }
    }
}
