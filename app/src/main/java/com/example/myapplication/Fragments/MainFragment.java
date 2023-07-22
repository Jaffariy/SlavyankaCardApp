package com.example.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.ActivitiesLoginReg.AddingData;
import com.example.myapplication.ActivitiesLoginReg.LoadingLoginActivity;
import com.example.myapplication.MainWindows.CardInfoWindow;
import com.example.myapplication.MainWindows.MainBottomMenu;
import com.example.myapplication.NotificationSetup.PurchaseNotification;
import com.example.myapplication.NotificationSetup.PurchaseNotificationAdapter;
import com.example.myapplication.R;

public class MainFragment extends Fragment {
    private Intent ToCard;
    private List<PurchaseNotification> purchaseNotifications = new ArrayList<>();
    private PurchaseNotificationAdapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Button toCardButton, notificationButt;
    public MainFragment() {

    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getContext();
        ToCard = new Intent(context, CardInfoWindow.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        notificationButt = view.findViewById(R.id.notificationButt);
        toCardButton = view.findViewById(R.id.toCardButton);
        TextView cn = view.findViewById(R.id.cardNumber);
        TextView bn = view.findViewById(R.id.bonusesMainPage);
        cn.setText(AddingData.cardnumberDebug);
        bn.setText(AddingData.pointsDebug);
        toCardButton.setOnClickListener(view1 -> startActivity(ToCard));
        notificationButt.setOnClickListener(view1 -> {
            adapter = new PurchaseNotificationAdapter(purchaseNotifications);
            recyclerView.setAdapter(adapter);
            purchaseNotifications.add(new PurchaseNotification(1, "Списано 43 бонуса!"));
            purchaseNotifications.add(new PurchaseNotification(2, "Начислено 20 бонусов!"));
            purchaseNotifications.add(new PurchaseNotification(2, "Начислено 40 бонусов!"));
            purchaseNotifications.add(new PurchaseNotification(1, "Списано 38 бонусов!"));
            purchaseNotifications.add(new PurchaseNotification(1, "Списано 15 бонусов!"));
            adapter.setOnItemClickListener(new PurchaseNotificationAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    PurchaseNotification notification = purchaseNotifications.get(position);
                    showToast("Уведомление прочитано!");
                    purchaseNotifications.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            });
        });
        return view;
    }
    private void showToast(String message) {
        Context context = getContext();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}