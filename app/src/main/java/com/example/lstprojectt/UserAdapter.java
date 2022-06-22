package com.example.lstprojectt;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private Context context;
    private List<user> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<user> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdata, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.NamaBand.setText(list.get(position).getNamaBand());
        holder.Genre.setText(list.get(position).getGenre());
        holder.terbentuk.setText(list.get(position).getTerbentuk());
        holder.bio.setText(list.get(position).getBio());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NamaBand,Genre,terbentuk,bio;

        public MyViewHolder(@NonNull View itemview) {
            super(itemview);
            NamaBand = itemview.findViewById(R.id.NamaBand);
            Genre = itemview.findViewById(R.id.Genre);
            terbentuk = itemview.findViewById(R.id.terbentuk);
            bio = itemview.findViewById(R.id.bio);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick((getLayoutPosition()));
                    }
                }
            });
        }
    }
}
