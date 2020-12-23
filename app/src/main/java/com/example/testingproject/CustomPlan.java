package com.example.testingproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


import com.example.testingproject.DataHandler.DataHandlerExercise;
import com.example.testingproject.models.ExercisePlan;

import java.util.ArrayList;
import java.util.List;

public class CustomPlan extends RecyclerView.Adapter<CustomPlan.MyViewHolder> {
    private Context context;
    ArrayList<ExercisePlan> exercisePlans;
    DataHandlerExercise db;
    private Activity activity;

    public CustomPlan(Activity activity, Context context, List<ExercisePlan> exercisePlans, DataHandlerExercise db) {
        this.context = context;
        this.exercisePlans = (ArrayList<ExercisePlan>) exercisePlans;
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomPlan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomPlan.MyViewHolder holder, int position) {
        holder.txtNamePlan.setText(String.valueOf(exercisePlans.get(position).getName()));
        holder.txtEquipPlan.setText(String.valueOf(exercisePlans.get(position).getReqEquip()));
        holder.txtDescPlan.setText(String.valueOf(exercisePlans.get(position).getDescription()));
        holder.txtFromPlan.setText(String.valueOf(exercisePlans.get(position).getSubmitedBy()));
        holder.imgDeletePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete " + exercisePlans.get(position).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Log.d("er", "onClick: " + db.getCount());
                            db.removePlan(exercisePlans.get(position).getId());
                            exercisePlans.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,PlanDetailsActivity.class);
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
        holder.imgUpdatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlanUpdateActivity.class);
                intent.putExtra("id",String.valueOf(exercisePlans.get(position).getId()));
                intent.putExtra("desc",String.valueOf(exercisePlans.get(position).getDescription()));
                intent.putExtra("name",String.valueOf(exercisePlans.get(position).getName()));
                intent.putExtra("equip",String.valueOf(exercisePlans.get(position).getReqEquip()));
                intent.putExtra("from",String.valueOf(exercisePlans.get(position).getSubmitedBy()));
//                context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
        holder.imgexpandplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewPlan);
                holder.expandedDetailsPlan.setVisibility(View.VISIBLE);
                holder.imgexpandplan.setVisibility(View.GONE);
                holder.imgcompressplan.setVisibility(View.VISIBLE);
                holder.imgsPlan.setVisibility(View.GONE);
            }
        });

        holder.imgcompressplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewPlan);
                holder.expandedDetailsPlan.setVisibility(View.GONE);
                holder.imgexpandplan.setVisibility(View.VISIBLE);
                holder.imgcompressplan.setVisibility(View.GONE);
                holder.imgsPlan.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return exercisePlans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamePlan,txtEquipPlan,txtDescPlan,txtFromPlan,txtMessagePlan;
        private ImageView imgDeletePlan,imgUpdatePlan,imgexpandplan,imgcompressplan;
        private RelativeLayout expandedDetailsPlan,imgsPlan;
        private CardView cardViewPlan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamePlan = itemView.findViewById(R.id.txtNamePlan);
            txtEquipPlan = itemView.findViewById(R.id.txtEquipPlan);
            txtDescPlan = itemView.findViewById(R.id.txtDescPlan);
            txtFromPlan = itemView.findViewById(R.id.txtFromPlan);
            txtMessagePlan = itemView.findViewById(R.id.txtMessagePlan);
            imgDeletePlan = itemView.findViewById(R.id.imgDeletePlan);
            imgUpdatePlan = itemView.findViewById(R.id.imgUpdatePlan);
            imgexpandplan = itemView.findViewById(R.id.imgexpandplan);
            imgcompressplan = itemView.findViewById(R.id.imgcompressplan);
            cardViewPlan = itemView.findViewById(R.id.CardViewPlan);
            expandedDetailsPlan = itemView.findViewById(R.id.expandedDetailsPlan);
            imgsPlan = itemView.findViewById(R.id.imgsPlan);
        }
    }
}
