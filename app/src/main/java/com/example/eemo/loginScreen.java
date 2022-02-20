package com.example.eemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class loginScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<contacts> arrayList = new ArrayList<>();
    AdapterClass adapterClass;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        BottomNavigationView bn = findViewById(R.id.navMenu);
        FloatingActionButton fb = findViewById(R.id.floatingButton);
//        Toolbar tl = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.contactsItem);
//        setSupportActionBar(tl);
        ActionBar ac = getActionBar();
//        ac.setDisplayHomeAsUpEnabled(true);
//        ac.setTitle("EeMo Messenger");
//        ac.setSubtitle("Add Participants");

        bn.setBackground(null);
        bn.getMenu().getItem(2).setEnabled(false);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                check();

            }
        });
    }

    public void check(){

        if (ContextCompat.checkSelfPermission(loginScreen.this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(loginScreen.this,
                    new String[]{Manifest.permission.READ_CONTACTS
                    }, 100);

        } else {

            Intent i =new Intent(loginScreen.this, makeGroup.class);
            startActivity(i);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            Intent i =new Intent(loginScreen.this, makeGroup.class);
            startActivity(i);

    }else{

            Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            check();

        }
}
}