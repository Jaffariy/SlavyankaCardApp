package com.example.myapplication.MainWindows;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ContactsWindow extends AppCompatActivity {
    private Intent BackToMain;
    private Button ContactsBackButt;

    private Button VK, OK, YT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        BackToMain = new Intent(this, MainBottomMenu.class);

        VK = findViewById(R.id.vkButt);
        OK = findViewById(R.id.classmatesButt);
        YT = findViewById(R.id.youtubeButt);

        VK.setOnClickListener(view -> {
            String url = "https://vk.com/slavyankacom";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        OK.setOnClickListener(view -> {
            String url = "https://ok.ru/slavyankacom";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        YT.setOnClickListener(view -> {
            String url = "https://www.youtube.com/channel/UCLLBUeD_IiJQZJGcZQeh8NA";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        ContactsBackButt = findViewById(R.id.backContacts);
        ContactsBackButt.setOnClickListener(view -> startActivity(BackToMain));
    }
}
