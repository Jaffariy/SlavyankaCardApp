package com.example.myapplication.ActivitiesLoginReg;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainWindows.MainBottomMenu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
public class AddingData extends AppCompatActivity {
    public static String cardnumberDebug, cityDebug, dateBirthDebug, emailDebug, fioDebug, genderDebug, passDebug, phoneDebug, pointsDebug, nameE, surnameE, fathernameE;
    FirebaseAuth firebaseAuth;
    private Intent MoveToMain;
    private FirebaseFirestore db;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        MoveToMain = new Intent(this, MainBottomMenu.class);
        super.onCreate(savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            DocumentReference userRef = db.collection("users").document(currentUserId);
            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    cardnumberDebug = documentSnapshot.getString("cardnumber");
                    cityDebug = documentSnapshot.getString("city");
                    dateBirthDebug = documentSnapshot.getString("dateBirth");
                    emailDebug = documentSnapshot.getString("email");
                    fioDebug = documentSnapshot.getString("fio");
                    genderDebug = documentSnapshot.getString("gender");
                    passDebug = documentSnapshot.getString("pass");
                    phoneDebug = documentSnapshot.getString("phone");
                    cardnumberDebug = documentSnapshot.getString("cardnumber");
                    pointsDebug = documentSnapshot.getString("points");
                    String[] fiosplited = fioDebug.split(" ");
                    surnameE = fiosplited[0];
                    nameE = fiosplited[1];
                    fathernameE = fiosplited[2];
                    startActivity(MoveToMain);
                    finish();
                }

            });
        }
    }
}
