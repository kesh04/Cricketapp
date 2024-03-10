package com.example.cricketapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> pl_id, ply_name, ply_match, ply_runs, ply_50, ply_100, ply_rate;

    Activity activity;
    CustomAdapter(Activity activity, Context context,
                  ArrayList<String> pl_id,
                  ArrayList<String> ply_name,
                  ArrayList<String> ply_match,
                  ArrayList<String> ply_runs,
                  ArrayList<String> ply_50,
                  ArrayList<String> ply_100,
                  ArrayList<String> ply_rate) {
        this.activity =activity;
        this.context = context;
        this.pl_id = pl_id;
        this.ply_name = ply_name;
        this.ply_match = ply_match;
        this.ply_runs = ply_runs;
        this.ply_50 = ply_50;
        this.ply_100 = ply_100;
        this.ply_rate = ply_rate;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.pl_id.setText(pl_id.get(position));
        holder.ply_name.setText(ply_name.get(position));
        holder.ply_match.setText(ply_match.get(position));
        holder.ply_runs.setText(ply_runs.get(position));
        holder.ply_50.setText(ply_50.get(position));
        holder.ply_100.setText(ply_100.get(position));
        holder.ply_rate.setText(ply_rate.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Update.class);
                intent.putExtra("id",String.valueOf(pl_id.get(position)));
                intent.putExtra("Name",String.valueOf(ply_name.get(position)));
                intent.putExtra("Match",String.valueOf(ply_match.get(position)));
                intent.putExtra("Runs",String.valueOf(ply_runs.get(position)));
                intent.putExtra("50",String.valueOf(ply_50.get(position)));
                intent.putExtra("100",String.valueOf(ply_100.get(position)));
                intent.putExtra("rate",String.valueOf(ply_rate.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pl_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pl_id, ply_name, ply_match, ply_runs, ply_50, ply_100, ply_rate;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pl_id = itemView.findViewById(R.id.pl_id);
            ply_name = itemView.findViewById(R.id.ply_name);
            ply_match = itemView.findViewById(R.id.ply_match);
            ply_runs = itemView.findViewById(R.id.ply_runs);
            ply_50 = itemView.findViewById(R.id.ply_50);
            ply_100 = itemView.findViewById(R.id.ply_100);
            ply_rate = itemView.findViewById(R.id.ply_rate);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
