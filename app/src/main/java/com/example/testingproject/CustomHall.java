package com.example.testingproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingproject.DataHandler.DataHandlerGymHall;
import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.GymHall;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class CustomHall extends RecyclerView.Adapter<CustomHall.MyViewHolder> {
    private Context context;
    ArrayList<GymHall> gymHalls;
    DataHandlerGymHall db;
    private Activity activity;

    public CustomHall(Activity activity, Context context, List<GymHall> gymHalls, DataHandlerGymHall db) {
        this.context = context;
        this.gymHalls = (ArrayList<GymHall>) gymHalls;
        this.db = db;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hall_list_view,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNameHall.setText(gymHalls.get(position).getName());
        holder.txtHrHall.setText(gymHalls.get(position).getHrs()+":00");
        holder.imgDeleteHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete " + gymHalls.get(position).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            db.removeHall(gymHalls.get(position).getId());
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            gymHalls.remove(position);
                            notifyDataSetChanged();
                            Intent intent = new Intent(context, HallDetailsActivity.class);
                            activity.startActivity(intent);
                        }
                        catch (Exception e){
                            Log.d("Er","Exception hua");
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gymHalls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameHall,txtHrHall;
        private ImageView imgDeleteHall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameHall = itemView.findViewById(R.id.txtNameHall);
            txtHrHall = itemView.findViewById(R.id.txtHrHall);
            imgDeleteHall = itemView.findViewById(R.id.imgDeleteHall);
        }
    }
}
