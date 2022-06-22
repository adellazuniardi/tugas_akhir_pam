package com.example.lstprojectt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    //mendefinisikan variable yang akan dipakai
    private RecyclerView recyclerView;
    private FloatingActionButton btnADD;
    //inisialisasi firestore untuk menghubugnkan dengan firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<user> list = new ArrayList<>();
    private UserAdapter useradapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerview);
        btnADD = findViewById(R.id.fab0);

        progressDialog = new ProgressDialog(home.this);
        progressDialog.setTitle("loading silahkan tunggu");
        progressDialog.setMessage("");
        useradapter = new UserAdapter(getApplicationContext(), list);
        useradapter.setDialog(new UserAdapter.Dialog() {

            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogitem = {"Edit","Hapus","Detail"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(home.this);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            //untuk melanjutkan data ke class setelahnya

                            case 0:
                                Intent intent = new Intent(getApplicationContext(),bio.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("NamaBand", list.get(pos).getNamaBand());
                                intent.putExtra("Genre", list.get(pos).getGenre());
                                intent.putExtra("terbentuk", list.get(pos).getTerbentuk());
                                intent.putExtra("bio", list.get(pos).getBio());
                                startActivity(intent);
                                break;
                            case 1:
                                //untuk memanggil delete data class
                                deleteData(list.get(pos).getId());
                                break;
                            case 2:
                                Intent intent1 = new Intent(getApplicationContext(),detail.class);
                                intent1.putExtra("id", list.get(pos).getId());
                                intent1.putExtra("NamaBand", list.get(pos).getNamaBand());
                                intent1.putExtra("Genre", list.get(pos).getGenre());
                                intent1.putExtra("terbentuk", list.get(pos).getTerbentuk());
                                intent1.putExtra("bio", list.get(pos).getBio());
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(useradapter);

        btnADD.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), bio.class));
        });
    }
    //method untuk menampilkan data
    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
    private void getData(){
        progressDialog.show();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                user user = new user(document.getString("NamaBand"), document.getString("Genre"), document.getString("terbentuk"), document.getString("bio"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            useradapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(home.this, "Data tidak berhasil diambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(home.this, "Data tidak berhasil di hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}