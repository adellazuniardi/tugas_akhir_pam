package com.example.lstprojectt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class detail extends AppCompatActivity {
    private TextView namaBand, genre, terbentuk, biografi;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        namaBand = findViewById(R.id.dnamaband);
        genre = findViewById(R.id.dgenre);
        terbentuk = findViewById(R.id.dterben);
        biografi = findViewById(R.id.dbio);

        progressDialog = new ProgressDialog(detail.this);
        progressDialog.setTitle("Loading");

        Intent intent = getIntent();
        if(intent != null){
            namaBand.setText(intent.getStringExtra("NamaBand"));
            genre.setText(intent.getStringExtra("Genre"));
            terbentuk.setText(intent.getStringExtra("terbentuk"));
            biografi.setText(intent.getStringExtra("bio"));
        }

    }
}