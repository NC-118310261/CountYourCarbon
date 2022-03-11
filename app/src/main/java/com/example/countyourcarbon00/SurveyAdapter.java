package com.example.countyourcarbon00;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.MyViewHolder> {

    Context context;

    ArrayList<SurveyData> list;

    public SurveyAdapter(Context context, ArrayList<SurveyData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_layout,parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SurveyData surveyData = list.get(position);
        holder.school.setText(surveyData.getSchools());
        holder.age.setText(surveyData.getAge());
        holder.confidence.setText(surveyData.getConfidence());
        holder.satisfaction.setText(surveyData.getSatisfaction());
        holder.recycling.setText(surveyData.getRecycling());
        holder.food.setText(surveyData.getFood());
        holder.foodOptions.setText(surveyData.getFoodOptions());
        holder.cups.setText(surveyData.getCups());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView school, age, satisfaction, confidence, recycling, food, foodOptions, cups;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            school = itemView.findViewById(R.id.txtSchool);
            age = itemView.findViewById(R.id.txtAge);
            satisfaction = itemView.findViewById(R.id.txtSatisfaction);
            confidence = itemView.findViewById(R.id.txtConfidence);
            recycling = itemView.findViewById(R.id.txtRecycling);
            food = itemView.findViewById(R.id.txtFood);
            foodOptions = itemView.findViewById(R.id.txtFoodOptions);
            cups = itemView.findViewById(R.id.txtCups);
        }
    }

}
