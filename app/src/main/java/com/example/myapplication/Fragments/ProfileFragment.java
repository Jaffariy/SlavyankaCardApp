package com.example.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ActivitiesLoginReg.AddingData;
import com.example.myapplication.ActivitiesLoginReg.LoadingLoginActivity;
import com.example.myapplication.ActivitiesLoginReg.RegistrationActivity;
import com.example.myapplication.MainWindows.AboutWindow;
import com.example.myapplication.MainWindows.CardInfoWindow;
import com.example.myapplication.MainWindows.ContactsWindow;
import com.example.myapplication.MainWindows.MyDataWindow;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import com.example.myapplication.R;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView NameSurname;

    private Intent AboutWindows;
    private Intent CIFWindow;
    private Intent ContactWindow;
    private Intent MyDWIndow;
    private Intent Logout;

    Button DataBT, ContactsBT, CardBT, AboutBT, ExitBT;

    private String mParam1;

    private String mParam2;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Context context = getContext();
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(context);

        AboutWindows = new Intent(context, AboutWindow.class);
        CIFWindow = new Intent(context, CardInfoWindow.class);
        ContactWindow = new Intent(context, ContactsWindow.class);
        MyDWIndow = new Intent(context, MyDataWindow.class);
        Logout = new Intent(context, LoadingLoginActivity.class);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        DataBT = rootView.findViewById(R.id.myDataButt);
        NameSurname = rootView.findViewById(R.id.User_fio);
        NameSurname.setText(AddingData.nameE +" " + AddingData.surnameE);

        ContactsBT = rootView.findViewById(R.id.Contact);
        CardBT = rootView.findViewById(R.id.infoButt);
        AboutBT = rootView.findViewById(R.id.aboutAppButt);
        ExitBT = rootView.findViewById(R.id.exitButt);

        DataBT.setOnClickListener(view -> startActivity(MyDWIndow));
        ContactsBT.setOnClickListener(view -> startActivity(ContactWindow));
        CardBT.setOnClickListener(view -> startActivity(CIFWindow));
        AboutBT.setOnClickListener(view -> startActivity(AboutWindows));

        ExitBT.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(Logout);
        });

        return rootView;
    }
}