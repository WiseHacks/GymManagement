package com.example.testingproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class CustomAvailable extends RecyclerView.Adapter<CustomAvailable.MyViewHolder>{
    private Context context;
    ArrayList<Trainer> trainers;
    DataHandlerTrainer db;
    private Activity activity;

    public CustomAvailable(Activity activity, Context context, List<Trainer> trainers, DataHandlerTrainer db) {
        this.context = context;
        this.trainers = (ArrayList<Trainer>) trainers;
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avail_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNameTrainer2.setText(String.valueOf(trainers.get(position).getName()));
        holder.txtEmailTrainer2.setText(String.valueOf(trainers.get(position).getEmail()));
        holder.txtPhoneTrainer2.setText(String.valueOf(trainers.get(position).getPhone()));

        holder.imgmailavailTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to send an email to "+holder.txtNameTrainer2.getText().toString()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{holder.txtEmailTrainer2.getText().toString()});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
                        emailIntent.putExtra(Intent.EXTRA_TEXT,"");
                        context.startActivity(Intent.createChooser(emailIntent,"Send Email.."));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });builder.create().show();
            }
        });
        holder.imgcallavailTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to call " + holder.txtNameTrainer2.getText().toString() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:+91"+holder.txtPhoneTrainer2.getText().toString()));
                        context.startActivity(callIntent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameTrainer2,txtEmailTrainer2,txtPhoneTrainer2;
        private ImageView imgcallavailTrainer,imgmailavailTrainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameTrainer2 = itemView.findViewById(R.id.txtNameTrainer2);
            txtEmailTrainer2 = itemView.findViewById(R.id.txtEmailTrainer2);
            txtPhoneTrainer2 = itemView.findViewById(R.id.txtPhoneTrainer2);
            imgcallavailTrainer = itemView.findViewById(R.id.imgcallavailtrainer);
            imgmailavailTrainer = itemView.findViewById(R.id.imgmailavailtrainer);
        }
    }
}
