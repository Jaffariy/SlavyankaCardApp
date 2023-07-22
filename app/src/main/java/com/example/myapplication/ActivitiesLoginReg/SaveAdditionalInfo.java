package com.example.myapplication.ActivitiesLoginReg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.PopupMenu;


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
import android.view.MenuItem;


//import com.example.myapplication.LoadingLoginActivity;
//import com.example.myapplication.RegistrationActivity;
//import com.example.myapplication.UserInfoEnum;
import com.example.myapplication.Enums.UserInfoEnum;
import com.example.myapplication.MainWindows.MainBottomMenu;
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

public class SaveAdditionalInfo extends AppCompatActivity {

    EditText fioText, birthdate, phoneNumber, city;
    private Intent ToLogin;
    private Intent ToMain;
    String choose;
    public String fio, birth, phone, cityData;
    public static final String COLLECTION_PATH = "users";
    String uid;
    Button chooseGenger;
    Button completeRegistration;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg3);


        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        fioText = findViewById(R.id.fioText);
        birthdate = findViewById(R.id.birthdate);
        phoneNumber = findViewById(R.id.phoneNumber);
        city = findViewById(R.id.city);
        ToLogin = new Intent(this, LoadingLoginActivity.class);
        ToMain = new Intent(this, AddingData.class);

        String savepassO = RegistrationActivity.password;
        String savemailO = RegistrationActivity.email;
        /*
        firebaseAuth.signInWithEmailAndPassword(savepassO, savemailO)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(ToMain);
                    } else {
                        //passwordLogAuth.setError(getString(R.string.login_or_password_error));
                    }
                });
*/
        chooseGenger = findViewById(R.id.chooseGenger);
        completeRegistration = findViewById(R.id.completeRegistration);
        completeRegistration.setOnClickListener(view -> {
            String fio = fioText.getText().toString();
            String birth = birthdate.getText().toString();
            String phone = phoneNumber.getText().toString();
            String cityData = city.getText().toString();
            String gend = choose;
            Log.d("STRREGNAME", "fio " + fio);
            Log.d("STRREGNAME", "birth " + birth);
            Log.d("STRREGNAME", "phone " + phone);
            Log.d("STRREGNAME", "city " + cityData);
            addUserInfoDatabase(fio, birth, phone, cityData, savepassO, savemailO, gend);
            startActivity(ToMain);
            finish();
            //showToastLogin("Вы успешно зарегистрировались! Войдите в свой новый аккаунт!");
            //startActivity(ToLogin);
            //finish();
        });
    }

    private void addUserInfoDatabase(String fio, String birth, String phone, String cityData, String savepass, String savemail, String gender){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        uid = currentUser.getUid();
        DocumentReference userRef = firestore.collection(COLLECTION_PATH).document(uid);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put(UserInfoEnum.FIO.getField(), fio);
        userInfo.put(UserInfoEnum.BIRTHDAY.getField(), birth);
        userInfo.put(UserInfoEnum.CITY.getField(), cityData);
        userInfo.put(UserInfoEnum.PHONE.getField(), phone);
        userInfo.put(UserInfoEnum.EMAIL.getField(), savemail);
        userInfo.put(UserInfoEnum.POINTS.getField(), "0");
        userInfo.put(UserInfoEnum.CARDNUM.getField(), LoadingLoginActivity.cardnumber);
        userInfo.put(UserInfoEnum.PASSWORD.getField(), savepass);
        userInfo.put(UserInfoEnum.GENDER.getField(), gender);
        userRef.set(userInfo);
    }
    private void showToastLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showPopupMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.gender_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_male) {
                    showToast("Мужской");
                    choose = "Мужской";
                    return true;
                } else if (item.getItemId() == R.id.menu_female) {
                    showToast("Женский");
                    choose = "Женский";
                    return true;
                } else {
                    return false;
                }
            }

        });
        popupMenu.show();
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
