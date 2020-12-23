package com.example.testingproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingproject.DataHandler.DataHandlerTrainer;
import com.example.testingproject.models.Trainer;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterTrainer extends RecyclerView.Adapter<CustomAdapterTrainer.MyViewHolder>{
    private Context context;
    ArrayList<Trainer> trainers;
    DataHandlerTrainer db;
    private Activity activity;

    public CustomAdapterTrainer(Activity activity, Context context, List<Trainer> trainers, DataHandlerTrainer db) {
        this.context = context;
        this.trainers = (ArrayList<Trainer>) trainers;
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainers_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNameTrainer.setText(String.valueOf(trainers.get(position).getName()));
        holder.txtEmailTrainer.setText(String.valueOf(trainers.get(position).getEmail()));
        holder.txtPhoneTrainer.setText(String.valueOf(trainers.get(position).getPhone()));
        holder.txtHourt.setText(String.valueOf(trainers.get(position).getAssigned())+":00");
//        holder.MemberId.setText(String.valueOf(customers.get(position).getId()));
        holder.imgDeleteTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete " + trainers.get(position).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Log.d("er", "onClick: " + db.getCount());
                            db.removeTrainer(trainers.get(position).getId());
                            trainers.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,TrainerDetailsActivity.class);
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
        holder.imgUpdateTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TrainerUpdateActivity.class);
                intent.putExtra("id",String.valueOf(trainers.get(position).getId()));
                intent.putExtra("phone",String.valueOf(trainers.get(position).getPhone()));
                intent.putExtra("name",String.valueOf(trainers.get(position).getName()));
                intent.putExtra("email",String.valueOf(trainers.get(position).getEmail()));
                intent.putExtra("Assign",String.valueOf(trainers.get(position).getAssigned()));
//                context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
        holder.imgmailTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to send an email to "+holder.txtNameTrainer.getText().toString()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{holder.txtEmailTrainer.getText().toString()});
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
        holder.imgcallTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to call " + holder.txtNameTrainer.getText().toString() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:+91"+holder.txtPhoneTrainer.getText().toString()));
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
        holder.imgexpandtrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewTrainer);
                holder.expandedDetailsTrainer.setVisibility(View.VISIBLE);
                holder.imgexpandtrainer.setVisibility(View.GONE);
                holder.imgcompresstrainer.setVisibility(View.VISIBLE);
                holder.imgsTrainer.setVisibility(View.GONE);
            }
        });

        holder.imgcompresstrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewTrainer);
                holder.expandedDetailsTrainer.setVisibility(View.GONE);
                holder.imgexpandtrainer.setVisibility(View.VISIBLE);
                holder.imgcompresstrainer.setVisibility(View.GONE);
                holder.imgsTrainer.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return trainers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameTrainer,txtEmailTrainer,txtPhoneTrainer,txtHourt,txtMessageTrainer;
        private ImageView imgDeleteTrainer,imgUpdateTrainer,imgexpandtrainer,imgcompresstrainer,imgmailTrainer,imgcallTrainer;
        private CardView cardViewTrainer;
        private RelativeLayout expandedDetailsTrainer,imgsTrainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameTrainer = itemView.findViewById(R.id.txtNameEquip);
            txtEmailTrainer = itemView.findViewById(R.id.txtUnitEquip);
            txtPhoneTrainer = itemView.findViewById(R.id.txtLocatioEquip);
            txtHourt  = itemView.findViewById(R.id.txtHourt);
            imgDeleteTrainer = itemView.findViewById(R.id.imgDeleteEquip);
            imgUpdateTrainer = itemView.findViewById(R.id.imgUpdateEquip);
            imgexpandtrainer = itemView.findViewById(R.id.imgexpandtrainer);
            imgcompresstrainer = itemView.findViewById(R.id.imgcompresstrainer);
            imgsTrainer = itemView.findViewById(R.id.imgsEquip);
            cardViewTrainer = itemView.findViewById(R.id.CardViewTrainer);
            expandedDetailsTrainer = itemView.findViewById(R.id.expandedDetailsTrainer);
            imgmailTrainer = itemView.findViewById(R.id.imgmailtrainer);
            imgcallTrainer = itemView.findViewById(R.id.imgcalltrainer);

        }
    }
}
