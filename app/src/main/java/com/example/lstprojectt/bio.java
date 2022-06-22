package com.example.lstprojectt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class bio extends AppCompatActivity {
    private EditText namaband, genre, terbentuk, bio;
    private Button btn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        btn = findViewById(R.id.btnext);
        namaband = findViewById(R.id.namaband);
        genre = findViewById(R.id.genr);
        terbentuk = findViewById(R.id.terb);
        bio = findViewById(R.id.biog);

        progressDialog = new ProgressDialog(bio.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("saving");

        btn.setOnClickListener(view -> {
            if (namaband.getText().length() > 0 && genre.getText().length() > 0 && terbentuk.getText().length() > 0 && bio.getText().length() > 0) {
                saveddata(namaband.getText().toString(), genre.getText().toString(), terbentuk.getText().toString(), bio.getText().toString());
            } else {
                Toast.makeText(this, "Isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            namaband.setText(intent.getStringExtra("NamaBand"));
            genre.setText(intent.getStringExtra("Genre"));
            terbentuk.setText(intent.getStringExtra("terbentuk"));
            bio.setText(intent.getStringExtra("bio"));
        }
    }
    private void saveddata(String namaband, String genre, String terbentuk, String bio){
        Map<String, Object> user = new HashMap<>();
        user.put("NamaBand", namaband);
        user.put("Genre", genre);
        user.put("terbentuk", terbentuk);
        user.put("bio", bio);

        progressDialog.show();

        if (id != null){
            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(bio.this, "Sukses", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(bio.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            db.collection("users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(bio.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(bio.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }

    }
}