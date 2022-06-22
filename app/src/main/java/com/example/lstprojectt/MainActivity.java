package com.example.lstprojectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nama;
    Button masuk;
    String Nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masuk = findViewById(R.id.masuk);
        nama = findViewById(R.id.nama);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nama = nama.getText().toString();

                if (nama.getText().toString().length()==0){
                    nama.setError("perlu memasukkan nama");
                }

                Intent i =new Intent(MainActivity.this,home.class);
                startActivity(i);
            }
        });
    }
}