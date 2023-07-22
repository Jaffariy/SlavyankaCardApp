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

//import com.example.myapplication.MainBottomMenu;
//import com.example.myapplication.RegistrationActivity;
import com.example.myapplication.MainWindows.MainBottomMenu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

public class LoadingLoginActivity extends AppCompatActivity {
    private EditText cardNum, emailInput, passInput;
    private FirebaseFirestore db;
    private Intent MoveToReg;
    private Intent MoveToAddData;
    public static String cardnumber;
    public static final String COLLECTION_PATH = "users";
    public static final String FIO_REGEXP = "^[А-ЯЁ][а-яё]+\\\\s[А-ЯЁ][а-яё]+\\\\s[А-ЯЁ][а-яё]+$";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    Button signButt;
    Button registrButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        MoveToReg = new Intent(this, RegistrationActivity.class);
        MoveToAddData = new Intent(this, AddingData.class);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        cardNum = findViewById(R.id.cardNum);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);

        signButt = findViewById(R.id.signButt);
        //RandomTest = findViewById(R.id.RandomTest);
        registrButt = findViewById(R.id.registrButt);

        signButt.setOnClickListener(view -> {
            loginCheck();
        });
        //RandomTest.setOnClickListener(view -> CardValidCheck());
        registrButt.setOnClickListener((view -> CardValidCheck()));

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }

    private void CardValidCheck() {
        cardnumber = cardNum.getText().toString().trim();
        if (TextUtils.isEmpty(cardnumber)) {
            cardNum.setError(getString(R.string.cardnum_empty));
            cardNum.requestFocus();
        } else if (cardnumber.length() != 6) {
            cardNum.setError(getString(R.string.lengthwarn));
            cardNum.requestFocus();

        } else {
            CollectionReference usersCollection = firestore.collection(COLLECTION_PATH);
            usersCollection
                    .whereEqualTo("cardnumber", cardnumber)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                cardNum.setError("Данный номер карты уже зарегистрирован");
                                cardNum.requestFocus();
                            } else {
                                startActivity(MoveToReg);
                                finish();
                            }
                        } else {
                            Log.e("CardValidCheck", "Ошибка при выполнении запроса", task.getException());

                        }
                    });
        }
    }

    private void loginCheck() {
        String email = emailInput.getText().toString().trim();
        String password = passInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.login_can_not_be_empty));
            emailInput.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            passInput.setError(getString(R.string.password_can_not_be_empty));
            passInput.requestFocus();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            startActivity(MoveToAddData);
                            finish();
                        } else {
                            passInput.setError(getString(R.string.login_or_password_error));
                        }
                    });
        }
    }
}