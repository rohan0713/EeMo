package com.example.eemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class makeGroup extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<contacts> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);
        recyclerView = findViewById(R.id.contactsItem);
        ActionBar ac = getSupportActionBar();
        assert ac != null;
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("New Group");
        ac.setSubtitle("Add Participants");

        getcontacts();
    }

    public void getcontacts(){

        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor cursor = getContentResolver().query(
                uri, null, null, null, sort
        );

        if(cursor.getCount() > 0){

            while(cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                Uri phone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor =getContentResolver().query(
                        phone, null, selection, new String[]{id}, null
                );

                if(phoneCursor.moveToNext()){

                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));

                    arrayList.add(new contacts(name, number));

                    phoneCursor.close();
                }
            }
            cursor.close();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterClass(this, arrayList));

    }
}