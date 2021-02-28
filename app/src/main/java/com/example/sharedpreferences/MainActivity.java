package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guvenliyi artirilmis bir yontemle save edilsin iteyirem
                SharedPreferences sp = getSharedPreferences("PersonalInformation", MODE_PRIVATE);
                //melumati save etmek ucun editor obyekti lazimdir
                //artiq editor la kayit ede bilerik
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", "punhan");
                editor.putInt("age", 23);
                editor.putFloat("height",1.73f);
                editor.putBoolean("single",false );

                // Set tipinde bir liste yaradiriq
                //Hasset arrayList kimi isleyir add metoduyla data ekleyirik amma index lemesini kafasina gore etmekdedir
                Set<String> arkadasListesi = new HashSet<>();
                arkadasListesi.add("friend_1");
                arkadasListesi.add("friend_2");

                editor.putStringSet("arkadasListesi", arkadasListesi);
                editor.commit();

                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }
}