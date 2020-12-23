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


import com.example.testingproject.DataHandler.DataHandlerEquipment;
import com.example.testingproject.models.Equipment;

import java.util.ArrayList;
import java.util.List;

public class CustomEquip extends RecyclerView.Adapter<CustomEquip.MyViewHolder>{
    private Context context;
    ArrayList<Equipment> equipments;
    DataHandlerEquipment db;
    private Activity activity;

    public CustomEquip(Activity activity, Context context, List<Equipment> equipments, DataHandlerEquipment db) {
        this.context = context;
        this.equipments = (ArrayList<Equipment>) equipments;
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomEquip.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equip_list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomEquip.MyViewHolder holder, int position) {
        holder.txtNameEquip.setText(String.valueOf(equipments.get(position).getName()));
        holder.txtUnitEquip.setText(String.valueOf(equipments.get(position).getUnits()));
        holder.txtLocationEquip.setText(String.valueOf(equipments.get(position).getLocation()));
//        holder.MemberId.setText(String.valueOf(customers.get(position).getId()));
        holder.imgDeleteEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete " + equipments.get(position).getName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Log.d("er", "onClick: " + db.getCount());
                            db.removeEquipment(equipments.get(position).getId());
                            equipments.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,EquipmentDetailsActivity.class);
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
        holder.imgUpdateEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EquipmentUpdateActivity.class);
                intent.putExtra("id",String.valueOf(equipments.get(position).getId()));
                intent.putExtra("unit",String.valueOf(equipments.get(position).getUnits()));
                intent.putExtra("name",String.valueOf(equipments.get(position).getName()));
                intent.putExtra("location",String.valueOf(equipments.get(position).getLocation()));
//                context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
        holder.imgexpandequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewEquip);
                holder.expandedDetailsEquip.setVisibility(View.VISIBLE);
                holder.imgexpandequip.setVisibility(View.GONE);
                holder.imgcompressequip.setVisibility(View.VISIBLE);
                holder.imgsEquip.setVisibility(View.GONE);
            }
        });

        holder.imgcompressequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cardViewEquip);
                holder.expandedDetailsEquip.setVisibility(View.GONE);
                holder.imgexpandequip.setVisibility(View.VISIBLE);
                holder.imgcompressequip.setVisibility(View.GONE);
                holder.imgsEquip.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return equipments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameEquip,txtUnitEquip,txtLocationEquip,txtMessageEquip;
        private ImageView imgDeleteEquip,imgUpdateEquip,imgexpandequip,imgcompressequip;
        private CardView cardViewEquip;
        private RelativeLayout expandedDetailsEquip,imgsEquip;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameEquip = itemView.findViewById(R.id.txtNameEquip);
            txtUnitEquip = itemView.findViewById(R.id.txtUnitEquip);
            txtLocationEquip = itemView.findViewById(R.id.txtLocationEquip);
            imgDeleteEquip = itemView.findViewById(R.id.imgDeleteEquip);
            imgUpdateEquip = itemView.findViewById(R.id.imgUpdateEquip);
            txtMessageEquip = itemView.findViewById(R.id.txtMessageEquip);
            imgexpandequip = itemView.findViewById(R.id.imgexpandequip);
            imgcompressequip = itemView.findViewById(R.id.imgcompressequip);
            cardViewEquip = itemView.findViewById(R.id.CardViewEquipment);
            expandedDetailsEquip = itemView.findViewById(R.id.expandedDetailsEquip);
            imgsEquip = itemView.findViewById(R.id.imgsEquip);
        }
    }
}
