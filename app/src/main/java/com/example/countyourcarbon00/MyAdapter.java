package com.example.countyourcarbon00;
//https://www.youtube.com/watch?v=M8sKwoVjqU0
//Firebase Data to RecyclerView | How to Retrieve Firebase Data into Recyclerview | Android Studio

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Users> list;
    private List<Users> exampleListFull;

    public MyAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Users user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.address.setText(user.getAddress());
        holder.school.setText(user.getSchool());
        holder.result.setText(String.valueOf(user.getResult()));

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, email, address, school, result;

        public MyViewHolder(@NonNull View itemView){

            super(itemView);

            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            address = itemView.findViewById(R.id.txtAddress);
            school = itemView.findViewById(R.id.txtSchool);
            result = itemView.findViewById(R.id.txtFootprint);
        }
    }
}
