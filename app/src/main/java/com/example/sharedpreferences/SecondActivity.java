package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class SecondActivity extends AppCompatActivity {
    private Button buttonDelete, buttonUpdate;
    private TextView textViewResult;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String name;
    private int age;
    private float length;
    private boolean single;
    private Set<String> friendList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        textViewResult = findViewById(R.id.textViewResult);

        sp = getSharedPreferences("PersonalInformation", MODE_PRIVATE);

        //yazma update ve delete emeliyyatlari editor ile yerine yetirilecek
        // amma sadece data oxuyacayiqsa sp bizim ucun kifayetdir
        editor = sp.edit();

        //yazdigimiz data lari indi oxuyaaq
        //ikicni parametrler default ucundur
        name = sp.getString("name", "ad yoxdur");
        age = sp.getInt("age", 0);
        length = sp.getFloat("length", 0.0f);
        single = sp.getBoolean("single ",false);
        friendList = sp.getStringSet("arkadasListesi",null);

        final StringBuilder sb = new StringBuilder();
        for (String s :  friendList ){
            sb.append(s + ", ");
        }

        textViewResult.setText("Name : " + name + "; Age : " + age +
                "; Length : " + length + "; Single : " + single + "; FriendList : " + sb.toString());

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("name");
                editor.commit();
                name = sp.getString("name","ad yoxdur");
                textViewResult.setText("Name : " + name + "; Age : " + age +
                        "; Length : " + length + "; Single : " + single + "; FriendList : " + sb.toString());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("age", 100);
                editor.commit();
                age = sp.getInt("age", 0);
                textViewResult.setText("Name : " + name + "; Age : " + age +
                        "; Length : " + length + "; Single : " + single + "; FriendList : " + sb.toString());
            }
        });
    }
}